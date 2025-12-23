package entregas.caicedoFernando.src;

public class NodoPedido {
    Pedido pedido;
    NodoPedido izquierda;
    NodoPedido derecha;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.izquierda = null;
        this.derecha = null;
    }
}