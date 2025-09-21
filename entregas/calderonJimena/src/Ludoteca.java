public class Ludoteca {
    private static final int TIEMPO_TOTAL = 120;
    private static final int TIEMPO_LLEGADAS_RAPIDAS = 10;
    private static final int TIEMPO_LLEGADAS_TARDIAS = 30;
    private static final int LLEGADAS_RAPIDAS_MIN = 0;
    private static final int LLEGADAS_RAPIDAS_MAX = 2;
    private static final int PROBABILIDAD_LLEGADA = 1; 
    private static final int SIN_LLEGADA = 0;
    private static final int MODULO_LLEGADA_TARDIA = 3;

    private int tiempo;
    private Juego juego;

    public Ludoteca() {
        tiempo = 0;
        juego = new Juego();
    }

    public void iniciar() {
        while (tiempo < TIEMPO_TOTAL) {
            llegadaNinos();
            juego.avanzarTiempo();
            tiempo++;
        }
    }

    private void llegadaNinos() {
        if (tiempo < TIEMPO_LLEGADAS_RAPIDAS) {
            int cantidad = aleatorio(LLEGADAS_RAPIDAS_MIN, LLEGADAS_RAPIDAS_MAX);
            for (int i = 0; i < cantidad; i++) {
                juego.recibirNino(new Nino());
            }
        } else if (tiempo < TIEMPO_LLEGADAS_TARDIAS) {
            if (tiempo % MODULO_LLEGADA_TARDIA == 0 && aleatorio(SIN_LLEGADA, PROBABILIDAD_LLEGADA) == PROBABILIDAD_LLEGADA) {
                juego.recibirNino(new Nino());
            }
        }
    }

    private int aleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
}
