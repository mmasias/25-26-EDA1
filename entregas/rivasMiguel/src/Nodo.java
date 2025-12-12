public class Nodo {

    private Pedido pedido;
    private Nodo left;
    private Nodo right;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.left = null;
        this.right = null;
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public Nodo getLeft() {
        return left;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public Nodo getRight() {
        return right;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }
}