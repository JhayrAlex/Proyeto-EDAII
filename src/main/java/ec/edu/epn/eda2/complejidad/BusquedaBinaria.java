package ec.edu.epn.eda2.complejidad;

/** O(log n): requiere arreglo ordenado; descarta la mitad en cada paso. */
public class BusquedaBinaria {
    public long operaciones;

    public int buscar(int[] a, int x) {
        operaciones = 0;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            operaciones++;
            int mid = (lo + hi) >>> 1;
            if (a[mid] == x) return mid;
            if (a[mid] < x) lo = mid + 1; else hi = mid - 1;
        }
        return -1;
    }
}
