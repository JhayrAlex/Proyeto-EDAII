package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.Main;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/** Ventana 2: menú de cuatro módulos; carga cada vista FXML bajo demanda. */
public class PrincipalController {
    private static final String[] VISTAS = {
        "/views/modulo1.fxml", "/views/modulo2.fxml", "/views/modulo3.fxml", "/views/modulo4.fxml"};

    @FXML private Label lblEstudiante;
    @FXML private StackPane contenido;
    @FXML private VBox menu;
    private final Node[] cache = new Node[4];

    @FXML
    private void initialize() {
        ((ToggleButton) menu.getChildren().get(0)).setSelected(true);
        mostrar(0);
    }

    public void setEstudiante(String nombre) {
        lblEstudiante.setText("Estudiante: " + nombre);
    }

    @FXML
    private void abrir(ActionEvent e) {
        ToggleButton b = (ToggleButton) e.getSource();
        b.setSelected(true);
        mostrar(menu.getChildren().indexOf(b));
    }

    private void mostrar(int i) {
        try {
            if (cache[i] == null) cache[i] = FXMLLoader.load(getClass().getResource(VISTAS[i]));
            contenido.getChildren().setAll(cache[i]);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void volver() {
        try {
            Main.mostrarInicio();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
