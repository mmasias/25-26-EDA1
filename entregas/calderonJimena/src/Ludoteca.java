public class Ludoteca {
    private static final int TIEMPO_TOTAL = 120;
    private static final int TIEMPO_LLEGADAS_RAPIDAS = 10;
    private static final int TIEMPO_LLEGADAS_TARDIAS = 30;
    private static final int LLEGADAS_RAPIDAS_MIN = 0;
    private static final int LLEGADAS_RAPIDAS_MAX = 2;
    private static final int MODULO_LLEGADA_TARDIA = 3;

    private int tiempo;
    private int contadorNino;
    private Juego juego;

    public Ludoteca() {
        tiempo = 0;
        contadorNino = 1;
        juego = new Juego();
    }

    public void iniciar() {
        System.out.println("Iniciando simulación con cola circular (encolar/desencolar)...");
        System.out.println("Inicia la ludoteca (simulación 120 minutos)");

        while (tiempo < TIEMPO_TOTAL) {
            System.out.println("----- Minuto " + tiempo / 60 + ":" + String.format("%02d", tiempo % 60) + " -----");
            llegadaNinos();
            juego.avanzarTiempo();
            tiempo++;
        }
    }

    private void llegadaNinos() {
        int cantidad = 0;
        if (tiempo < TIEMPO_LLEGADAS_RAPIDAS) {
            cantidad = aleatorio(LLEGADAS_RAPIDAS_MIN, LLEGADAS_RAPIDAS_MAX);
        } else if (tiempo < TIEMPO_LLEGADAS_TARDIAS) {
            if (tiempo % MODULO_LLEGADA_TARDIA == 0) {
                if (aleatorio(0, 1) == 1) cantidad = 1;
            }
        }

        for (int i = 0; i < cantidad; i++) {
            Nino nino = new Nino("Niño" + contadorNino, tiempo);
            System.out.println("Llega " + nino.getNombre() + " (llegó en minuto " + tiempo + ") a Lydia (fase 1) en " + tiempo / 60 + ":" + String.format("%02d", tiempo % 60));
            System.out.println("Aisha agrega " + nino.getNombre() + " a la fila");
            juego.recibirNino(nino);
            contadorNino++;
        }

        juego.imprimirEstado();
    }

    private int aleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
}
