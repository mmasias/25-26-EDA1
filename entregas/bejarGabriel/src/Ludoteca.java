import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Ludoteca {
    private final int duracionMinutos;
    private final Random rnd;
    private final GeneradorLlegadas generador;
    private final Queue<Niño> colaAisha = new LinkedList<>();
    private final List<Niño> conLydia = new ArrayList<>();
    private final Estadisticas estadisticas = new Estadisticas();
    private final boolean imprimirDetalles;

    private int minutoActual = 0;

    public Ludoteca(int duracionMinutos, long seed, boolean imprimirDetalles) {
        this.duracionMinutos = duracionMinutos;
        this.rnd = new Random(seed);
        this.generador = new GeneradorLlegadas(rnd);
        this.imprimirDetalles = imprimirDetalles;
    }

    public Estadisticas getEstadisticas() { return estadisticas; }

    public void simular() {
        boolean juegoEnCurso = false;
        int minutosRestantesJuego = 0;

        for (minutoActual = 0; minutoActual < duracionMinutos; minutoActual++) {
            if (imprimirDetalles) System.out.println("\n--- Minuto " + minutoActual + " ---");

            int llegadas = generador.llegadasEnMinuto(minutoActual);
            estadisticas.registrarLlegadas(llegadas);

            for (int i = 0; i < llegadas; i++) {
                Niño n = new Niño();
                if (juegoEnCurso) {
                    conLydia.add(n);
                    if (imprimirDetalles) System.out.println("Llegada: " + n + " -> con Lydia (juego en curso).");
                } else {
                    colaAisha.add(n);
                    if (imprimirDetalles) System.out.println("Llegada: " + n + " -> a la cola de Aisha.");
                }
            }

            if (!juegoEnCurso && colaAisha.size() > 5) {
                if (imprimirDetalles) System.out.println("Aisha inicia juego con " + colaAisha.size() + " participantes.");
                Juego juego = new Juego(colaAisha, new Random(rnd.nextLong()));
                int duracionJuego = juego.ejecutar(imprimirDetalles);

                estadisticas.registrarJuego(juego.getNumeroParticipantes(), juego.getDistanciaHamming());

                juegoEnCurso = true;
                minutosRestantesJuego = duracionJuego;

                while (minutosRestantesJuego > 0) {
                    minutoActual++;
                    minutosRestantesJuego--;

                    if (minutoActual >= duracionMinutos) break;

                    if (imprimirDetalles) System.out.println("\n--- Minuto " + minutoActual + " (juego en curso) ---");
                    int lleg = generador.llegadasEnMinuto(minutoActual);
                    estadisticas.registrarLlegadas(lleg);
                    for (int i = 0; i < lleg; i++) {
                        Niño n = new Niño();
                        conLydia.add(n);
                        if (imprimirDetalles) System.out.println("Llegada: " + n + " -> con Lydia (juego en curso).");
                    }
                }

                if (imprimirDetalles) System.out.println("Juego finalizado. Lydia pasa " + conLydia.size() + " niños a la cola.");
                colaAisha.addAll(conLydia);
                conLydia.clear();
                juegoEnCurso = false;

                if (minutoActual >= duracionMinutos - 1) break;
            }
        }
        if (imprimirDetalles) System.out.println("\nSimulación finalizada.");
    }
}
