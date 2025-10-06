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
public void presentarseMayoresDe(int edadMinima) {
    if (cola.isEmpty()) {
        System.out.println("No hay niños en la cola de Aisha");
        return;
    }
    System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
    for (Niño n : cola) {
        if (n.getEdad() > edadMinima) {
            System.out.println(n.presentarse());
        }
    }
}
public void presentarsePorInicial(char letra) {
    if (cola.isEmpty()) {
        System.out.println("No hay niños en la cola de Aisha");
        return;
    }
    System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
    for (Niño n : cola) {
        if (Character.toUpperCase(n.getNombre().charAt(0)) == Character.toUpperCase(letra)) {
            System.out.println("Hola, soy " + n.getNombre());
        }
    }
}


