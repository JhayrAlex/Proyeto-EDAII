package ec.edu.epn.eda2.clasificacion;

import ec.edu.epn.eda2.grafos.Grafo;

/** Viajante de comercio por fuerza bruta: evalúa (n-1)! recorridos. */
public class TSP {
    private final int[][] d;
    private int[] actual;
    private boolean[] visitado;
    public long operaciones;
    public int mejorCosto;
    public int[] mejorRuta;

    public TSP(int[][] distancias) { this.d = distancias; }

    public void resolver() {
        int n = d.length;
        operaciones = 0; mejorCosto = Grafo.INF; mejorRuta = null;
        actual = new int[n]; visitado = new boolean[n];
        actual[0] = 0; visitado[0] = true;
        buscar(1, 0);
    }

    private void buscar(int k, int costo) {
        int n = d.length;
        if (k == n) {
            operaciones++;
            int vuelta = d[actual[n - 1]][0];
            if (vuelta < Grafo.INF && costo + vuelta < mejorCosto) {
                mejorCosto = costo + vuelta;
                mejorRuta = actual.clone();
            }
            return;
        }
        for (int c = 1; c < n; c++) {
            if (!visitado[c] && d[actual[k - 1]][c] < Grafo.INF) {
                visitado[c] = true;
                actual[k] = c;
                buscar(k + 1, costo + d[actual[k - 1]][c]);
                visitado[c] = false;
            }
        }
    }
}
