import java.util.PriorityQueue;
import java.util.Comparator;

public class ColaPrioridadPedidos implements IEstructuraPedidos {
    private final PriorityQueue<Pedido> cola;
    private long comparaciones;

    public ColaPrioridadPedidos() {
        cola = new PriorityQueue<>(new Comparator<Pedido>() {
            @Override
            public int compare(Pedido p1, Pedido p2) {
                comparaciones++;
                return Integer.compare(p1.getTiempoPreparacion(), p2.getTiempoPreparacion());
            }
        });
        comparaciones = 0;
    }
}


