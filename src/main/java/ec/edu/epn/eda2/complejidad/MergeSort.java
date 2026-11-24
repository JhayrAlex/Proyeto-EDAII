package ec.edu.epn.eda2.complejidad;

/** O(n log n) en todos los casos (divide y vencerás). */
public class MergeSort {
    public long operaciones; // comparaciones

    public void ordenar(int[] a) {
        operaciones = 0;
        if (a.length < 2) return;
        ordenar(a, new int[a.length], 0, a.length - 1);
    }

    private void ordenar(int[] a, int[] t, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        ordenar(a, t, lo, mid);
        ordenar(a, t, mid + 1, hi);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            operaciones++;
            t[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }
        while (i <= mid) t[k++] = a[i++];
        while (j <= hi) t[k++] = a[j++];
        for (k = lo; k <= hi; k++) a[k] = t[k];
    }
}
