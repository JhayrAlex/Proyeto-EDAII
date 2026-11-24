package ec.edu.epn.eda2.clasificacion;

import java.util.ArrayList;
import java.util.List;

/** Subset Sum por fuerza bruta: examina los 2ⁿ subconjuntos. */
public class SubsetSum {
    public long operaciones, soluciones;
    public List<Integer> primera;

    public void resolver(int[] a, int objetivo) {
        operaciones = 0; soluciones = 0; primera = null;
        int n = a.length;
        for (long mask = 1; mask < (1L << n); mask++) {
            operaciones++;
            long s = 0;
            for (int b = 0; b < n; b++) if (((mask >> b) & 1) == 1) s += a[b];
            if (s == objetivo) {
                soluciones++;
                if (primera == null) {
                    primera = new ArrayList<>();
                    for (int b = 0; b < n; b++) if (((mask >> b) & 1) == 1) primera.add(a[b]);
                }
            }
        }
    }
}
