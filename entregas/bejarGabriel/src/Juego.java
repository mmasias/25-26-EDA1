import java.util.List;
import java.util.Random;

public class Juego {

    private final List<Niño> participantes;
    private final Random rnd;

    private int duracion;
    private int distanciaHamming;

    public Juego(List<Niño> participantes, Random rnd) {
        if (participantes == null || participantes.isEmpty()) {
            throw new IllegalArgumentException("La lista de participantes no puede estar vacía.");
        }
        this.participantes = participantes;
        this.rnd = rnd;
        this.duracion = 0;
        this.distanciaHamming = 0;
    }

    public int ejecutar(boolean imprimirDetalles) {
        duracion = 5 + rnd.nextInt(11);

        if (imprimirDetalles) {
            System.out.println("Juego iniciado con " + participantes.size() + " participantes.");
            System.out.println("Duración estimada: " + duracion + " minutos.");
        }

        for (int minuto = 1; minuto <= duracion; minuto++) {
            if (imprimirDetalles) {
                System.out.println("Minuto " + minuto + " del juego...");
            }
        }


        distanciaHamming = calcularDistanciaHamming();

        if (imprimirDetalles) {
            System.out.println("Distancia Hamming calculada: " + distanciaHamming);
        }

        return duracion;
    }

    private int calcularDistanciaHamming() {
        int distancia = 0;
        for (int i = 0; i < participantes.size() - 1; i++) {
            int id1 = participantes.get(i).getId();
            int id2 = participantes.get(i + 1).getId();
            distancia += Integer.bitCount(id1 ^ id2);
        }
        return distancia;
    }

    public int getNumeroParticipantes() {
        return participantes.size();
    }

    public int getDuracion() {
        return duracion;
    }

    public int getDistanciaHamming() {
        return distanciaHamming;
    }
}
