import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Cocina {

    private PriorityQueue<Pedido> cola;
    public AtomicLong comparaciones;

    public Cocina() {
        comparaciones = new AtomicLong(0);
        cola = new PriorityQueue<>(new Comparator<Pedido>() {
            @Override
            public int compare(Pedido a, Pedido b) {
                comparaciones.incrementAndGet();
                if (a.tiempoRestante != b.tiempoRestante) {
                    return Integer.compare(a.tiempoRestante, b.tiempoRestante);
                } else {
                    return Integer.compare(a.llegada, b.llegada);
                }
            }
        });
    }

    public void agregarPedido(Pedido p) {
        cola.add(p);
    }

    public Pedido obtenerPedido() {
        return cola.poll();
    }

    public int pedidosEnCola() {
        return cola.size();
    }
}
