public class GeneradorMensaje {
    private static final int LONGITUD_MENSAJE = 10;
    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generarMensaje() {
        char[] mensaje = new char[LONGITUD_MENSAJE];
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            int indice = aleatorio(0, ALFABETO.length() - 1);
            mensaje[i] = ALFABETO.charAt(indice);
        }
        return new String(mensaje);
    }

    private int aleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
}
