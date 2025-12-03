public class NodoPedido {

    Pedido pedido;
    NodoPedido izquierdo;
    NodoPedido derecho;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }
}
