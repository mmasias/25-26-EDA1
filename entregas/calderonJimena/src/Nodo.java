class Nodo {
    Pedido pedido;
    Nodo hijoIzquierdo;
    Nodo hijoDerecho;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
}