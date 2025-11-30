
public class Arbol implements EstructuraPedidos {

    private static class Nodo {

        Pedido pedido;
        Nodo left;
        Nodo right;

        Nodo(Pedido p) {
            this.pedido = p;
        }
    }

    private Nodo root;
    private int size = 0;
    private long comparaciones = 0;

    @Override
    public void insertar(Pedido p) {
        root = insertarRec(root, p);
        size++;
    }

    private Nodo insertarRec(Nodo nodo, Pedido p) {
        if (nodo == null) {
            return new Nodo(p);
        }
        comparaciones++;
        if (p.getTiempoPreparacion() < nodo.pedido.getTiempoPreparacion()) {
            nodo.left = insertarRec(nodo.left, p);
        } else {
            nodo.right = insertarRec(nodo.right, p);
        }
        return nodo;
    }

    @Override
    public Pedido extraerMinimo() {
        if (root == null) {
            return null;
        }
        Nodo parent = null;
        Nodo cur = root;
        while (cur.left != null) {
            comparaciones++;
            parent = cur;
            cur = cur.left;
        }
        Pedido min = cur.pedido;
        if (parent == null) {
            root = cur.right;
        } else {
            parent.left = cur.right;
        }
        size--;
        return min;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public long getComparaciones() {
        return comparaciones;
    }
}
