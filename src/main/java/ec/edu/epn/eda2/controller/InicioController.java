package ec.edu.epn.eda2.controller;

import ec.edu.epn.eda2.Main;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Ventana 1: presentación del estudiante (nombre fijo). */
public class InicioController {
    private static final String ESTUDIANTE = "Jhayr Alexander Pazmiño";

    @FXML private Label lblLogo;
    @FXML private ImageView imgLogo;

    @FXML
    private void initialize() {
        InputStream in = getClass().getResourceAsStream("/images/logo-epn.png");
        if (in != null) {
            imgLogo.setImage(new Image(in));
            lblLogo.setVisible(false);
        }
    }

    @FXML
    private void ingresar() {
        try {
            Main.mostrarPrincipal(ESTUDIANTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
