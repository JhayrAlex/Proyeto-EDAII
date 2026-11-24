package ec.edu.epn.eda2.clasificacion;

/** Verifica en tiempo polinómico (O(n·k)) un certificado de Subset Sum. */
public class VerificadorNP {
    public long operaciones;
    public String mensaje;

    public boolean verificar(int[] conjunto, int objetivo, int[] certificado) {
        operaciones = 0;
        boolean[] usado = new boolean[conjunto.length];
        long suma = 0;
        for (int c : certificado) {
            boolean hallado = false;
            for (int i = 0; i < conjunto.length; i++) {
                operaciones++;
                if (!usado[i] && conjunto[i] == c) { usado[i] = true; hallado = true; break; }
            }
            if (!hallado) {
                mensaje = "El valor " + c + " no pertenece al conjunto (o se usa más veces de las disponibles).";
                return false;
            }
            suma += c;
        }
        if (suma == objetivo) { mensaje = "Certificado VÁLIDO: la suma es " + suma + " = objetivo."; return true; }
        mensaje = "Certificado INVÁLIDO: la suma es " + suma + " ≠ " + objetivo + ".";
        return false;
    }
}
