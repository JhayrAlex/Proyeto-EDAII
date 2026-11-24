package ec.edu.epn.eda2.grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Árbol de expansión mínima con Kruskal y Union-Find: O(E log E). */
public class Kruskal {
    public final List<Arista> arbol = new ArrayList<>();
    public final List<String> pasos = new ArrayList<>();
    public int costoTotal;
    private int[] padre;

    public void ejecutar(Grafo g) {
        arbol.clear(); pasos.clear(); costoTotal = 0;
        int n = g.vertices.size();
        padre = new int[n];
        for (int i = 0; i < n; i++) padre[i] = i;
        List<Arista> orden = new ArrayList<>(g.aristas);
        orden.sort(Comparator.comparingInt((Arista a) -> a.peso));
        for (Arista a : orden) {
            int ra = buscar(a.origen.indice), rb = buscar(a.destino.indice);
            if (ra != rb) {
                padre[ra] = rb;
                arbol.add(a);
                costoTotal += a.peso;
                pasos.add("✔ Se acepta " + a + " (une dos componentes)");
            } else {
                pasos.add("✘ Se descarta " + a + " (formaría un ciclo)");
            }
            if (arbol.size() == n - 1) break;
        }
    }

    private int buscar(int x) {
        while (padre[x] != x) { padre[x] = padre[padre[x]]; x = padre[x]; }
        return x;
    }
}
