public class ArbolPedidos {

    private class Nodo {
        Pedido pedido;
        Nodo izquierda;
        Nodo derecha;

        Nodo(Pedido pedido) {
            this.pedido = pedido;
            izquierda = null;
            derecha = null;
        }
    }

    private Nodo raiz;
    private int comparaciones;

    public ArbolPedidos() {
        raiz = null;
        comparaciones = 0;
    }

    public void insertar(Pedido pedido) {
        Nodo nuevo = new Nodo(pedido);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo actual = raiz;
            boolean insertado = false;

            while (!insertado) {
                if (pedido.obtenerTiempoRestante() < actual.pedido.obtenerTiempoRestante()) {
                    comparaciones = comparaciones + 1;

                    if (actual.izquierda == null) {
                        actual.izquierda = nuevo;
                        insertado = true;
                    } else {
                        actual = actual.izquierda;
                    }
                } else {
                    comparaciones = comparaciones + 1;

                    if (actual.derecha == null) {
                        actual.derecha = nuevo;
                        insertado = true;
                    } else {
                        actual = actual.derecha;
                    }
                }
            }
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public Pedido extraerMinimo() {

        if (raiz == null) {
            return null;
        }

        Nodo actual = raiz;
        Nodo padre = null;

        while (actual.izquierda != null) {
            comparaciones = comparaciones + 1;
            padre = actual;
            actual = actual.izquierda;
        }

        Pedido minimo = actual.pedido;

        if (padre == null) {
            raiz = actual.derecha;
        } else {
            padre.izquierda = actual.derecha;
        }

        return minimo;
    }

    public int obtenerComparaciones() {
        return comparaciones;
    }
}