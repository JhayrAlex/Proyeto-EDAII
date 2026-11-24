package ec.edu.epn.eda2.complejidad;

/** O(n): recorre el arreglo hasta encontrar el valor. */
public class BusquedaLineal {
    public long operaciones;

    public int buscar(int[] a, int x) {
        operaciones = 0;
        for (int i = 0; i < a.length; i++) {
            operaciones++;
            if (a[i] == x) return i;
        }
        return -1;
    }
}
