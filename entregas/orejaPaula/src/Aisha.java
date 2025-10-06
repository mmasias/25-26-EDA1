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
public void transferirTodosA(Aisha otra) {
    while (!cola.isEmpty()) {
        otra.recibirNiño(cola.poll());
    }
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
public void presentarseConNiños() {
    if (cola.isEmpty()) {
        System.out.println("No hay niños en la cola de Aisha");
        return;
    }
    System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
    for (Niño n : cola) {
        System.out.println(n.presentarse());
    }
}


