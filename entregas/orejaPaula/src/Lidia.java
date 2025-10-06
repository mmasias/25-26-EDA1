import java.util.LinkedList;

public class Lidia {
    private String nombre;
    private LinkedList<Niño> cola;

    public Lidia() {
        this.nombre = "Lidia";
        this.cola = new LinkedList<>();
    }
}
public void recibirNiño(Niño n) {
    cola.add(n);
}
public LinkedList<Niño> getCola() {
    return cola;
}

public int contarNiños() {
    return cola.size();
}

