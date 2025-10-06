import java.util.LinkedList;
import java.util.List;

public class Aisha {
    private String nombre;
    private LinkedList<Niño> cola;

    public Aisha() {
        this.nombre = "Aisha";
        this.cola = new LinkedList<>();
    }
}
public void recibirNiño(Niño n) {
    cola.add(n);
}

public List<Niño> getCola() {
    return cola;
}

public int contarNiños() {
    return cola.size();
}


