package entregas.caicedoFernando.src;

public class NodoPedido {
    public Pedido pedido;
    public NodoPedido izquierda;
    public NodoPedido derecha;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.izquierda = null;
        this.derecha = null;
    }
}