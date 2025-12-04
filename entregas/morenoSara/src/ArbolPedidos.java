public class ArbolPedidos {

    private NodoPedido raiz = null;
    private int totalComparaciones = 0;
    private int tamaño = 0;

    public void insertarPedido(Pedido pedido) {
        if (raiz == null) {
            raiz = new NodoPedido(pedido);
            tamaño++;
            return;
        }
        NodoPedido actual = raiz;
        while (true) {
            totalComparaciones++;
            if (pedido.getMinutosRestantes() < actual.pedido.getMinutosRestantes()) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new NodoPedido(pedido);
                    tamaño++;
                    return;
                } else {
                    actual = actual.izquierdo;
                }
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new NodoPedido(pedido);
                    tamaño++;
                    return;
                } else {
                    actual = actual.derecho;
                }
            }
        }
    }

    public int cantidadPendientes() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public Pedido obtenerSiguientePedido() {
        return extraerMinimo();
    }

    private Pedido extraerMinimo() {
        if (raiz == null) return null;
        NodoPedido padre = null;
        NodoPedido actual = raiz;
        while (actual.izquierdo != null) {
            padre = actual;
            actual = actual.izquierdo;
        }
        Pedido minimo = actual.pedido;
        if (padre == null) {
            raiz = unirSubarboles(raiz.izquierdo, raiz.derecho);
        } else {
            padre.izquierdo = unirSubarboles(actual.izquierdo, actual.derecho);
        }
        tamaño--;
        return minimo;
    }

    private NodoPedido unirSubarboles(NodoPedido a, NodoPedido b) {
        if (a == null) return b;
        NodoPedido actual = a;
        while (actual.derecho != null) actual = actual.derecho;
        actual.derecho = b;
        return a;
    }

    public int getTotalComparaciones() {
        return totalComparaciones;
    }
}

