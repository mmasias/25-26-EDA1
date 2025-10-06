```java
import java.util.LinkedList;

public class Lidia {
    private String nombre;
    private LinkedList<Niño> cola;

    public Lidia() {
        this.nombre = "Lidia";
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

    public void transferirTodosA(Aisha aisha) {
        while (!cola.isEmpty()) {
            aisha.recibirNiño(cola.poll());
        }
    }

    public void pasarNiñosAAisha(Aisha aisha) {
        if (cola.size() >= 5) {
            System.out.println("Lidia transfiere sus niños a Aisha:");
            for (Niño n : cola) {
                System.out.println(" - " + n);
            }
            transferirTodosA(aisha);
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }
}
```


