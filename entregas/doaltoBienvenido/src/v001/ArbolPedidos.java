package v001;

public class ArbolPedidos {
    private NodoPedido raiz;
    private int cantidadPedidos;
    private static final int CANTIDAD_INICIAL = 0;
    private static final int CANTIDAD_VACIA = 0;

    public ArbolPedidos() {
        raiz = null;
        cantidadPedidos = CANTIDAD_INICIAL;
    }

    public void insertar(Pedido pedido) {
        assert pedido != null : "pedido no puede ser null";
        NodoPedido nuevo = new NodoPedido(pedido);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoPedido padre = null;
            NodoPedido actual = raiz;
            while (actual != null) {
                padre = actual;
                if (nuevo.getPedido().compareTo(actual.getPedido()) < 0) {
                    actual = actual.getIzquierdo();
                } else {
                    actual = actual.getDerecho();
                }
            }
            if (nuevo.getPedido().compareTo(padre.getPedido()) < 0) {
                padre.setIzquierdo(nuevo);
                nuevo.setPadre(padre);
            } else {
                padre.setDerecho(nuevo);
                nuevo.setPadre(padre);
            }
        }
        cantidadPedidos++;
    }

    public Pedido extraerMin() {
        if (raiz == null) return null;
        NodoPedido nodoMin = raiz;
        while (nodoMin.getIzquierdo() != null) {
            nodoMin = nodoMin.getIzquierdo();
        }
        Pedido min = nodoMin.getPedido();
        eliminarNodo(nodoMin);
        cantidadPedidos--;
        return min;
    } 

    public int tamaño() { 
        return cantidadPedidos; 
    }

    public boolean estaVacia() { 
        return cantidadPedidos == CANTIDAD_VACIA; 
    }

    private void eliminarNodo(NodoPedido nodo) {
        assert nodo != null : "nodo no puede ser null";

        if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
            reemplazarNodoEnPadre(nodo, nodo.getDerecho());
        } else if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            reemplazarNodoEnPadre(nodo, null);
        } else if (nodo.getIzquierdo() != null) {
            NodoPedido reemplazo = nodo.getIzquierdo();
            while (reemplazo.getDerecho() != null) {
                reemplazo = reemplazo.getDerecho();
            }
            nodo.setIzquierdo(reemplazo.getIzquierdo());
            reemplazo.setIzquierdo(nodo.getIzquierdo());
            reemplazo.setDerecho(nodo.getDerecho());
            reemplazarNodoEnPadre(nodo, reemplazo);
        }
    }

    private void reemplazarNodoEnPadre(NodoPedido nodo, NodoPedido nuevo) {
        assert nodo != null : "nodo no puede ser null";
        if (nodo.getPadre() != null) {
            if (nodo.getPadre().getIzquierdo() == nodo) {
                nodo.getPadre().setIzquierdo(nuevo);
            } else {
                nodo.getPadre().setDerecho(nuevo);
            }
        } else {
            raiz = nuevo;
        }
        if (nuevo != null) {
            nuevo.setPadre(nodo.getPadre());
        }
    }    
}