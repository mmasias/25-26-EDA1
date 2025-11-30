public class Nodo {
    Pedido pedido;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        izquierda = null;
        derecha = null;
    }
}