public class arbolPedidos {

    private Nodo raiz = null;
    private long comparaciones = 0;

    public long getComparaciones() { return comparaciones; }
    public boolean estaVacio() { return raiz == null; }

    public void insertar(Pedido pedido) {
        raiz = insertarRecursivo(raiz, pedido);
    }

    private Nodo insertarRecursivo(Nodo nodoActual, Pedido pedido) {
        if (nodoActual == null) {
            return new Nodo(pedido);
        }

        comparaciones++;

        if (pedido.getTiempoRestante() < nodoActual.getPedido().getTiempoRestante()) {
            nodoActual.setIzquierda(insertarRecursivo(nodoActual.getIzquierda(), pedido));
        } else {
            nodoActual.setDerecha(insertarRecursivo(nodoActual.getDerecha(), pedido));
        }

        return nodoActual;
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo[] resultado = extraerMinimoRecursivo(raiz, null);
        raiz = resultado[1];
        return resultado[0].getPedido();
    }

    private Nodo[] extraerMinimoRecursivo(Nodo nodoActual, Nodo nodoPadre) {
        if (nodoActual.getIzquierda() == null) {
            if (nodoPadre == null) {
                return new Nodo[]{nodoActual, nodoActual.getDerecha()};
            } else {
                nodoPadre.setIzquierda(nodoActual.getDerecha());
                return new Nodo[]{nodoActual, raiz};
            }
        }
        return extraerMinimoRecursivo(nodoActual.getIzquierda(), nodoActual);
    }

    public void imprimirArbol() {
        imprimirRec(raiz, 0);
    }

    private void imprimirRec(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirRec(nodo.getDerecha(), nivel + 1);
        System.out.println(" ".repeat(nivel * 4) + "- " + nodo.getPedido());
        imprimirRec(nodo.getIzquierda(), nivel + 1);
    }
}

