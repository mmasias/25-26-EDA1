class ArbolBinario {
    private Nodo raiz;
    private int totalComparaciones;
    private int cantidadPedidos;

    public ArbolBinario() {
        this.raiz = null;
        this.totalComparaciones = 0;
        this.cantidadPedidos = 0;
    }

    public void insertar(Pedido nuevoPedido) {
        raiz = insertarDesdeRaiz(raiz, nuevoPedido);
        cantidadPedidos++;
    }

    private Nodo insertarDesdeRaiz(Nodo nodoActual, Pedido nuevoPedido) {
        if (nodoActual == null) {
            return new Nodo(nuevoPedido);
        }

        totalComparaciones++;
        if (nuevoPedido.tiempoTotalPreparacion < nodoActual.pedido.tiempoTotalPreparacion) {
            nodoActual.hijoIzquierdo = insertarDesdeRaiz(nodoActual.hijoIzquierdo, nuevoPedido);
        } else {
            nodoActual.hijoDerecho = insertarDesdeRaiz(nodoActual.hijoDerecho, nuevoPedido);
        }
        return nodoActual;
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo padreDelMinimo = null;
        Nodo nodoMinimo = raiz;

        while (nodoMinimo.hijoIzquierdo != null) {
            totalComparaciones++; 
            padreDelMinimo = nodoMinimo;
            nodoMinimo = nodoMinimo.hijoIzquierdo;
        }

        Pedido pedidoMinimo = nodoMinimo.pedido;

        if (padreDelMinimo == null) {
            raiz = nodoMinimo.hijoDerecho;
        } else {
            padreDelMinimo.hijoIzquierdo = nodoMinimo.hijoDerecho;
        }

        cantidadPedidos--;
        return pedidoMinimo;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int obtenerCantidadPedidos() {
        return cantidadPedidos;
    }

    public int obtenerTotalComparaciones() {
        return totalComparaciones;
    }
}