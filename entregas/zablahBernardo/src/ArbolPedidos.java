
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
        tamano++;
        Nodo nuevoNodo = new Nodo(nuevoPedido);

        if (nodo == null) {
            nodo = nuevoNodo;
            return;
        }

        Nodo actual = nodo;
        while (true) {
            comparaciones++;
            if (nuevoPedido.getTiempoPreparacion() <= actual.getPedido().getTiempoPreparacion()) {
                if (actual.getIzquierda() == null) {
                    actual.setIzquierda(nuevoNodo);
                    return;
                }
                actual = actual.getIzquierda();
            } else {
                if (actual.getDerecha() == null) {
                    actual.setDerecha(nuevoNodo);
                    return;
                }
                actual = actual.getDerecha();
            }
        }
    }

    public Pedido extraerMinimo() {
        if (nodo == null)
            return null;

        Nodo padre = null;
        Nodo actual = nodo;

        while (actual.getIzquierda() != null) {
            comparaciones++;
            padre = actual;
            actual = actual.getIzquierda();
        }

        tamano--;
        if (padre == null) {
            nodo = actual.getDerecha();
        } else {
            padre.setIzquierda(actual.getDerecha());
        }
        return actual.getPedido();
    }

    public int getTamano() {
        return this.tamano;
    }

    public Object getComparaciones() {
        return comparaciones;
    }
}
