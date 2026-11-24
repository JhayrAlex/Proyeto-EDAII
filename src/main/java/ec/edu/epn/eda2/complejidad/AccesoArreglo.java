package ec.edu.epn.eda2.complejidad;

/** O(1): acceso directo por índice. */
public class AccesoArreglo {
    public long operaciones;

    public int acceder(int[] a, int i) {
        operaciones = 1;
        return a[i];
    }
}
