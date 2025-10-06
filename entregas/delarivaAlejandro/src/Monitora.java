import java.util.*;

public class Monitora {
    private final String nombre;
    private final Deque<Ninio> cola = new ArrayDeque<>();

    public Monitora(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int tamanoCola() {
        return cola.size();
    }

    public void encolar(Ninio ninio) {
        cola.addLast(ninio);
    }

    public Ninio desencolar() {
        return cola.pollFirst();
    }

    public List<Ninio> verCola() {
        return new ArrayList<>(cola);
    }

    public List<Ninio> drenarTodoPreservandoOrden() {
        List<Ninio> lista = new ArrayList<>(cola);
        cola.clear();
        return lista;
    }

    public void recibirListaPreservandoOrden(List<Ninio> lista) {
        for (Ninio n : lista) cola.addLast(n);
    }

    public double edadPromedio() {
        if (cola.isEmpty()) return 0.0;
        long suma = 0;
        for (Ninio n : cola) suma += n.getEdad();
        return (double) suma / cola.size();
    }

    public long contarMayoresOIguales(int edadMin) {
        return cola.stream().filter(n -> n.getEdad() >= edadMin).count();
    }
}
