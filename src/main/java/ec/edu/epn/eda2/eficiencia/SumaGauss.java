package ec.edu.epn.eda2.eficiencia;

/** Suma 1..n con la fórmula de Gauss: O(1) tiempo, O(1) espacio. */
public class SumaGauss {
    private long operaciones;

    public long calcular(long n) {
        operaciones = 3; // n + 1, n * (n + 1), / 2
        return n * (n + 1) / 2;
    }

    public long getOperaciones() { return operaciones; }
}
