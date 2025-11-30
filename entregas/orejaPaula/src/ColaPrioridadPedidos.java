import java.util.PriorityQueue;

public class ColaPrioridadPedidos implements IEstructuraPedidos {
    private final PriorityQueue<Pedido> cola;
    private long comparaciones;
}

