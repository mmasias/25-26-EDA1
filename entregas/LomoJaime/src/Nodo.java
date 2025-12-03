class Nodo {
    Pedido pedido;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }
}