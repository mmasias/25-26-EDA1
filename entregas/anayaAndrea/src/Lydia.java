import java.util.ArrayList;

public class Lydia {
    private ArrayList<Nino> salaEspera;

    public Lydia() {
        salaEspera = new ArrayList<>();
    }

    public void recibirNino(Nino nino) {
        salaEspera.add(nino);
    }

    public ArrayList<Nino> pasarNinos() {
        ArrayList<Nino> copia = new ArrayList<>(salaEspera);
        salaEspera.clear();
        return copia;
    }

    public boolean tieneNinos() {
        return !salaEspera.isEmpty();
    }
}