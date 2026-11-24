package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.clasificacion.SubsetSum;
import ec.edu.epn.eda2.clasificacion.TSP;
import ec.edu.epn.eda2.clasificacion.VerificadorNP;
import ec.edu.epn.eda2.complejidad.BusquedaBinaria;
import ec.edu.epn.eda2.complejidad.BusquedaLineal;
import ec.edu.epn.eda2.util.Contenido;
import ec.edu.epn.eda2.util.MedidorTiempo;
import java.util.Arrays;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

/** Módulo 3: clasificación de problemas (P, NP, NP-completo, NP-difícil). */
public class Modulo3Controller {
    @FXML private HBox selector;
    @FXML private TextArea txtDefinicion, txtSalida;
    @FXML private Label lblA, lblB, lblC;
    @FXML private TextField txtA, txtB, txtC;
    private int clase = 0;

    @FXML
    private void initialize() {
        ((ToggleButton) selector.getChildren().get(0)).setSelected(true);
        configurar(0);
    }

    @FXML
    private void seleccionar(ActionEvent e) {
        ToggleButton b = (ToggleButton) e.getSource();
        b.setSelected(true);
        clase = selector.getChildren().indexOf(b);
        configurar(clase);
    }

    private void visible(Label l, TextField t, boolean v) {
        l.setVisible(v); l.setManaged(v); t.setVisible(v); t.setManaged(v);
    }

    private void configurar(int c) {
        txtDefinicion.setText(Contenido.CLASES[c]);
        txtSalida.clear();
        switch (c) {
            case 0 -> {
                lblA.setText("Arreglo (enteros separados por coma):"); txtA.setText("7, 3, 15, 9, 21, 4, 12");
                lblB.setText("Valor a buscar:"); txtB.setText("9");
                visible(lblA, txtA, true); visible(lblB, txtB, true); visible(lblC, txtC, false);
            }
            case 1 -> {
                lblA.setText("Conjunto:"); txtA.setText("3, 34, 4, 12, 5, 2");
                lblB.setText("Objetivo:"); txtB.setText("9");
                lblC.setText("Certificado (subconjunto propuesto):"); txtC.setText("4, 5");
                visible(lblA, txtA, true); visible(lblB, txtB, true); visible(lblC, txtC, true);
            }
            case 2 -> {
                lblA.setText("Conjunto (máx. 22 elementos):"); txtA.setText("3, 34, 4, 12, 5, 2");
                lblB.setText("Objetivo:"); txtB.setText("9");
                visible(lblA, txtA, true); visible(lblB, txtB, true); visible(lblC, txtC, false);
            }
            default -> {
                lblA.setText("Número de ciudades (3 a 10):"); txtA.setText("8");
                visible(lblA, txtA, true); visible(lblB, txtB, false); visible(lblC, txtC, false);
            }
        }
    }

    private int[] enteros(String s) {
        if (s.isBlank()) return new int[0];
        return Arrays.stream(s.trim().split("[,;\\s]+")).mapToInt(Integer::parseInt).toArray();
    }

    @FXML
    private void ejecutar() {
        try {
            txtSalida.setText(switch (clase) {
                case 0 -> ejecutarP();
                case 1 -> ejecutarNP();
                case 2 -> ejecutarNPCompleto();
                default -> ejecutarNPDificil();
            });
        } catch (NumberFormatException ex) {
            txtSalida.setText("⚠ Revisa los datos: usa números enteros separados por comas.");
        } catch (IllegalArgumentException ex) {
            txtSalida.setText("⚠ " + ex.getMessage());
        }
    }

    private String ejecutarP() {
        int[] a = enteros(txtA.getText());
        int x = Integer.parseInt(txtB.getText().trim());
        BusquedaLineal lineal = new BusquedaLineal();
        int i1 = lineal.buscar(a, x);
        int[] ord = a.clone();
        Arrays.sort(ord);
        BusquedaBinaria bin = new BusquedaBinaria();
        int i2 = bin.buscar(ord, x);
        return "BÚSQUEDA LINEAL  O(n)\n  Índice: " + i1 + "   Comparaciones: " + lineal.operaciones
            + "\n\nBÚSQUEDA BINARIA  O(log n)  (sobre el arreglo ordenado " + Arrays.toString(ord) + ")\n  Índice: " + i2
            + "   Comparaciones: " + bin.operaciones
            + "\n\nAmbos algoritmos son de tiempo polinómico: pertenecen a la clase P.";
    }

    private String ejecutarNP() {
        int[] a = enteros(txtA.getText());
        int obj = Integer.parseInt(txtB.getText().trim());
        int[] cert = enteros(txtC.getText());
        VerificadorNP v = new VerificadorNP();
        v.verificar(a, obj, cert);
        return "Conjunto: " + Arrays.toString(a) + "   Objetivo: " + obj
            + "\nCertificado: " + Arrays.toString(cert)
            + "\n\n" + v.mensaje + "\nOperaciones de verificación: " + v.operaciones
            + "\n\nVerificar es polinómico (≈ n·k). Encontrar el certificado sin conocerlo exige explorar hasta 2ⁿ subconjuntos.";
    }

    private String ejecutarNPCompleto() {
        int[] a = enteros(txtA.getText());
        int obj = Integer.parseInt(txtB.getText().trim());
        if (a.length < 1 || a.length > 22)
            throw new IllegalArgumentException("El conjunto debe tener entre 1 y 22 elementos (se recorren 2ⁿ subconjuntos).");
        SubsetSum ss = new SubsetSum();
        MedidorTiempo m = new MedidorTiempo();
        m.iniciar();
        ss.resolver(a, obj);
        long t = m.detener();
        return "SUBSET SUM por fuerza bruta\nConjunto: " + Arrays.toString(a) + "   Objetivo: " + obj
            + "\nSubconjuntos examinados: " + ss.operaciones + "  (2^" + a.length + " − 1)"
            + "\nSoluciones encontradas: " + ss.soluciones
            + "\nPrimera solución: " + (ss.primera == null ? "ninguna (la respuesta de decisión es NO)" : ss.primera)
            + "\nTiempo: " + MedidorTiempo.formato(t)
            + "\n\nCada elemento adicional duplica el trabajo: complejidad O(2ⁿ·n).";
    }

    private String ejecutarNPDificil() {
        int n = Integer.parseInt(txtA.getText().trim());
        if (n < 3 || n > 10) throw new IllegalArgumentException("El número de ciudades debe estar entre 3 y 10.");
        Random rnd = new Random(7);
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) d[i][j] = d[j][i] = 10 + rnd.nextInt(90);
        StringBuilder sb = new StringBuilder("Matriz de distancias:\n    ");
        for (int j = 0; j < n; j++) sb.append(String.format("%5s", (char) ('A' + j)));
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%4s", (char) ('A' + i)));
            for (int j = 0; j < n; j++) sb.append(String.format("%5d", d[i][j]));
            sb.append('\n');
        }
        TSP tsp = new TSP(d);
        MedidorTiempo m = new MedidorTiempo();
        m.iniciar();
        tsp.resolver();
        long t = m.detener();
        StringBuilder ruta = new StringBuilder();
        for (int c : tsp.mejorRuta) ruta.append((char) ('A' + c)).append(" → ");
        ruta.append('A');
        return sb + "\nMejor ruta: " + ruta + "\nCosto total: " + tsp.mejorCosto
            + "\nRecorridos evaluados: " + tsp.operaciones + "  ((n−1)! = " + tsp.operaciones + ")"
            + "\nTiempo: " + MedidorTiempo.formato(t)
            + "\n\nCon 10 ciudades ya son 362 880 recorridos; con 20 serían ≈ 1.2 × 10¹⁷.";
    }
}
