
public class Arbol implements EstructuraPedidos {

    private static class Nodo {
        Pedido pedido;
        Nodo izquierda;
        Nodo derecha;

        Nodo(Pedido p) {
            this.pedido = p;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    private Nodo raiz;
    private int size;
    private long comparaciones;

    public Arbol() {
        this.raiz = null;
        this.size = 0;
        this.comparaciones = 0;
    }

    @Override
    public void insertar(Pedido p) {
        assert p != null : "No se puede insertar un pedido nulo";
        raiz = insertarRec(raiz, p);
        size++;
        
        assert size > 0 : "El tamaño debe ser positivo tras insertar";
    }

    private Nodo insertarRec(Nodo nodo, Pedido p) {
        if (nodo == null) {
            return new Nodo(p);
        }

        comparaciones++;
        if (p.getTiempoPreparacion() < nodo.pedido.getTiempoPreparacion()) {
            nodo.izquierda = insertarRec(nodo.izquierda, p);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, p);
        }
        return nodo;
    }

    @Override
    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.izquierda != null) {
            comparaciones++;
            padre = actual;
            actual = actual.izquierda;
        }

        Pedido minPedido = actual.pedido;

        if (padre == null) {
            raiz = actual.derecha;
        } else {
            padre.izquierda = actual.derecha;
        }

        size--;
        assert size >= 0 : "El tamaño no puede ser negativo";
        return minPedido;
    }

    @Override
    public boolean estaVacia() {
        return size == 0;
    }

    @Override
    public int tamano() {
        return size;
    }

    @Override
    public long getComparaciones() {
        return comparaciones;
    }
}