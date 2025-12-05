public class ArbolPedidos {

    private NodoArbolPedido raiz;
    private int tamaño;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamaño = 0;
    }

    public void insertar(Pedido pedido) {
        raiz = insertarRec(raiz, pedido);
        tamaño++;
    }

    private NodoArbolPedido insertarRec(NodoArbolPedido nodo, Pedido pedido) {
        NodoArbolPedido resultado = nodo;

        if (nodo == null) {
            resultado = new NodoArbolPedido(pedido);
            return resultado;
        }

        if (pedido.compareTo(nodo.getDato()) < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), pedido));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), pedido));
        }

        return resultado;
    }

    public boolean estaVacio() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public Pedido extraerMinimo() {
        Pedido resultado = null;

        if (raiz == null) {
            return resultado;
        }

        if (raiz.getIzquierdo() == null) {
            resultado = raiz.getDato();
            raiz = raiz.getDerecho();
            tamaño--;
            return resultado;
        }

        NodoArbolPedido padre = raiz;
        NodoArbolPedido actual = raiz.getIzquierdo();

        while (actual.getIzquierdo() != null) {
            padre = actual;
            actual = actual.getIzquierdo();
        }

        resultado = actual.getDato();
        padre.setIzquierdo(actual.getDerecho());
        tamaño--;

        return resultado;
    }
}
