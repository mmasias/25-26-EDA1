import java.util.ArrayList;

public class Aisha {
    private ArrayList<Nino> fila;
    private boolean jugando;

    public Aisha() {
        fila = new ArrayList<>();
        jugando = false;
    }

    public void recibirNinos(ArrayList<Nino> nuevos) {
        fila.addAll(nuevos);
    }

    public boolean puedeJugar() {
        return fila.size() > 5;
    }

    public ArrayList<Nino> getFila() {
        jugando = true;
        return fila;
    }

    public void vaciarFila() {
        fila.clear();
        jugando = false;
    }

    public boolean estaJugando() {
        return jugando;
    }
}
