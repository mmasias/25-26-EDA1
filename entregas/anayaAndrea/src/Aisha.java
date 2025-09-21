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


}
