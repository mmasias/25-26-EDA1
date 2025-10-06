import java.util.LinkedList;
import java.util.List;

public abstract class Monitora {
    protected String nombre;
    protected LinkedList<Niño> cola;
}
public Monitora(String nombre) {
    this.nombre = nombre;
    this.cola = new LinkedList<>();
}
public String getNombre() {
    return nombre;
}
public void recibirNiño(Niño n) {
    cola.add(n);
}
public List<Niño> getCola() {
    return cola;
}
