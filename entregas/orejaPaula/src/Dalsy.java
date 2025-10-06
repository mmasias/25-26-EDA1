import java.util.LinkedList;

public class Dalsy {
    private String nombre;
    private LinkedList<Niño> cola;

    public Dalsy() {
        this.nombre = "Dalsy";
        this.cola = new LinkedList<>();
    }
}
public void recibirNiño(Niño n) {
    cola.add(n);
}
