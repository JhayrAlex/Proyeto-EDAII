package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.complejidad.Ejecutor;
import ec.edu.epn.eda2.util.Contenido;
import ec.edu.epn.eda2.util.MedidorTiempo;
import ec.edu.epn.eda2.util.Resultado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/** Módulo 2: análisis de complejidad con ejemplos ejecutables. */
public class Modulo2Controller {
    @FXML private TextArea txtTeoria, txtSalida;
    @FXML private FlowPane botones;
    @FXML private Label lblSeleccion;
    @FXML private TextField txtN;
    @FXML private LineChart<Number, Number> grafico;
    private int tipo = -1;

    @FXML
    private void initialize() {
        txtTeoria.setText(Contenido.COMPLEJIDAD);
        txtSalida.setText("Selecciona una complejidad con los botones de arriba.");
        grafico.setAnimated(false);
    }

    @FXML
    private void seleccionar(ActionEvent e) {
        tipo = botones.getChildren().indexOf(e.getSource());
        lblSeleccion.setText(Ejecutor.NOMBRES[tipo] + "   (n máximo: " + Ejecutor.MAXIMOS[tipo] + ")");
        txtN.setText(String.valueOf(Ejecutor.SUGERIDOS[tipo]));
    }

    private Integer leerN() {
        if (tipo < 0) { txtSalida.setText("⚠ Primero selecciona una complejidad."); return null; }
        try {
            int n = Integer.parseInt(txtN.getText().trim());
            if (n >= 1 && n <= Ejecutor.MAXIMOS[tipo]) return n;
        } catch (NumberFormatException ignorada) { }
        txtSalida.setText("⚠ Para este algoritmo n debe estar entre 1 y " + Ejecutor.MAXIMOS[tipo] + ".");
        return null;
    }

    @FXML
    private void ejecutar() {
        Integer n = leerN();
        if (n == null) return;
        Resultado r = Ejecutor.ejecutar(tipo, n);
        txtSalida.setText("Algoritmo: " + Ejecutor.NOMBRES[tipo] + "\nTamaño de entrada n = " + n
            + "\nResultado: " + r.detalle
            + "\nOperaciones principales: " + r.operaciones
            + "\nTiempo de ejecución: " + MedidorTiempo.formato(r.nanos)
            + "\nComplejidad teórica: " + Ejecutor.TEORICA[tipo]
            + "\n\nInterpretación: " + Ejecutor.INTERPRETACION[tipo]);
    }

    @FXML
    private void graficar() {
        Integer n = leerN();
        if (n == null) return;
        XYChart.Series<Number, Number> serie = new XYChart.Series<>();
        serie.setName(Ejecutor.TEORICA[tipo]);
        int puntos = tipo >= 5 ? n : 10;
        for (int i = 1; i <= puntos; i++) {
            int k = tipo >= 5 ? i : Math.max(1, (int) ((long) n * i / puntos));
            serie.getData().add(new XYChart.Data<>(k, Ejecutor.ejecutar(tipo, k).operaciones));
        }
        grafico.getData().clear();
        grafico.getData().add(serie);
    }
}
