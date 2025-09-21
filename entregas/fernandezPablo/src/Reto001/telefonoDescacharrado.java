import java.util.LinkedList;
import java.util.Queue;

public class telefonoDescacharrado {
    private Queue<Niño> niños;
    private int capacidad;

    public telefonoDescacharrado(int capacidad) {
        this.capacidad = capacidad;
        this.niños = new LinkedList<>();
    }

    public void agregarNiño(Niño n) {
        if (niños.size() < capacidad) {
            niños.add(n);
            System.out.println(n.getNombre() + " ha llegado al juego.");
        } else {
            System.out.println("No caben más niños en el juego.");
        }
    }

    public void jugarVariasRondas(int numRondas) {
        for (int ronda = 1; ronda <= numRondas; ronda++) {
            System.out.println("\n=== Ronda " + ronda + " ===");

            if (niños.isEmpty()) {
                System.out.println("No hay niños para jugar.");
                return;
            }

            Mensaje mensaje = new Mensaje();
            System.out.println("Mensaje inicial: " + mensaje.getContenido());

            String transformado = mensaje.getContenido();

            for (Niño n : niños) {
                transformado = n.transformar(transformado);
                System.out.println(n.getNombre() + " lo transforma en: " + transformado);
            }

            System.out.println("Mensaje final de la ronda: " + transformado);

            Niño primero = niños.poll();
            if (primero != null) {
                niños.add(primero);
                System.out.println(primero.getNombre() + " se mueve al final de la fila.");
            }
        }
    }
}

