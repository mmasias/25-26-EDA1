public class Nodo {
    private Pedido pedido;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.izquierda = null;
        this.derecha = null;
    }

    public Nodo izquierda() {
        return izquierda;
    }

    public void crearNodoIzquierda(Nodo nodo) {
        this.izquierda = nodo;
    }

    public Nodo derecha() {
        return derecha;
    }

    public void crearNodoDerecha(Nodo nodo) {
        this.derecha = nodo;
    }

    public Pedido pedido() {
        return pedido;
    }
}