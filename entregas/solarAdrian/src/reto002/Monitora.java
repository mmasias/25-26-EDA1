package reto002;

import java.util.LinkedList;
import java.util.List;

public class Monitora {
    private String nombre;
    private LinkedList<Ninio> cola = new LinkedList<>();

    public Monitora(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public List<Ninio> getCola() { return cola; }

    public void agregarNinio(Ninio n) {
        cola.addLast(n);
    }

    public void transferirTodosA(Monitora otra) {
        while (!cola.isEmpty()) {
            otra.agregarNinio(cola.pollFirst());
        }
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int cantidad() {
        return cola.size();
    }

    public double edadPromedio() {
        if (cola.isEmpty()) return 0;
        int suma = 0;
        for (Ninio n : cola) {
            suma += n.getEdad();
        }
        return (double) suma / cola.size();
    }

    @Override
    public String toString() {
        String texto = nombre.toUpperCase() + ":\n";
        if (cola.isEmpty()) {
            texto = texto + "  Cola vacía\n";
        } else {
            texto = texto + "  Niños en cola: " + cola.size() + "\n";
            for (Ninio n : cola) {
                texto = texto + "  - " + n.toString() + "\n";
            }
        }
        return texto;
    }
}
