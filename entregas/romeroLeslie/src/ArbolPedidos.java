public class ArbolPedidos {
    private NodoArbol raiz; 
    private int tamano;
    private int contadorComparaciones;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamano = 0;
        this.contadorComparaciones = 0;
    }

    public int getTamano() {
        return tamano;
    }

    public int getComparaciones() {
        return contadorComparaciones;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(Pedido nuevoPedido) {
        raiz = insertarRecursivo(raiz, nuevoPedido);
        tamano++;
    }

    private NodoArbol insertarRecursivo(NodoArbol nodoActual, Pedido nuevoPedido) {
        if (nodoActual == null) {
            return new NodoArbol(nuevoPedido);
        }

        contadorComparaciones++;
        
        int tiempoActual = nodoActual.obtenerPedido().getTiempoPreparacionTotal();
        int tiempoNuevo = nuevoPedido.getTiempoPreparacionTotal();

        if (tiempoNuevo < tiempoActual) {
            nodoActual.asignarIzquierdo(insertarRecursivo(nodoActual.obtenerIzquierdo(), nuevoPedido));
        } else if (tiempoNuevo > tiempoActual) {
            nodoActual.asignarDerecho(insertarRecursivo(nodoActual.obtenerDerecho(), nuevoPedido));
        } else {
            
            if (nuevoPedido.getId() < nodoActual.obtenerPedido().getId()) {
                 nodoActual.asignarIzquierdo(insertarRecursivo(nodoActual.obtenerIzquierdo(), nuevoPedido));
            } else {
                 nodoActual.asignarDerecho(insertarRecursivo(nodoActual.obtenerDerecho(), nuevoPedido));
            }
        }
        return nodoActual;
    }

   
    public Pedido obtenerMaxPrioridad() {
        if (estaVacio()) {
            return null;
        }

        NodoArbol nodoMinimo = encontrarNodoMinimo(raiz);
        Pedido pedidoPrioritario = nodoMinimo.obtenerPedido();

        raiz = eliminarNodoMinimo(raiz);
        tamano--;
        
        return pedidoPrioritario;
    }

    private NodoArbol encontrarNodoMinimo(NodoArbol nodo) {
        contadorComparaciones++;
        if (nodo.obtenerIzquierdo() == null) {
            return nodo;
        }
        return encontrarNodoMinimo(nodo.obtenerIzquierdo());
    }

    private NodoArbol eliminarNodoMinimo(NodoArbol nodo) {
        contadorComparaciones++;
        if (nodo.obtenerIzquierdo() == null) {
            return nodo.obtenerDerecho();
        }
        nodo.asignarIzquierdo(eliminarNodoMinimo(nodo.obtenerIzquierdo()));
        return nodo;
    }
}