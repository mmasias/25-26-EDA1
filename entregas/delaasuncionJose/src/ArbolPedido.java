public class ArbolPedido {
    private NodoPedido raiz;
    private long comparaciones;
    private int size;

    public ArbolPedido() {
        this.raiz = null;
        this.comparaciones = 0;
        this.size = 0;
    }

    public void add(Pedido pedido) {
        this.raiz = addRecursivo(this.raiz, pedido);
        this.size++;
    }

    private NodoPedido addRecursivo(NodoPedido actual, Pedido pedido) {
        if (actual == null) {
            return new NodoPedido(pedido);
        }

        comparaciones++;
        if (pedido.tiempoPreparacion < actual.pedido.tiempoPreparacion) {
            actual.izquierdo = addRecursivo(actual.izquierdo, pedido);
        } else {
            actual.derecho = addRecursivo(actual.derecho, pedido);
        }

        return actual;
    }

    public Pedido poll() {
        if (raiz == null) {
            return null;
        }

        NodoPedido minNodeParent = null;
        NodoPedido minNode = raiz;

        while (minNode.izquierdo != null) {
            comparaciones++;
            minNodeParent = minNode;
            minNode = minNode.izquierdo;
        }

        if (minNodeParent == null) {
            raiz = minNode.derecho;
        } else {
            minNodeParent.izquierdo = minNode.derecho;
        }

        this.size--;
        return minNode.pedido;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.raiz == null;
    }

    public long getComparaciones() {
        return this.comparaciones;
    }
}
