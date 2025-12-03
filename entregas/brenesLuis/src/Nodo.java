
public class Nodo {

    public Pedido pedido;
    public Nodo izquierdo;
    public Nodo derecho;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }
}
