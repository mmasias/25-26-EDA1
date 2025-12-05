public class NodoPedido {
    private Pedido dato;
    private NodoPedido siguiente;

    public NodoPedido(Pedido dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Pedido getDato() {
        return dato;
    }

    public NodoPedido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPedido siguiente) {
        this.siguiente = siguiente;
    }
}
