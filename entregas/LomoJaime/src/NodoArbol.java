class NodoArbol {
    Pedido pedido;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Pedido pedido) {
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }
}