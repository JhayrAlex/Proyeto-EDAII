package ec.edu.epn.eda2.vista;

import ec.edu.epn.eda2.grafos.Arista;
import ec.edu.epn.eda2.grafos.Grafo;
import ec.edu.epn.eda2.grafos.Vertice;
import java.util.Map;
import java.util.Set;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/** Dibuja un grafo: aristas resaltadas en naranja y etiquetas junto a los vértices. */
public class GrafoCanvas extends Canvas {
    private static final double R = 28, M = 60;
    private static final Color MARINO = Color.web("#17365D"), ACERO = Color.web("#2874A6"),
            GRIS = Color.web("#94A3B8"), NARANJA = Color.web("#E67E22");

    public GrafoCanvas(double ancho, double alto) { super(ancho, alto); }

    private double px(Vertice v) { return M + v.x * (getWidth() - 2 * M); }
    private double py(Vertice v) { return M + v.y * (getHeight() - 2 * M); }

    public void dibujar(Grafo g, Set<Arista> resaltadas, Map<Vertice, String> etiquetas) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
        if (g == null) return;
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        for (Arista a : g.aristas) {
            double x1 = px(a.origen), y1 = py(a.origen), x2 = px(a.destino), y2 = py(a.destino);
            boolean res = resaltadas.contains(a);
            gc.setStroke(res ? NARANJA : GRIS);
            gc.setFill(res ? NARANJA : GRIS);
            gc.setLineWidth(res ? 4 : 1.8);
            gc.strokeLine(x1, y1, x2, y2);
            if (g.isDirigido()) {
                double ang = Math.atan2(y2 - y1, x2 - x1);
                double tx = x2 - R * Math.cos(ang), ty = y2 - R * Math.sin(ang), s = 13;
                gc.fillPolygon(
                    new double[]{tx, tx - s * Math.cos(ang - 0.4), tx - s * Math.cos(ang + 0.4)},
                    new double[]{ty, ty - s * Math.sin(ang - 0.4), ty - s * Math.sin(ang + 0.4)}, 3);
            }
            if (g.isPonderado()) {
                double mx = (x1 + x2) / 2, my = (y1 + y2) / 2;
                gc.setFill(Color.WHITE);
                gc.fillRoundRect(mx - 15, my - 10, 30, 20, 8, 8);
                gc.setFill(res ? NARANJA : MARINO);
                gc.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
                gc.fillText(String.valueOf(a.peso), mx, my);
            }
        }
        for (Vertice v : g.vertices) {
            double x = px(v), y = py(v);
            boolean marcado = etiquetas.containsKey(v);
            gc.setFill(marcado ? ACERO : MARINO);
            gc.fillOval(x - R, y - R, 2 * R, 2 * R);
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Segoe UI", FontWeight.BOLD, v.nombre.length() > 4 ? 11 : 15));
            gc.fillText(v.nombre, x, y);
            if (marcado) {
                gc.setFill(NARANJA);
                gc.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
                gc.fillText(etiquetas.get(v), x, y - R - 10);
            }
        }
    }
}
