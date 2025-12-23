package entregas.caicedoFernando.src;

public class ColaPedidos {
    private NodoPedido raiz;
    private int tamano;

    public ColaPedidos() {
        this.raiz = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return raiz == null;
    }

    public int obtenerTamano() {
        return tamano;
    }

    public void agregar(Pedido pedido) {
        raiz = insertarRecursivo(raiz, pedido);
        tamano++;
    }

    private NodoPedido insertarRecursivo(NodoPedido nodo, Pedido pedido) {
        if (nodo == null) {
            return new NodoPedido(pedido);
        }
        if (pedido.tiempoPreparacion < nodo.pedido.tiempoPreparacion) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, pedido);
        } else {
            nodo.derecha = insertarRecursivo(nodo.derecha, pedido);
        }
        return nodo;
    }

    public ResultadoExtraccion extraerMinimo() {
        if (estaVacia()) return new ResultadoExtraccion(null, 0);

        int comparaciones = 0;
        NodoPedido padre = null;
        NodoPedido actual = raiz;

        while (actual.izquierda != null) {
            comparaciones++;
            padre = actual;
            actual = actual.izquierda;
        }

        if (padre == null) {
            raiz = actual.derecha;
        } else {
            padre.izquierda = actual.derecha;
        }

        tamano--;
        return new ResultadoExtraccion(actual.pedido, comparaciones);
    }
}