public class Nodo {

    private Pedido pedido;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(Pedido pedido) {
        assert pedido != null : "El pedido no puede ser null";
        
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getTiempoPreparacion() {
        return pedido.getTiempoPreparacion(); 
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
