import java.util.ArrayList;
import java.util.List;

public class ColaPrioridad {

    private List<Pedido> pedidos;
    private int comparacionesTotales;

    public ColaPrioridad() {
        this.pedidos = new ArrayList<>();
        this.comparacionesTotales = 0;
    }

    public void agregar(Pedido pedido) {
        pedidos.add(pedido);
    }
   
    public Pedido obtenerSiguiente() {
        if (pedidos.isEmpty()) {
            return null;
        }

        Pedido candidatoMenor = pedidos.get(0);
        int indiceMenor = 0;

        for (int i = 1; i < pedidos.size(); i++) {
            Pedido actual = pedidos.get(i);
            
            if (actual.getTiempoRestante() < candidatoMenor.getTiempoRestante()) {
                candidatoMenor = actual;
                indiceMenor = i;
            }
        }

        pedidos.remove(indiceMenor);
        return candidatoMenor;
    }

    public boolean estaVacia() {
        return pedidos.isEmpty();
    }

    public int cantidadPendientes() {
        return pedidos.size();
    }

    public int getComparaciones() {
        return comparacionesTotales;
    }
}