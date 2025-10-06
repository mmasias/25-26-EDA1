```java
import java.util.LinkedList;
import java.util.List;

public class Aisha {
    private String nombre;
    private LinkedList<Niño> cola;

    public Aisha() {
        this.nombre = "Aisha";
        this.cola = new LinkedList<>();
    }

    public void recibirNiño(Niño n) {
        cola.add(n);
    }

    public List<Niño> getCola() {
        return cola;
    }

    public void transferirTodosA(Aisha otra) {
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

    public void primerosCinco() {
        if (cola.isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        for (int i = 0; i < Math.min(5, cola.size()); i++) {
            Niño n = cola.get(i);
            System.out.println("Hola, soy " + n.getNombre());
        }
    }

    public void ultimosCinco() {
        if (cola.isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        int start = Math.max(cola.size() - 5, 0);
        for (int i = start; i < cola.size(); i++) {
            Niño n = cola.get(i);
            System.out.println("Hola, soy " + n.getNombre());
        }
    }

    public double edadPromedio() {
        if (cola.isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return 0;
        }
        double suma = 0;
        for (Niño n : cola) {
            suma += n.getEdad();
        }
        double promedio = suma / cola.size();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", promedio);
        return promedio;
    }

    public boolean verificarJuegoRana() {
        if (cola.isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return false;
        }
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = cola.size();
        int mayores5 = 0;
        for (Niño n : cola) {
            if (n.getEdad() >= 5) mayores5++;
        }
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores5);
        if (mayores5 > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
            return true;
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
            return false;
        }
    }

    public void separarParaJuego(Dalsy dalsy) {
        System.out.println("Separando niños para el juego de la rana...");
        for (int i = 0; i < cola.size(); i++) {
            Niño n = cola.get(i);
            if (n.getEdad() < 5) {
                dalsy.recibirNiño(n);
                System.out.println("- " + n + " pasa con Dalsy");
            }
        }
        cola.removeIf(n -> n.getEdad() < 5);
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (Niño n : cola) {
            System.out.println("- " + n);
        }
    }
}
```


