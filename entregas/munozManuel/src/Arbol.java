package entregas.munozManuel.src;

class Arbol {
    private Nodo raiz;
    private int cantidadNodos;

    public Arbol() {
        cantidadNodos = 0;
    }

    public void insertarPedido(Nodo nodo) {
        if (raiz == null) {
            raiz = nodo;
            cantidadNodos += 1;
        } else {
            raiz.insertarHijo(nodo);
            cantidadNodos += 1;
        }
    }

    public int cantidadNodos() {
        return cantidadNodos;
    }

    public Nodo buscarNodoConTiempoMinimo() {
        return buscarNodoRecursivo(raiz, null);
    }

    public void recorrerOrdenes() {
        if (cantidadNodos == 0) {
            System.out.println("Ordenes vacío");
            return;
        }

        Nodo[] nodosOrdenados = new Nodo[cantidadNodos];
        int indiceInicial = 0;

        recorrerInOrden(raiz, nodosOrdenados, indiceInicial);

        for (Nodo nodo : nodosOrdenados) {
            if (nodo != null && !nodo.pedidoHecho()) {
                System.out.println(nodo.nombreDelPedido() + " con tiempo: " + nodo.tiempoPreparacion());
            }
        }
    }
    
    public int recorrerOrdenes(boolean terminado) {
        Nodo[] nodosOrdenados = new Nodo[cantidadNodos];
        int cantidadSatisfecha = 0;

        if (cantidadNodos == 0) {
            return cantidadSatisfecha;
        }

        int indiceInicial = 0;

        recorrerInOrden(raiz, nodosOrdenados, indiceInicial);

        for (Nodo nodo : nodosOrdenados) {
            if (nodo != null && nodo.pedidoHecho() == terminado) {
                cantidadSatisfecha += 1;
            }
        }
        return cantidadSatisfecha;
    }

    public int contarComparaciones() {
        if (cantidadNodos == 0){
            return 0;
        }
        int[] contador = new int[1];
        contarComparaciones(raiz, null, contador);
        return contador[0];
    }
    
    private Nodo contarComparaciones(Nodo nodo, Nodo nodoActualMinimo, int[] contador) {
        if (nodo == null){
            return nodoActualMinimo;
        }

        nodoActualMinimo = contarComparaciones(nodo.hijoIzquerda(), nodoActualMinimo, contador);

        if (nodoActualMinimo != null) {
            contador[0]++;
            if (nodo.tiempoPreparacion() < nodoActualMinimo.tiempoPreparacion()) {
                nodoActualMinimo = nodo;
            }
        } else {
            nodoActualMinimo = nodo;
        }

        nodoActualMinimo = contarComparaciones(nodo.hijoDerecha(), nodoActualMinimo, contador);

        return nodoActualMinimo;
    }

    private int recorrerInOrden(Nodo nodo, Nodo[] nodosOrdenados, int indice) {
        if (nodo == null){
            return indice;
        }

        indice = recorrerInOrden(nodo.hijoIzquerda(), nodosOrdenados, indice);

        nodosOrdenados[indice] = nodo;
        indice += 1;

        indice = recorrerInOrden(nodo.hijoDerecha(), nodosOrdenados, indice);

        return indice;
    }

    private Nodo buscarNodoRecursivo(Nodo nodo, Nodo nodoConTiempoMinimo) {
        if (nodo == null) {
            return nodoConTiempoMinimo;
        }

        nodoConTiempoMinimo = buscarNodoRecursivo(nodo.hijoIzquerda(), nodoConTiempoMinimo);

        if ((nodoConTiempoMinimo == null || nodo.tiempoPreparacion() < nodoConTiempoMinimo.tiempoPreparacion())
                && !nodo.pedidoHecho()) {
            nodoConTiempoMinimo = nodo;
        }

        nodoConTiempoMinimo = buscarNodoRecursivo(nodo.hijoDerecha(), nodoConTiempoMinimo);

        return nodoConTiempoMinimo;
    }

}
