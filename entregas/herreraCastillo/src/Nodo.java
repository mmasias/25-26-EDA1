public class Nodo {
    private Pedido pedido;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.izquierda = null;
        this.derecha = null;
    }


    public Pedido getPedido() {
        return pedido;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}