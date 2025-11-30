import java.util.List;
import java.util.ArrayList;

public class ListaLinealPedidos implements IEstructuraPedidos {
    private final List<Pedido> lista;
    private long comparaciones;

    public ListaLinealPedidos() {
        lista = new ArrayList<>();
        comparaciones = 0;
    }
}


