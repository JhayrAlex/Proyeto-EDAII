package ec.edu.epn.eda2.complejidad;

/** O(n²) en el peor caso y caso promedio; O(n) en el mejor caso (con bandera de intercambio). */
public class BubbleSort {
    public long operaciones; // comparaciones

    public void ordenar(int[] a) {
        operaciones = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean cambio = false;
            for (int j = 0; j < n - 1 - i; j++) {
                operaciones++;
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    cambio = true;
                }
            }
            if (!cambio) break;
        }
    }
}
