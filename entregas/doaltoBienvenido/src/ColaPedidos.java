public class ColaPedidos {
    private NodoPedido raiz;
    private int cantidadPedidos;

    public ColaPedidos() {
        raiz = null;
        cantidadPedidos = 0;
    }

    public void insertar(Pedido pedido) {
        NodoPedido nodo = new NodoPedido(pedido);
        if (raiz == null) {
            raiz = nodo;
        } else {
            insertarEnArbol(raiz, nodo);
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

    public Pedido cogerMinimo() {
        if (raiz == null) return null;
        NodoPedido nodoMin = raiz;
        while (nodoMin.getIzquierdo() != null) {
            nodoMin = nodoMin.getIzquierdo();
        }
        return nodoMin.getPedido();
    }

    public int tamaño() { 
        return cantidadPedidos; 
    }

    public boolean estaVacia() { 
        return cantidadPedidos == 0; 
    }

    private void insertarEnArbol(NodoPedido actual, NodoPedido nuevo) {
        if (nuevo.getPedido().compareTo(actual.getPedido()) < 0) {
            if (actual.getIzquierdo() == null) {
                actual.setIzquierdo(nuevo);
                nuevo.setPadre(actual);
            } else {
                insertarEnArbol(actual.getIzquierdo(), nuevo);
            }
        } else {
            if (actual.getDerecho() == null) {
                actual.setDerecho(nuevo);
                nuevo.setPadre(actual);
            } else {
                insertarEnArbol(actual.getDerecho(), nuevo);
            }
        }
    }


    private void eliminarNodo(NodoPedido nodo) {
        
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