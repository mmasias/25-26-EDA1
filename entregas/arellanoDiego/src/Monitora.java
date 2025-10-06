

import java.util.LinkedList;

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

    public LinkedList<Niño> getCola() {
        return cola;
    }

    public void agregarNiño(Niño niño) {
        cola.add(niño);
    }

    public void transferirNiñosA(Monitora destino) {
        destino.getCola().addAll(this.cola);
        this.cola.clear();
    }

    public void mostrarCola() {
        if (cola.isEmpty()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cola.size());
            for (Niño n : cola) {
                System.out.println("  - " + n);
            }
        }
    }
}
