public class ArbolPedidos {
    private Nodo raiz;
    private int tamano;

    public static int comparacionesTotales = 0;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamano = 0;
    }

    public void insertar(Pedido nuevoPedido) {
        if (raiz == null) {
            raiz = new Nodo(nuevoPedido);
        } else {
            insertarRecursivo(raiz, nuevoPedido);
        }
        tamano++;
    }

    private void insertarRecursivo(Nodo actual, Pedido nuevoPedido) {
        comparacionesTotales++;

        if (nuevoPedido.getTiempoTotal() < actual.getPedido().getTiempoTotal()) {
            if (actual.getIzquierdo() == null) {
                actual.setIzquierdo(new Nodo(nuevoPedido));
            } else {
                insertarRecursivo(actual.getIzquierdo(), nuevoPedido);
            }
        }

        else {
            if (actual.getDerecho() == null) {
                actual.setDerecho(new Nodo(nuevoPedido));
            } else {
                insertarRecursivo(actual.getDerecho(), nuevoPedido);
            }
        }
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        if (raiz.getIzquierdo() == null) {
            Pedido pedidoMinimo = raiz.getPedido();
            raiz = raiz.getDerecho();
            tamano--;
            return pedidoMinimo;
        }

        Nodo padre = raiz;
        Nodo actual = raiz.getIzquierdo();

        while (actual.getIzquierdo() != null) {
            comparacionesTotales++;
            actual = actual.getIzquierdo();
        }

        Pedido pedidoMinimo = actual.getPedido();

        padre.setIzquierdo(actual.getDerecho());

        tamano--;
        return pedidoMinimo;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public int getTamano() {
        return tamano;
    }
}
