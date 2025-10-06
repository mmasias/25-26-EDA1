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

    public void agregarNinio(Ninio n) {
        cola.addLast(n);
    }

    public void transferirTodosA(Monitora destino) {
        destino.cola.addAll(this.cola);
        this.cola.clear();
    }

    public int cantidad() {
        return cola.size();
    }

    public List<Ninio> getCola() {
        return cola;
    }

    @Override
    public String toString() {
        String salida = nombre.toUpperCase() + ":\n";
        if (cola.isEmpty()) {
            salida += "  Cola vacía\n";
        } else {
            salida += "  Niños en cola: " + cola.size() + "\n";
            for (Ninio n : cola) {
                salida += "  - " + n.toString() + "\n";
            }
        }
        return salida;
    }
}
