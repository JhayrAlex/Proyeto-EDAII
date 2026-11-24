package ec.edu.epn.eda2.grafos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** Ordenamiento topológico (algoritmo de Kahn): O(V + E). */
public class Kahn {
    public final List<String> pasos = new ArrayList<>();
    public boolean hayCiclo;

    public List<Vertice> ordenar(Grafo g) {
        pasos.clear();
        int n = g.vertices.size();
        int[] grado = new int[n];
        for (Arista a : g.aristas) grado[a.destino.indice]++;
        Deque<Vertice> cola = new ArrayDeque<>();
        StringBuilder ini = new StringBuilder();
        for (Vertice v : g.vertices) {
            if (grado[v.indice] == 0) { cola.add(v); ini.append(v.nombre).append(' '); }
        }
        pasos.add("Vértices sin dependencias (grado de entrada 0): " + ini);
        List<Vertice> orden = new ArrayList<>();
        while (!cola.isEmpty()) {
            Vertice u = cola.poll();
            orden.add(u);
            pasos.add("Se extrae " + u.nombre + " → posición " + orden.size());
            for (Arista a : g.adyacentes(u)) {
                Vertice w = a.destino;
                grado[w.indice]--;
                if (grado[w.indice] == 0) {
                    cola.add(w);
                    pasos.add("   " + w.nombre + " ya no tiene dependencias pendientes → entra a la cola");
                }
            }
        }
        hayCiclo = orden.size() != n;
        return orden;
    }
}
