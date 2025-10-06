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
public void transferirTodosA(Monitora otra) {
    while (!cola.isEmpty()) {
        otra.recibirNiño(cola.poll());
    }
}
public int contarNiños() {
    return cola.size();
}
public void mostrarCola() {
    if (cola.isEmpty()) {
        System.out.println("  Cola vacía");
    } else {
        System.out.println("  Niños en cola: " + cola.size());
        for (Niño n : cola) {
            System.out.println("   - " + n);
        }
    }
}
