package entregas.puenteSergio.src;

public class ArbolPedidos {
    private Nodo raiz;
    private int tamano;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamano = 0;
    }

    public void insertar(Pedido pedido) {
        raiz = insertarRec(raiz, pedido);
        tamano++;
    }

    private Nodo insertarRec(Nodo nodo, Pedido pedido) {
        if (nodo == null) {
            return new Nodo(pedido);
        }

        if (pedido.compareTo(nodo.getPedido()) < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), pedido));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), pedido));
        }

        return nodo;
    }

    public Pedido eliminarMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.getIzquierdo() != null) {
            padre = actual;
            actual = actual.getIzquierdo();
        }

        Pedido pedidoMinimo = actual.getPedido();

        if (padre == null) {
            raiz = actual.getDerecho();
        } else {
            padre.setIzquierdo(actual.getDerecho());
        }

        tamano--;
        return pedidoMinimo;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int getTamano() {
        return tamano;
    }
}
