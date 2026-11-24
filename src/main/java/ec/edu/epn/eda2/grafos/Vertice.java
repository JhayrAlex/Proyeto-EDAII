package ec.edu.epn.eda2.grafos;

/** Vértice con posición relativa (0..1) para dibujarlo. */
public class Vertice {
    public final int indice;
    public final String nombre;
    public final double x, y;

    public Vertice(int indice, String nombre, double x, double y) {
        this.indice = indice; this.nombre = nombre; this.x = x; this.y = y;
    }

    @Override public String toString() { return nombre; }
}
