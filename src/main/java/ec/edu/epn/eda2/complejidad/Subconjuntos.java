package ec.edu.epn.eda2.complejidad;

/** O(2ⁿ): genera los 2ⁿ subconjuntos de {1..n} mediante máscaras de bits. */
public class Subconjuntos {
    public long operaciones;
    public long acumulado; // evita que el trabajo sea eliminado por el compilador
    public String ejemplo = "";

    public void generar(int n) {
        operaciones = 0;
        acumulado = 0;
        StringBuilder sb = new StringBuilder();
        for (long mask = 0; mask < (1L << n); mask++) {
            operaciones++;
            acumulado += Long.bitCount(mask);
            if (n <= 3) {
                sb.append('{');
                boolean primero = true;
                for (int b = 0; b < n; b++) {
                    if (((mask >> b) & 1) == 1) {
                        if (!primero) sb.append(',');
                        sb.append(b + 1);
                        primero = false;
                    }
                }
                sb.append("} ");
            }
        }
        ejemplo = sb.toString();
    }
}
