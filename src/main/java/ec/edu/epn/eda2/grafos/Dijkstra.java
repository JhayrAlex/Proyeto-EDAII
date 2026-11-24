package ec.edu.epn.eda2.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/** Caminos mínimos desde un origen (pesos no negativos): O((V + E) log V). */
public class Dijkstra {
    public int[] dist;
    public Vertice[] previo;
    public final List<String> pasos = new ArrayList<>();

    public void ejecutar(Grafo g, Vertice origen) {
        pasos.clear();
        int n = g.vertices.size();
        dist = new int[n];
        Arrays.fill(dist, Grafo.INF);
        previo = new Vertice[n];
        boolean[] fijo = new boolean[n];
        dist[origen.indice] = 0;
        PriorityQueue<int[]> cola = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        cola.add(new int[]{0, origen.indice});
        while (!cola.isEmpty()) {
            int u = cola.poll()[1];
            if (fijo[u]) continue;
            fijo[u] = true;
            Vertice vu = g.vertices.get(u);
            pasos.add("Se fija " + vu.nombre + " con distancia mínima " + dist[u]);
            for (Arista a : g.adyacentes(vu)) {
                Vertice w = a.otro(vu);
                int nd = dist[u] + a.peso;
                if (nd < dist[w.indice]) {
                    dist[w.indice] = nd;
                    previo[w.indice] = vu;
                    cola.add(new int[]{nd, w.indice});
                    pasos.add("   Relajación: " + w.nombre + " mejora a " + nd + " (vía " + vu.nombre + ")");
                }
            }
        }
    }

    public List<Vertice> camino(Vertice destino) {
        LinkedList<Vertice> c = new LinkedList<>();
        if (dist[destino.indice] >= Grafo.INF) return c;
        for (Vertice v = destino; v != null; v = previo[v.indice]) c.addFirst(v);
        return c;
    }
}
