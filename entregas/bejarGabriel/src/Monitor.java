import java.io.Console;
import java.util.ArrayList;
import java.util.List;

class Monitor {
    private final String nombre;
    private final Cola colaNiños;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
    }

    public void recibeNiño(Niño niño) {
        if (colaNiños.size() < 15) {
            colaNiños.addNiño(niño);
        } else {
            new Console().writeln("ERROR: Cola de " + nombre + " llena, niño " + niño.getNombre() + " rechazado");
        }
    }

    public void recibeNiñoEmergencia(Niño niño) {
        colaNiños.addNiño(niño);
    }

    public Niño removeLastNiño() {
        return colaNiños.removeLast();
    }

    public List<Niño> entregaNiños(Monitor otro) {
        List<Niño> trans = new ArrayList<>();
        while (tieneNiños() && otro.size() < 15) {
            Niño unNiño = colaNiños.removeNiño();
            if (unNiño != null) {
                otro.recibeNiño(unNiño);
                trans.add(unNiño);
            }
        }
        return trans;
    }

    public List<Niño> transferirTodosA(Monitor target) {
        List<Niño> trans = new ArrayList<>();
        while (tieneNiños()) {
            Niño n = colaNiños.removeNiño();
            if (n != null) {
                target.recibeNiñoEmergencia(n);
                trans.add(n);
            }
        }
        return trans;
    }

    public boolean tieneNiños() {
        return colaNiños.hayNiños();
    }

    public int size() {
        return colaNiños.size();
    }

    public Niño getNiño(int pos) {
        return colaNiños.getNiño(pos);
    }

    public List<Niño> getAllNiños() {
        return colaNiños.getAll();
    }

    public void vaciarCola() {
        colaNiños.limpiar();
    }

    public void mostrarListaNiños() {
        new Console().write("> " + this.nombre + " --> ");
        colaNiños.listaNiños();
    }
}