public class ListaPedidos {

    private NodoPedido cabeza;
    private int tamaño;

    public ListaPedidos() {
        cabeza = null;
        tamaño = 0;
    }

    public void insertarOrdenado(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);

        if (cabeza == null) {
            cabeza = nuevo;
            tamaño++;
            return;
        }

        if (cabeza.getDato().compareTo(pedido) > 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            tamaño++;
            return;
        }

        NodoPedido actual = cabeza;

        while (actual.getSiguiente() != null && actual.getSiguiente().getDato().compareTo(pedido) <= 0) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamaño++;
    }

    public Pedido extraerPrimero() {
        Pedido resultado = null;

        if (cabeza != null) {
            resultado = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
            tamaño--;
        }

        return resultado;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }
}
