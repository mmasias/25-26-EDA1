import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Cola {
    private LinkedList<Niño> lista = new LinkedList<>();

    public void addNiño(Niño niño) {
        lista.addLast(niño);
    }

    public Niño removeNiño() {
        if (lista.isEmpty()) return null;
        return lista.removeFirst();
    }

    public Niño removeLast() {
        if (lista.isEmpty()) return null;
        return lista.removeLast();
    }

    public void limpiar() {
        lista.clear();
    }

    public boolean hayNiños() {
        return !lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }

    public void listaNiños() {
        for (Niño niño : lista) {
            new Console().write(niño.getNombre() + " / ");
        }
        new Console().writeln();
    }

    public Niño getNiño(int posicion) {
        if (posicion < 0 || posicion >= size()) {
            return null;
        }
        return lista.get(posicion);
    }

    public List<Niño> getAll() {
        return new ArrayList<>(lista);
    }
}