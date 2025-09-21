import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Juego {
    private final List<Niño> participantes;
    private final int duracion;
    public Juego(List<Niño> participantes, Random rnd) {
        this.participantes = new ArrayList<>(participantes);
        this.duracion = 5 + rnd.nextInt(11);
    }
    public int getDuracion() {
        return duracion;
    }
    public int getNumeroParticipantes() {
        return participantes.size();
    }
    @Override
    public String toString() {
        return "Juego{" +
                "participantes=" + participantes +
                ", duracion=" + duracion +
                '}';
    }
}