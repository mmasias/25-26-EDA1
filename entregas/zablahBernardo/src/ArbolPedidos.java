
public class ArbolPedidos {
    private Nodo nodo;
    private int comparaciones;
    private int tamano;

    public ArbolPedidos() {
        nodo = null;
        comparaciones = 0;
        tamano = 0;
    }

    public boolean estaVacio() {
        return nodo == null;
    }

    public void insertar(Pedido nuevoPedido) {
        nodo = insertarRecursivo(nodo, nuevoPedido);
        tamano++;
    }

    private Nodo insertarRecursivo(Nodo actual, Pedido nuevoPedido) {
        if (actual == null) {
            return new Nodo(nuevoPedido);
        }

        comparaciones++;
        if (nuevoPedido.getTiempoPreparacion() <= actual.pedido.getTiempoPreparacion()) {
            actual.izquierda = insertarRecursivo(actual.izquierda, nuevoPedido);
        } else {
            actual.derecha = insertarRecursivo(actual.derecha, nuevoPedido);
        }
        return actual;
    }

    public Pedido extraerMinimo() {
        if (nodo == null)
            return null;

        Nodo padre = null;
        Nodo actual = nodo;

        while (actual.izquierda != null) {
            comparaciones++;
            padre = actual;
            actual = actual.izquierda;
        }

        Pedido pedidoMinimo = actual.pedido;

        if (padre == null) {
            nodo = actual.derecha;
        } else {
            padre.izquierda = actual.derecha;
        }

        tamano--;
        return pedidoMinimo;
    }

    public int getTamano() {
        return this.tamano;
    }

    public Object getComparaciones() {
        return comparaciones;
    }

}
