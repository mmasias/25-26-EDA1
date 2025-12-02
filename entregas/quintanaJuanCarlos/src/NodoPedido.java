public class NodoPedido {

    Pedido pedido;
    NodoPedido nodoIzquierdo;
    NodoPedido nodoDerecho;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
    }
}
