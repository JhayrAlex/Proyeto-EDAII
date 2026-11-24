package ec.edu.epn.eda2;

import ec.edu.epn.eda2.controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Inicializa el programa y gestiona el cambio entre las dos ventanas. */
public class Main extends Application {

    private static Stage ventana;

    @Override
    public void start(Stage stage) throws Exception {
        ventana = stage;
        stage.setTitle("EDA II – Resumen del Bimestre");
        mostrarInicio();
        stage.show();
    }

    public static void mostrarInicio() throws Exception {
        Parent raiz = FXMLLoader.load(Main.class.getResource("/views/inicio.fxml"));
        cambiar(raiz, 560, 640);
    }

    public static void mostrarPrincipal(String nombre) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/principal.fxml"));
        Parent raiz = loader.load();
        PrincipalController c = loader.getController();
        c.setEstudiante(nombre);
        cambiar(raiz, 1200, 760);
    }

    private static void cambiar(Parent raiz, double w, double h) {
        Scene escena = new Scene(raiz, w, h);
        escena.getStylesheets().add(Main.class.getResource("/css/estilos.css").toExternalForm());
        ventana.setScene(escena);
        ventana.sizeToScene();
        ventana.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
