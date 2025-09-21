import java.util.LinkedList;
import java.util.Random;

public class Ludoteca {

    private static final int MIN_PARTICIPANTES_JUEGO = 5;

    private final int duracionMinutos;
    private final GeneradorLlegadas generador;
    private final Estadisticas estadisticas;
    private final Random rnd;
    private final boolean imprimirDetalles;

    private final LinkedList<Niño> colaAisha = new LinkedList<>();
    private final LinkedList<Niño> conLydia = new LinkedList<>();

    public Ludoteca(int duracionMinutos, GeneradorLlegadas generador, Estadisticas estadisticas, Random rnd, boolean imprimirDetalles) {
        this.duracionMinutos = duracionMinutos;
        this.generador = generador;
        this.estadisticas = estadisticas;
        this.rnd = rnd;
        this.imprimirDetalles = imprimirDetalles;
    }

    public void simular() {
        boolean juegoEnCurso = false;
        int minutosRestantesJuego = 0;
        Juego juegoActual = null;

        for (int minutoActual = 0; minutoActual < duracionMinutos; minutoActual++) {
            if (imprimirDetalles) {
                System.out.println("\n--- Minuto " + minutoActual + " ---");
            }

            procesarLlegadas(minutoActual, juegoEnCurso);

            if (!juegoEnCurso && colaAisha.size() > MIN_PARTICIPANTES_JUEGO) {
                juegoActual = iniciarJuego();
                juegoEnCurso = true;
                minutosRestantesJuego = juegoActual.getDuracion();
            }

            if (juegoEnCurso) {
                minutosRestantesJuego--;
                if (minutosRestantesJuego == 0) {
                    finalizarJuego(juegoActual);
                    juegoEnCurso = false;
                    juegoActual = null;
                }
            }
        }

        if (imprimirDetalles) {
            System.out.println("\nSimulación finalizada.");
        }
    }

    private void procesarLlegadas(int minutoActual, boolean juegoEnCurso) {
        int llegadas = generador.llegadasEnMinuto(minutoActual);
        estadisticas.registrarLlegadas(llegadas);

        for (int i = 0; i < llegadas; i++) {
            Niño n = new Niño();
            if (juegoEnCurso) {
                conLydia.add(n);
                if (imprimirDetalles) {
                    System.out.println("Llegada: " + n + " -> con Lydia (juego en curso).");
                }
            } else {
                colaAisha.add(n);
                if (imprimirDetalles) {
                    System.out.println("Llegada: " + n + " -> a la cola de Aisha.");
                }
            }
        }
    }

    private Juego iniciarJuego() {
        if (imprimirDetalles) {
            System.out.println("Aisha inicia juego con " + colaAisha.size() + " participantes.");
        }
        Juego juego = new Juego(colaAisha, new Random(rnd.nextLong()));
        int duracion = juego.ejecutar(imprimirDetalles);
        estadisticas.registrarJuego(juego.getNumeroParticipantes(), juego.getDistanciaHamming());
        return juego;
    }

    private void finalizarJuego(Juego juego) {
        if (imprimirDetalles) {
            System.out.println("Juego finalizado. Lydia pasa " + conLydia.size() + " niños a la cola.");
        }
        colaAisha.addAll(conLydia);
        conLydia.clear();
    }
}
