import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class ColaDeJugadores {
    private final Queue<Jugador> cola;

    public ColaDeJugadores() {
        cola = new ArrayDeque<>();
    }

    public void añadirJugador(Jugador jugador) {
        cola.add(jugador);
    }

    public Jugador[] obtenerJugadores() {
        return cola.toArray(new Jugador[0]);
    }

    public int tamaño() {
        return cola.size();
    }

    public void limpiar() {
        cola.clear();
    }

    @Override
    public String toString() {
        if (cola.isEmpty())
            return "Cola vacía";
        return cola.stream()
                .map(Jugador::getNombre)
                .collect(Collectors.joining(", "));
    }
}
