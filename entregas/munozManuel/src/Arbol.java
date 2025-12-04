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
            cantidadNodos++;
        } else {
            raiz.insertarHijo(nodo);
            cantidadNodos++;
        }
    }

    public int cantidadNodos() {
        return cantidadNodos;
    }

    public Nodo buscarNodoConTiempoMinimo() {
        return buscarNodoRecursivo(raiz, null);
    }

    public void recorrerOrdenes() {
        if (raiz == null || cantidadNodos == 0) {
            System.out.println("Ordenes vacío");
            return;
        }

        Nodo[] nodosOrdenados = new Nodo[cantidadNodos];
        int indiceInicial = 0;

        recorrerInOrden(raiz, nodosOrdenados, indiceInicial);

        for (Nodo nodosOrdenado : nodosOrdenados) {
            if (nodosOrdenado != null && !nodosOrdenado.pedidoHecho()) {
                System.out.println(
                        nodosOrdenado.nombreDelPedido() + " con tiempo: " + nodosOrdenado.tiempoPreparacion());
            }
        }
    }

    public int contarComparaciones() {
        if (raiz == null) return 0;
        int[] contador = new int[1];
        buscarMinimoConContador(raiz, null, contador);
        return contador[0];
    }


    public int recorrerOrdenes(boolean terminado) {
        Nodo[] nodosOrdenados = new Nodo[cantidadNodos];
        int cantidadSatisfecha = 0;

        if (raiz == null || cantidadNodos == 0) {
            return cantidadSatisfecha;
        }

        int indiceInicial = 0;

        recorrerInOrden(raiz, nodosOrdenados, indiceInicial);

        for (Nodo nodosOrdenado : nodosOrdenados) {
            if (nodosOrdenado != null && nodosOrdenado.pedidoHecho() == terminado) {
                cantidadSatisfecha += 1;
            }
        }
        return cantidadSatisfecha;
    }
    
    private Nodo buscarMinimoConContador(Nodo nodo, Nodo actualMin, int[] contador) {
        if (nodo == null) return actualMin;

        actualMin = buscarMinimoConContador(nodo.hijoIzquerda(), actualMin, contador);

        if (actualMin != null) {
            contador[0]++;
            if (nodo.tiempoPreparacion() < actualMin.tiempoPreparacion()) {
                actualMin = nodo;
            }
        } else {
            actualMin = nodo;
        }

        actualMin = buscarMinimoConContador(nodo.hijoDerecha(), actualMin, contador);

        return actualMin;
    }

    private int recorrerInOrden(Nodo nodo, Nodo[] nodosOrdenados, int indice) {
        if (nodo == null)
            return indice;

        indice = recorrerInOrden(nodo.hijoIzquerda(), nodosOrdenados, indice);

        nodosOrdenados[indice] = nodo;
        indice += 1;

        indice = recorrerInOrden(nodo.hijoDerecha(), nodosOrdenados, indice);

        return indice;
    }

    private Nodo buscarNodoRecursivo(Nodo nodo, Nodo actualMinimo) {
        if (nodo == null) {
            return actualMinimo;
        }

        actualMinimo = buscarNodoRecursivo(nodo.hijoIzquerda(), actualMinimo);

        if ((actualMinimo == null || nodo.tiempoPreparacion() < actualMinimo.tiempoPreparacion())
                && !nodo.pedidoHecho()) {
            actualMinimo = nodo;
        }

        actualMinimo = buscarNodoRecursivo(nodo.hijoDerecha(), actualMinimo);

        return actualMinimo;
    }

}
