import java.util.ArrayList;
import java.util.List;

public class FilaPedidos {

    private List<Pedido> listaPedidosOrdenadaPorTiempo = new ArrayList<>();
    private int totalComparaciones = 0;

    public void insertarPedido(Pedido pedido) {
        int indice = 0;
        while (indice < listaPedidosOrdenadaPorTiempo.size()) {
            totalComparaciones++;
            if (pedido.getMinutosRestantes() < listaPedidosOrdenadaPorTiempo.get(indice).getMinutosRestantes()) {
                break;
            }
            indice++;
        }
        listaPedidosOrdenadaPorTiempo.add(indice, pedido);
    }

    public int cantidadPendientes() {
        return listaPedidosOrdenadaPorTiempo.size();
    }

    public boolean estaVacia() {
        return listaPedidosOrdenadaPorTiempo.isEmpty();
    }

    public Pedido obtenerSiguientePedido() {
        if (estaVacia()) return null;
        return listaPedidosOrdenadaPorTiempo.remove(0);
    }

    public int getTotalComparaciones() {
        return totalComparaciones;
    }
}
