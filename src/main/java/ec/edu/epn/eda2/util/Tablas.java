package ec.edu.epn.eda2.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/** Utilidad para armar tablas de texto (cada fila es un String[]). */
public class Tablas {
    public static void configurar(TableView<String[]> tabla, String... columnas) {
        tabla.getColumns().clear();
        for (int i = 0; i < columnas.length; i++) {
            final int k = i;
            TableColumn<String[], String> c = new TableColumn<>(columnas[i]);
            c.setCellValueFactory(d -> new SimpleStringProperty(d.getValue()[k]));
            c.setSortable(false);
            tabla.getColumns().add(c);
        }
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
