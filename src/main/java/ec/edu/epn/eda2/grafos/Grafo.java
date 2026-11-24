package ec.edu.epn.eda2.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Grafo con lista de vértices y aristas (dirigido o no, ponderado o no). */
public class Grafo {
    public static final int INF = Integer.MAX_VALUE / 4;

    public final List<Vertice> vertices = new ArrayList<>();
    public final List<Arista> aristas = new ArrayList<>();
    private final boolean dirigido, ponderado;

    public Grafo(boolean dirigido, boolean ponderado) {
        this.dirigido = dirigido; this.ponderado = ponderado;
    }

    public boolean isDirigido() { return dirigido; }
    public boolean isPonderado() { return ponderado; }

    public Vertice agregarVertice(String nombre, double x, double y) {
        Vertice v = new Vertice(vertices.size(), nombre, x, y);
        vertices.add(v);
        return v;
    }

    public Arista agregarArista(Vertice o, Vertice d, int peso) {
        Arista a = new Arista(o, d, peso);
        aristas.add(a);
        return a;
    }

    /** Aristas que salen de v (en grafos no dirigidos, todas las incidentes). */
    public List<Arista> adyacentes(Vertice v) {
        List<Arista> r = new ArrayList<>();
        for (Arista a : aristas) {
            if (a.origen == v) r.add(a);
            else if (!dirigido && a.destino == v) r.add(a);
        }
        return r;
    }

    public Arista buscarArista(Vertice a, Vertice b) {
        for (Arista e : aristas) {
            if (e.origen == a && e.destino == b) return e;
            if (!dirigido && e.origen == b && e.destino == a) return e;
        }
        return null;
    }

    public int[][] matrizPesos() {
        int n = vertices.size();
        int[][] d = new int[n][n];
        for (int[] fila : d) Arrays.fill(fila, INF);
        for (int i = 0; i < n; i++) d[i][i] = 0;
        for (Arista a : aristas) {
            d[a.origen.indice][a.destino.indice] = a.peso;
            if (!dirigido) d[a.destino.indice][a.origen.indice] = a.peso;
        }
        return d;
    }
}
