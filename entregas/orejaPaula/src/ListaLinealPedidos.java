import java.util.List;
import java.util.ArrayList;

public class ListaLinealPedidos implements IEstructuraPedidos {
    private final List<Pedido> lista;
    private long comparaciones;

    public ListaLinealPedidos() {
        lista = new ArrayList<>();
        comparaciones = 0;
    }

    @Override
    public void insertar(Pedido p) {
        lista.add(p);
    }

    @Override
    public boolean estaVacia() {
        return lista.isEmpty();
    }

    @Override
    public int tamano() {
        return lista.size();
    }
}



