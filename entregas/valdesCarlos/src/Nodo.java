public class Nodo {
    public Pedido pedido;
    public Nodo izquierdo;
    public Nodo derecho;

    public Nodo(Pedido pedido) {
        
        assert pedido != null : "No se puede crear un Nodo sin un Pedido";
        
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
    }
}