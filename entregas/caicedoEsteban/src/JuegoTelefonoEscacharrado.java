import java.util.Random;

public class JuegoTelefonoEscacharrado {
    private static final int INTERVALO_RAPIDO = 10;
    private static final int MAX_LLEGADAS = 2;
    private static final int DURACION_TOTAL = 120;
    private static final int MOD_LENTO = 3;
    private static final int TIEMPO_EXTRA = 2;
    private static final int LONGITUD_MENSAJE = 10;
    private static final int INTERVALO_LENTO = 30;
    private static final int MIN_JUGADORES = 6;

    private final ColaDeJugadores cola;
    private final Random random;
    private int minutoActual;
    private int contadorJugadores;

    public JuegoTelefonoEscacharrado() {
        this.cola = new ColaDeJugadores();
        this.random = new Random();
        this.minutoActual = 0;
        this.contadorJugadores = 1;
    }

    public void iniciar() {
        while (minutoActual < DURACION_TOTAL) {
            System.out.println("\nMinuto " + minutoActual + ":");

            generarLlegadas();

            System.out.println("Jugadores en la cola: " + cola);

            if (cola.tamaño() >= MIN_JUGADORES) {
                procesarRonda();
            } else {
                minutoActual++;
            }
        }
    }

    private void generarLlegadas() {
        if (minutoActual < INTERVALO_RAPIDO) {
            int llegadas = random.nextInt(MAX_LLEGADAS + 1);
            for (int i = 0; i < llegadas; i++) {
                añadirNuevoJugador();
            }
        } else if (minutoActual < INTERVALO_LENTO && minutoActual % MOD_LENTO == 0 && random.nextBoolean()) {
            añadirNuevoJugador();
        }
    }

    private void añadirNuevoJugador() {
        Jugador jugador = new Jugador("Jugador" + contadorJugadores++);
        cola.añadirJugador(jugador);
        System.out.println("Llega el jugador: " + jugador.getNombre());
    }

    private void procesarRonda() {
        limpiarPizarras();

        String mensaje = generarMensaje();
        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Mensaje final en la pizarra:");

        for (Jugador jugador : cola.obtenerJugadores()) {
            mensaje = jugador.escribirEnPizarra(mensaje);
            System.out.println(jugador);
        }

        minutoActual += cola.tamaño() + TIEMPO_EXTRA;
    }

    private void limpiarPizarras() {
        System.out.println("Limpiando pizarras...");
        for (Jugador jugador : cola.obtenerJugadores()) {
            jugador.limpiarPizarra();
        }
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(LONGITUD_MENSAJE);
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        return sb.toString();
    }
}
