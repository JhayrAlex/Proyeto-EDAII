package ec.edu.epn.eda2.eficiencia;

import ec.edu.epn.eda2.util.MedidorTiempo;

/** Compara ambos métodos repitiendo la ejecución para obtener tiempos promedio. */
public class Comparador {

    public static class Comparacion {
        public long resultadoFor, resultadoGauss, opsFor, opsGauss;
        public double promFor, promGauss;
        public int repeticiones;
        public boolean coinciden() { return resultadoFor == resultadoGauss; }
    }

    public Comparacion comparar(long n, int repeticiones) {
        SumaFor f = new SumaFor();
        SumaGauss g = new SumaGauss();
        Comparacion c = new Comparacion();
        c.repeticiones = repeticiones;
        MedidorTiempo m = new MedidorTiempo();
        long tf = 0, tg = 0;
        for (int i = 0; i < repeticiones; i++) {
            m.iniciar(); c.resultadoFor = f.calcular(n); tf += m.detener();
            m.iniciar(); c.resultadoGauss = g.calcular(n); tg += m.detener();
        }
        c.promFor = tf / (double) repeticiones;
        c.promGauss = tg / (double) repeticiones;
        c.opsFor = f.getOperaciones();
        c.opsGauss = g.getOperaciones();
        return c;
    }
}
