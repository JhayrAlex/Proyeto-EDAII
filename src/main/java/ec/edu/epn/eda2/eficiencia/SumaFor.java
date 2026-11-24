package ec.edu.epn.eda2.eficiencia;

/** Suma 1..n con un ciclo for: O(n) tiempo, O(1) espacio. */
public class SumaFor {
    private long operaciones;

    public long calcular(long n) {
        operaciones = 0;
        long suma = 0;
        for (long i = 1; i <= n; i++) {
            suma += i;
            operaciones++;
        }
        return suma;
    }

    public long getOperaciones() { return operaciones; }
}
