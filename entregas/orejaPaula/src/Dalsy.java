```java
import java.util.LinkedList;

public class Dalsy {
    private String nombre;
    private LinkedList<Niño> cola;

    public Dalsy() {
        this.nombre = "Dalsy";
        this.cola = new LinkedList<>();
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

    public void transferirTodosA(Lidia lidia) {
        while (!cola.isEmpty()) {
            lidia.recibirNiño(cola.poll());
        }
    }
}
```

