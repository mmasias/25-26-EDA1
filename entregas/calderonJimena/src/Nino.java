public class Nino {
    private static final int CAMBIOS_MIN = 0;
    private static final int CAMBIOS_MAX = 2;
    private static final int RANGO_LETRAS = 25;
    private static final char PRIMERA_LETRA = 'A';

    private String nombre;
    private int minutoLlegada;

    public Nino(String nombre, int minutoLlegada) {
        this.nombre = nombre;
        this.minutoLlegada = minutoLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    public String recibir(String mensaje) {
        char[] letras = mensaje.toCharArray();
        int cambios = aleatorio(CAMBIOS_MIN, CAMBIOS_MAX);
        for (int i = 0; i < cambios; i++) {
            int pos = aleatorio(0, letras.length - 1);
            letras[pos] = (char) (PRIMERA_LETRA + aleatorio(0, RANGO_LETRAS));
        }
        return new String(letras);
    }

    private int aleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
}
