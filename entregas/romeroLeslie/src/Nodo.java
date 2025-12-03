class NodoArbol {
    private Pedido pedido; 
    private NodoArbol nodoIzquierdo;
    private NodoArbol nodoDerecho;

    public NodoArbol(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido obtenerPedido() {
        return pedido;
    }

    public NodoArbol obtenerIzquierdo() {
        return nodoIzquierdo;
    }

    public void asignarIzquierdo(NodoArbol izquierdo) {
        this.nodoIzquierdo = izquierdo;
    }

    public NodoArbol obtenerDerecho() {
        return nodoDerecho;
    }

    public void asignarDerecho(NodoArbol derecho) {
        this.nodoDerecho = derecho;
    }
}