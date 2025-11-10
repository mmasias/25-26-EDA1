import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Monitora {
    private String nombre;
    private LinkedList<Niño> cola;

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.cola = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int contarNiños() {
        return cola.size();
    }

    public List<Niño> listarNiños() {
        return new LinkedList<>(cola);
    }

    public void agregarNiño(Niño niño) {
        cola.addLast(niño);
    }

    public void agregarTodos(List<Niño> niños) {
        cola.addAll(niños);
    }

    public void transferirNiñosA(Monitora destino) {
        while (!cola.isEmpty()) {
            destino.agregarNiño(cola.removeFirst());
        }
    }

    public int contarNiñosMayoresOIgualesA(int edadMinima) {
        int contador = 0;
        for (Niño niño : cola) {
            if (niño.getEdad() >= edadMinima) {
                contador++;
            }
        }
        return contador;
    }

    public List<Niño> retirarNiñosMenoresQue(int edadLimite) {
        LinkedList<Niño> retirados = new LinkedList<>();
        Iterator<Niño> iterador = cola.iterator();
        while (iterador.hasNext()) {
            Niño niño = iterador.next();
            if (niño.getEdad() < edadLimite) {
                retirados.add(niño);
                iterador.remove();
            }
        }
        return retirados;
    }

    public void mostrarCola() {
        if (cola.isEmpty()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cola.size());
            for (Niño niño : cola) {
                System.out.println("  - " + niño);
            }
        }
    }
}
