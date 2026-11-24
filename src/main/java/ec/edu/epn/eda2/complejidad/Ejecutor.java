package ec.edu.epn.eda2.complejidad;

import ec.edu.epn.eda2.util.MedidorTiempo;
import ec.edu.epn.eda2.util.Resultado;
import java.util.Random;

/** Ejecuta el algoritmo de cada clase de complejidad y devuelve operaciones y tiempo. */
public class Ejecutor {

    public static final String[] NOMBRES = {
        "O(1) – Acceso a un arreglo", "O(log n) – Búsqueda binaria", "O(n) – Búsqueda lineal",
        "O(n log n) – Merge Sort", "O(n²) – Bubble Sort",
        "O(2ⁿ) – Generación de subconjuntos", "O(n!) – Generación de permutaciones"};
    public static final String[] TEORICA = {"O(1)", "O(log n)", "O(n)", "O(n log n)", "O(n²)", "O(2ⁿ)", "O(n!)"};
    public static final int[] MAXIMOS = {10_000_000, 10_000_000, 10_000_000, 1_000_000, 20_000, 22, 10};
    public static final int[] SUGERIDOS = {1_000, 1_000_000, 1_000_000, 100_000, 5_000, 15, 8};
    public static final String[] INTERPRETACION = {
        "El costo no depende de n: una sola operación, sin importar el tamaño.",
        "Duplicar n solo agrega UNA comparación más. Requiere datos ordenados.",
        "Las operaciones crecen proporcionalmente a n (peor caso: el valor no está).",
        "Muy eficiente para ordenar; crece un poco más rápido que n.",
        "Al multiplicar n por 10, las operaciones se multiplican por ~100.",
        "Cada elemento nuevo DUPLICA el trabajo: inviable para n grandes.",
        "Crece más rápido que cualquier exponencial: inviable más allá de n≈12."};

    public static Resultado ejecutar(int tipo, int n) {
        MedidorTiempo m = new MedidorTiempo();
        Resultado r = new Resultado();
        switch (tipo) {
            case 0 -> {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) a[i] = i;
                AccesoArreglo x = new AccesoArreglo();
                m.iniciar(); int v = x.acceder(a, n / 2); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = "a[" + (n / 2) + "] = " + v;
            }
            case 1 -> {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) a[i] = 2 * i;
                BusquedaBinaria x = new BusquedaBinaria();
                m.iniciar(); int p = x.buscar(a, 2 * n - 1); r.nanos = m.detener(); // impar: no existe (peor caso)
                r.operaciones = x.operaciones;
                r.detalle = p < 0 ? "Valor no encontrado (peor caso)" : "Encontrado en " + p;
            }
            case 2 -> {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) a[i] = i;
                BusquedaLineal x = new BusquedaLineal();
                m.iniciar(); int p = x.buscar(a, -1); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = p < 0 ? "Valor no encontrado (peor caso)" : "Encontrado en " + p;
            }
            case 3 -> {
                int[] a = aleatorio(n);
                MergeSort x = new MergeSort();
                m.iniciar(); x.ordenar(a); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = "Arreglo de " + n + " enteros ordenado. " + muestra(a);
            }
            case 4 -> {
                int[] a = aleatorio(n);
                BubbleSort x = new BubbleSort();
                m.iniciar(); x.ordenar(a); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = "Arreglo de " + n + " enteros ordenado. " + muestra(a);
            }
            case 5 -> {
                Subconjuntos x = new Subconjuntos();
                m.iniciar(); x.generar(n); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = "Se generaron 2^" + n + " = " + x.operaciones + " subconjuntos. " + x.ejemplo;
            }
            default -> {
                Permutaciones x = new Permutaciones();
                m.iniciar(); x.generar(n); r.nanos = m.detener();
                r.operaciones = x.operaciones;
                r.detalle = "Se generaron " + n + "! = " + x.operaciones + " permutaciones. " + x.ejemplo;
            }
        }
        return r;
    }

    private static int[] aleatorio(int n) {
        Random rnd = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1_000_000);
        return a;
    }

    private static String muestra(int[] a) {
        StringBuilder sb = new StringBuilder("Primeros valores: ");
        for (int i = 0; i < Math.min(8, a.length); i++) sb.append(a[i]).append(' ');
        return sb.toString();
    }
}
