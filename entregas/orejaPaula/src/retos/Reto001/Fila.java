import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<Nino> cola;

    public Fila() {
        cola = new ArrayList<>();
    }
}
public void agregarNino(Nino nino) {
    cola.add(nino);
}
public List<Nino> obtenerCola() {
    return cola;
}
public int tamano() {
    return cola.size();
}

