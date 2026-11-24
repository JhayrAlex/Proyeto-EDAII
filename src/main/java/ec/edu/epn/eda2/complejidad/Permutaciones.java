package ec.edu.epn.eda2.complejidad;

import java.util.Arrays;

/** O(n!): genera todas las permutaciones de {1..n} por intercambios recursivos. */
public class Permutaciones {
    public long operaciones;
    public String ejemplo = "";
    private final StringBuilder sb = new StringBuilder();

    public void generar(int n) {
        operaciones = 0;
        sb.setLength(0);
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i + 1;
        permutar(p, 0, n <= 3);
        ejemplo = sb.toString();
    }

    private void permutar(int[] p, int k, boolean guardar) {
        if (k == p.length) {
            operaciones++;
            if (guardar) sb.append(Arrays.toString(p)).append(' ');
            return;
        }
        for (int i = k; i < p.length; i++) {
            int t = p[k]; p[k] = p[i]; p[i] = t;
            permutar(p, k + 1, guardar);
            t = p[k]; p[k] = p[i]; p[i] = t;
        }
    }
}
