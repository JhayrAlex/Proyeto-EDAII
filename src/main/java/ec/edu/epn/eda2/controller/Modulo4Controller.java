package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.clasificacion.TSP;
import ec.edu.epn.eda2.grafos.*;
import ec.edu.epn.eda2.util.Contenido;
import ec.edu.epn.eda2.util.MedidorTiempo;
import ec.edu.epn.eda2.vista.GrafoCanvas;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

/** Módulo 4: algoritmos de grafos con visualización. */
public class Modulo4Controller {
    @FXML private ComboBox<String> cmbAlgoritmo, cmbOrigen, cmbDestino;
    @FXML private StackPane areaGrafo;
    @FXML private TextArea txtExplicacion, txtResultados;

    private final GrafoCanvas canvas = new GrafoCanvas(620, 470);
    private Grafo grafo;

    @FXML
    private void initialize() {
        areaGrafo.getChildren().add(canvas);
        cmbAlgoritmo.getItems().addAll("Árbol de expansión mínima – Kruskal", "Ordenamiento topológico – Kahn",
            "Caminos mínimos – Dijkstra", "Viajante de comercio (TSP)");
        cmbAlgoritmo.getSelectionModel().selectedIndexProperty().addListener((o, a, b) -> cambiar(b.intValue()));
        cmbAlgoritmo.getSelectionModel().selectFirst();
    }

    private void cambiar(int i) {
        if (i < 0) return;
        grafo = switch (i) {
            case 0, 2 -> Ejemplos.ciudades();
            case 1 -> Ejemplos.tareas();
            default -> Ejemplos.viajante();
        };
        cmbOrigen.getItems().clear();
        cmbDestino.getItems().clear();
        for (Vertice v : grafo.vertices) {
            cmbOrigen.getItems().add(v.nombre);
            cmbDestino.getItems().add(v.nombre);
        }
        cmbOrigen.getSelectionModel().selectFirst();
        cmbDestino.getSelectionModel().selectLast();
        cmbOrigen.setDisable(i != 2);
        cmbDestino.setDisable(i != 2);
        txtExplicacion.setText(Contenido.GRAFOS[i]);
        txtResultados.clear();
        canvas.dibujar(grafo, Set.of(), Map.of());
    }

    @FXML
    private void reiniciar() {
        cambiar(cmbAlgoritmo.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void ejecutar() {
        int i = cmbAlgoritmo.getSelectionModel().getSelectedIndex();
        Set<Arista> resaltadas = new HashSet<>();
        Map<Vertice, String> etiquetas = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        MedidorTiempo m = new MedidorTiempo();

        switch (i) {
            case 0 -> {
                Kruskal k = new Kruskal();
                m.iniciar(); k.ejecutar(grafo); long t = m.detener();
                sb.append("KRUSKAL\n\n");
                k.pasos.forEach(p -> sb.append(p).append('\n'));
                sb.append("\nAristas del árbol: ").append(k.arbol)
                  .append("\nCosto total mínimo: ").append(k.costoTotal)
                  .append("\nTiempo: ").append(MedidorTiempo.formato(t))
                  .append("\nComplejidad: O(E log E)");
                resaltadas.addAll(k.arbol);
            }
            case 1 -> {
                Kahn k = new Kahn();
                m.iniciar(); List<Vertice> orden = k.ordenar(grafo); long t = m.detener();
                sb.append("KAHN\n\n");
                k.pasos.forEach(p -> sb.append(p).append('\n'));
                for (int j = 0; j < orden.size(); j++) etiquetas.put(orden.get(j), "#" + (j + 1));
                sb.append(k.hayCiclo ? "\n⚠ El grafo tiene un ciclo: no existe orden topológico."
                    : "\nOrden topológico: " + orden.stream().map(v -> v.nombre).collect(Collectors.joining(" → ")))
                  .append("\nTiempo: ").append(MedidorTiempo.formato(t))
                  .append("\nComplejidad: O(V + E)");
            }
            case 2 -> {
                Vertice o = grafo.vertices.get(cmbOrigen.getSelectionModel().getSelectedIndex());
                Vertice d = grafo.vertices.get(cmbDestino.getSelectionModel().getSelectedIndex());
                Dijkstra dj = new Dijkstra();
                m.iniciar(); dj.ejecutar(grafo, o); long t = m.detener();
                List<Vertice> camino = dj.camino(d);
                sb.append("DIJKSTRA desde ").append(o.nombre).append("\n\n");
                dj.pasos.forEach(p -> sb.append(p).append('\n'));
                for (int j = 0; j + 1 < camino.size(); j++) resaltadas.add(grafo.buscarArista(camino.get(j), camino.get(j + 1)));
                for (Vertice v : grafo.vertices)
                    if (dj.dist[v.indice] < Grafo.INF) etiquetas.put(v, "d=" + dj.dist[v.indice]);
                sb.append("\nCamino ").append(o.nombre).append(" → ").append(d.nombre).append(": ")
                  .append(camino.isEmpty() ? "no existe"
                      : camino.stream().map(v -> v.nombre).collect(Collectors.joining(" → "))
                        + "  (costo " + dj.dist[d.indice] + ")")
                  .append("\nTiempo: ").append(MedidorTiempo.formato(t))
                  .append("\nComplejidad: O((V + E) log V)");
            }
            default -> {
                TSP tsp = new TSP(grafo.matrizPesos());
                m.iniciar(); tsp.resolver(); long t = m.detener();
                int n = grafo.vertices.size();
                StringBuilder ruta = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    Vertice a = grafo.vertices.get(tsp.mejorRuta[j]);
                    Vertice b = grafo.vertices.get(tsp.mejorRuta[(j + 1) % n]);
                    resaltadas.add(grafo.buscarArista(a, b));
                    etiquetas.put(a, String.valueOf(j + 1));
                    ruta.append(a.nombre).append(" → ");
                }
                ruta.append(grafo.vertices.get(0).nombre);
                sb.append("VIAJANTE DE COMERCIO (fuerza bruta)\n\nRecorridos evaluados: ").append(tsp.operaciones)
                  .append("  ((n−1)! con n = ").append(n).append(")\nMejor ruta: ").append(ruta)
                  .append("\nCosto total: ").append(tsp.mejorCosto)
                  .append("\nTiempo: ").append(MedidorTiempo.formato(t))
                  .append("\nComplejidad: O(n!)");
            }
        }
        txtResultados.setText(sb.toString());
        canvas.dibujar(grafo, resaltadas, etiquetas);
    }
}
