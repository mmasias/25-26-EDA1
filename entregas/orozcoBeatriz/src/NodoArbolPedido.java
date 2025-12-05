public class NodoArbolPedido {

    private Pedido dato;
    private NodoArbolPedido izquierdo;
    private NodoArbolPedido derecho;

    public NodoArbolPedido(Pedido dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Pedido getDato() {
        return dato;
    }

    public NodoArbolPedido getIzquierdo() {
        return izquierdo;
    }

    public NodoArbolPedido getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoArbolPedido izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoArbolPedido derecho) {
        this.derecho = derecho;
    }
}
