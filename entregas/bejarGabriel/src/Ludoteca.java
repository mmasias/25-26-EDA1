import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

class Ludoteca {
    private final int duracionSimulacion;
    private final GeneradorLlegadas generadorLlegadas;
    private final Estadisticas estadisticas;
    private final Random rnd;
    private final Queue<Niño> colaNiños = new LinkedList<>();
    public Ludoteca(int duracionSimulacion, GeneradorLlegadas generadorLlegadas, Estadisticas estadisticas, Random rnd) {
        this.duracionSimulacion = duracionSimulacion;
        this.generadorLlegadas = generadorLlegadas;
        this.estadisticas = estadisticas;
        this.rnd = rnd;
    }
    public void simular() {
        for (int minuto = 0; minuto < duracionSimulacion; minuto++) {
            int llegadas = generadorLlegadas.llegadasEnMinuto(minuto);
            for (int i = 0; i < llegadas; i++) {
                colaNiños.add(new Niño());
            }
            while (colaNiños.size() >= 3) {
                int participantes = 3 + rnd.nextInt(Math.min(3, colaNiños.size() - 2));
                List<Niño> grupo = new ArrayList<>();
                for (int i = 0; i < participantes; i++) {
                    grupo.add(colaNiños.poll());
                }
                Juego juego = new Juego(grupo, rnd);
                estadisticas.registrarJuego(juego);
            }
        }
    }
}