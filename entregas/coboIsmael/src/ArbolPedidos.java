public class ArbolPedidos {

    private NodoArbolPedido raiz;
    private int tamano;
    private int comparacionesInsercion;
    private int comparacionesSeleccion;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamano = 0;
        this.comparacionesInsercion = 0;
        this.comparacionesSeleccion = 0;
    }

    public void insertar(Pedido pedido) {
        if (raiz == null) {
            raiz = new NodoArbolPedido(pedido);
            tamano++;
            return;
        }

        NodoArbolPedido nodoActual = raiz;

        while (true) {
            comparacionesInsercion++;
            if (pedido.getTiempoPreparacion() < nodoActual.getDato().getTiempoPreparacion()) {
                if (nodoActual.getIzquierdo() == null) {
                    nodoActual.setIzquierdo(new NodoArbolPedido(pedido));
                    tamano++;
                    return;
                } else {
                    nodoActual = nodoActual.getIzquierdo();
                }
            } else {
                if (nodoActual.getDerecho() == null) {
                    nodoActual.setDerecho(new NodoArbolPedido(pedido));
                    tamano++;
                    return;
                } else {
                    nodoActual = nodoActual.getDerecho();
                }
            }
        }
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        NodoArbolPedido nodoPadre = null;
        NodoArbolPedido nodoActual = raiz;

        while (nodoActual.getIzquierdo() != null) {
            comparacionesSeleccion++;
            nodoPadre = nodoActual;
            nodoActual = nodoActual.getIzquierdo();
        }

        Pedido resultado = nodoActual.getDato();

        if (nodoPadre == null) {
            raiz = nodoActual.getDerecho();
        } else {
            nodoPadre.setIzquierdo(nodoActual.getDerecho());
        }

        tamano--;
        return resultado;
    }

    public boolean estaVacio() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }

    public int getComparacionesInsercion() {
        return comparacionesInsercion;
    }

    public int getComparacionesSeleccion() {
        return comparacionesSeleccion;
    }
}
