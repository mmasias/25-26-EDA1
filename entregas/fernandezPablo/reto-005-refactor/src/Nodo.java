public class Nodo {
    private Pedido pedido;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Pedido pedidoNuevo) {
        this.pedido = pedidoNuevo;
    }

    public Pedido getPedido() { return pedido; }
    public Nodo getIzquierda() { return izquierda; }
    public Nodo getDerecha() { return derecha; }

    public void setIzquierda(Nodo izquierda) { this.izquierda = izquierda; }
    public void setDerecha(Nodo derecha) { this.derecha = derecha; }
}


