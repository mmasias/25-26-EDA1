class ArbolPedidos {
    private NodoArbolPedido raiz;
    private int contadorComparaciones = 0;

    private int compararPedidos(Pedido p1, Pedido p2) {
        contadorComparaciones++;
        int comparacion = Integer.compare(p1.obtenerTiempoPreparacionTotal(), p2.obtenerTiempoPreparacionTotal());
        if (comparacion == 0)
            return Integer.compare(p1.obtenerMinutoLlegada(), p2.obtenerMinutoLlegada());
        return comparacion;
    }

    public int obtenerContadorComparaciones() {
        return contadorComparaciones;
    }

    public void reiniciarContadorComparaciones() {
        contadorComparaciones = 0;
    }

    public void insertarPedido(Pedido pedido) {
        raiz = insertarRecursivo(raiz, pedido);
    }

    private NodoArbolPedido insertarRecursivo(NodoArbolPedido nodo, Pedido pedido) {
        if (nodo == null) return new NodoArbolPedido(pedido);
        if (compararPedidos(pedido, nodo.pedido) < 0)
            nodo.izquierda = insertarRecursivo(nodo.izquierda, pedido);
        else
            nodo.derecha = insertarRecursivo(nodo.derecha, pedido);
        return nodo;
    }

    public Pedido extraerPedidoMasRapido() {
        if (raiz == null) return null;
        NodoArbolPedido[] resultado = extraerMinimoRecursivo(raiz);
        raiz = resultado[1];
        return resultado[0].pedido;
    }

    private NodoArbolPedido[] extraerMinimoRecursivo(NodoArbolPedido nodo) {
        if (nodo.izquierda == null)
            return new NodoArbolPedido[]{ nodo, nodo.derecha };
        NodoArbolPedido[] resultado = extraerMinimoRecursivo(nodo.izquierda);
        nodo.izquierda = resultado[1];
        return new NodoArbolPedido[]{ resultado[0], nodo };
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int contarPedidos() {
        return contarPedidosRecursivo(raiz);
    }

    private int contarPedidosRecursivo(NodoArbolPedido nodo) {
        if (nodo == null) return 0;
        return 1 + contarPedidosRecursivo(nodo.izquierda) + contarPedidosRecursivo(nodo.derecha);
    }
}
