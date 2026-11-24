package ec.edu.epn.eda2.grafos;

public class Arista {
    public final Vertice origen, destino;
    public final int peso;

    public Arista(Vertice origen, Vertice destino, int peso) {
        this.origen = origen; this.destino = destino; this.peso = peso;
    }

    public Vertice otro(Vertice v) { return v == origen ? destino : origen; }

    @Override public String toString() { return origen.nombre + "–" + destino.nombre + " (" + peso + ")"; }
}
