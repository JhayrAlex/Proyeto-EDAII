package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.eficiencia.Comparador;
import ec.edu.epn.eda2.eficiencia.SumaFor;
import ec.edu.epn.eda2.eficiencia.SumaGauss;
import ec.edu.epn.eda2.util.Contenido;
import ec.edu.epn.eda2.util.MedidorTiempo;
import ec.edu.epn.eda2.util.Tablas;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Módulo 1: eficiencia algorítmica (FOR vs Gauss). */
public class Modulo1Controller {
    private static final long MAX = 100_000_000L;

    @FXML private TextArea txtTeoria, txtAnalisis;
    @FXML private TextField txtN;
    @FXML private ComboBox<String> cmbMetodo;
    @FXML private TableView<String[]> tabla;

    private final SumaFor sumaFor = new SumaFor();
    private final SumaGauss gauss = new SumaGauss();
    private final Comparador comparador = new Comparador();

    @FXML
    private void initialize() {
        txtTeoria.setText(Contenido.EFICIENCIA);
        cmbMetodo.getItems().addAll("Método iterativo (for)", "Método de Gauss (fórmula)");
        cmbMetodo.getSelectionModel().selectFirst();
        Tablas.configurar(tabla, "Método", "Resultado", "Tiempo", "Operaciones", "Temporal", "Espacial");
        txtAnalisis.setText("Ingresa n (1 a 100 000 000) y pulsa «Ejecutar» o «Comparar».");
    }

    private Long leerN() {
        try {
            long n = Long.parseLong(txtN.getText().trim());
            if (n >= 1 && n <= MAX) return n;
        } catch (NumberFormatException ignorada) { }
        txtAnalisis.setText("⚠ n debe ser un entero entre 1 y 100 000 000.");
        return null;
    }

    @FXML
    private void ejecutar() {
        Long n = leerN();
        if (n == null) return;
        boolean usaFor = cmbMetodo.getSelectionModel().getSelectedIndex() == 0;
        MedidorTiempo m = new MedidorTiempo();
        m.iniciar();
        long r = usaFor ? sumaFor.calcular(n) : gauss.calcular(n);
        long t = m.detener();
        long ops = usaFor ? sumaFor.getOperaciones() : gauss.getOperaciones();
        tabla.getItems().add(new String[]{usaFor ? "FOR" : "Gauss", String.valueOf(r),
            MedidorTiempo.formato(t), String.valueOf(ops), usaFor ? "O(n)" : "O(1)", "O(1)"});
        txtAnalisis.setText("n = " + n + "\nResultado: " + r + "\nTiempo: " + MedidorTiempo.formato(t)
            + "\nOperaciones principales: " + ops
            + "\n\nUna sola medición es poco fiable (calentamiento de la JVM). Usa «Comparar» para promedios.");
    }

    @FXML
    private void comparar() {
        Long n = leerN();
        if (n == null) return;
        Comparador.Comparacion c = comparador.comparar(n, 20);
        tabla.getItems().add(new String[]{"FOR (prom. " + c.repeticiones + ")", String.valueOf(c.resultadoFor),
            MedidorTiempo.formato((long) c.promFor), String.valueOf(c.opsFor), "O(n)", "O(1)"});
        tabla.getItems().add(new String[]{"Gauss (prom. " + c.repeticiones + ")", String.valueOf(c.resultadoGauss),
            MedidorTiempo.formato((long) c.promGauss), String.valueOf(c.opsGauss), "O(1)", "O(1)"});
        double razon = c.promFor / Math.max(c.promGauss, 1);
        txtAnalisis.setText("COMPARACIÓN con n = " + n + " (promedio de " + c.repeticiones + " ejecuciones)\n"
            + "Resultados iguales: " + (c.coinciden() ? "sí" : "NO") + " (" + c.resultadoFor + ")\n"
            + "Tiempo promedio FOR:   " + MedidorTiempo.formato((long) c.promFor) + "  →  " + c.opsFor + " operaciones\n"
            + "Tiempo promedio Gauss: " + MedidorTiempo.formato((long) c.promGauss) + "  →  " + c.opsGauss + " operaciones\n"
            + String.format("Gauss fue ≈ %.0f veces más rápido.%n", razon)
            + "\nConclusión: FOR crece con n (O(n)); Gauss mantiene costo constante (O(1)).");
    }
}
