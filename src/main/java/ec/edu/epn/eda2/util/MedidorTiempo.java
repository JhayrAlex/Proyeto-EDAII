package ec.edu.epn.eda2.util;

/** Mide tiempos de ejecución con System.nanoTime(). */
public class MedidorTiempo {
    private long inicio;

    public void iniciar() { inicio = System.nanoTime(); }

    public long detener() { return System.nanoTime() - inicio; }

    public static String formato(long ns) {
        if (ns < 1_000) return ns + " ns";
        if (ns < 1_000_000) return String.format("%.2f µs", ns / 1e3);
        if (ns < 1_000_000_000L) return String.format("%.2f ms", ns / 1e6);
        return String.format("%.2f s", ns / 1e9);
    }
}
