import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Juego {
    private final List<Niño> participantes;
    private final int duracion;
    private final int erroresTotales;
    private final String mensajeFinal;

    public Juego(List<Niño> participantes, Random rnd) {
        this.participantes = new ArrayList<>(participantes);
        this.duracion = participantes.size(); // Duración = número de turnos (niños)
        this.erroresTotales = calcularErroresTotales(participantes);
        this.mensajeFinal = participantes.isEmpty() ? "" : participantes.get(participantes.size() - 1).mostrarMensaje();
    }

    private int calcularErroresTotales(List<Niño> participantes) {
        int total = 0;
        for (Niño niño : participantes) {
            total += niño.getErroresIntroducidos();
        }
        return total;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getNumeroParticipantes() {
        return participantes.size();
    }

    public int getErroresTotales() {
        return erroresTotales;
    }

    public String getMensajeFinal() {
        return mensajeFinal;
    }

    @Override
    public String toString() {
        return "Juego [participantes=" + participantes.size() + ", duracion=" + duracion + ", errores=" + erroresTotales + ", final='" + mensajeFinal + "']";
    }
}