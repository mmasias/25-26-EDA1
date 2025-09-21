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

