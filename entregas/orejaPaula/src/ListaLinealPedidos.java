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

    @Override
    public Pedido extraerMinimo() {
        if (lista.isEmpty()) {
            return null;
        }
        int indiceMinimo = 0;
        Pedido minimo = lista.get(0);

        for (int i = 1; i < lista.size(); i++) {
            Pedido actual = lista.get(i);
            comparaciones++;
            if (actual.getTiempoPreparacion() < minimo.getTiempoPreparacion()) {
                minimo = actual;
                indiceMinimo = i;
            }
        }

        lista.remove(indiceMinimo);
        return minimo;
    }
}




