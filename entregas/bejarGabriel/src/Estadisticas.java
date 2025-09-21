import java.util.ArrayList;
import java.util.List;

public class Estadisticas {

    private final List<Integer> llegadasPorMinuto;
    private final List<Integer> participantesPorJuego;
    private final List<Integer> distanciasHammingPorJuego;

    public Estadisticas() {
        llegadasPorMinuto = new ArrayList<>();
        participantesPorJuego = new ArrayList<>();
        distanciasHammingPorJuego = new ArrayList<>();
    }

    public void registrarLlegadas(int cantidad) {
        llegadasPorMinuto.add(cantidad);
    }

    public void registrarJuego(int numParticipantes, int distanciaHamming) {
        participantesPorJuego.add(numParticipantes);
        distanciasHammingPorJuego.add(distanciaHamming);
    }

    public int getTotalLlegadas() {
        return llegadasPorMinuto.stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalJuegos() {
        return participantesPorJuego.size();
    }

    public double getMediaParticipantesPorJuego() {
        if (participantesPorJuego.isEmpty()) return 0.0;
        return participantesPorJuego.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public double getMediaDistanciaHamming() {
        if (distanciasHammingPorJuego.isEmpty()) return 0.0;
        return distanciasHammingPorJuego.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public void imprimirResumen() {
        System.out.println("----- Resumen de Estadísticas -----");
        System.out.println("Total de llegadas: " + getTotalLlegadas());
        System.out.println("Total de juegos realizados: " + getTotalJuegos());
        System.out.printf("Media de participantes por juego: %.2f%n", getMediaParticipantesPorJuego());
        System.out.printf("Media de distancia Hamming por juego: %.2f%n", getMediaDistanciaHamming());
        System.out.println("-----------------------------------");
    }

    public void reset() {
        llegadasPorMinuto.clear();
        participantesPorJuego.clear();
        distanciasHammingPorJuego.clear();
    }
}
