public class Nodo {
    private Pedido pedido;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.izquierda = null;
        this.derecha = null;
    }

    public Nodo izquierda(){
        return izquierda;
    }

    public Nodo derecha(){
        return derecha;
    }

    public Pedido pedido(){
        return pedido;
    }
}