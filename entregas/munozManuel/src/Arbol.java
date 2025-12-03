package entregas.munozManuel.src;

class Arbol {
    private Nodo raiz;
    private int cantidadNodos;

    public Arbol(){
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

    public int cantidadNodos(){
        return cantidadNodos;
    }

    public Nodo buscarNodoConTiempoMinimo() {
        return buscarNodoRecursivo(raiz, null);
    }

    public void recorrerArbol() {
        if (raiz == null || cantidadNodos == 0) {
            System.out.println("Árbol vacío");
            return;
        }

        Nodo[] nodosOrdenados = new Nodo[cantidadNodos];
        int indiceInicial = 0;

        recorrerInOrden(raiz, nodosOrdenados, indiceInicial);

        for (Nodo nodosOrdenado : nodosOrdenados) {
            if (nodosOrdenado != null) {
                System.out.println(
                        nodosOrdenado.nombreDelPedido() + " elemento con timepo: " + nodosOrdenado.tiempoPreparacion());
            }
        }
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
        if (nodo == null){
            return actualMinimo;
        }

        actualMinimo = buscarNodoRecursivo(nodo.hijoIzquerda(), actualMinimo);

        if ((actualMinimo == null || nodo.tiempoPreparacion() < actualMinimo.tiempoPreparacion()) && !nodo.pedidoHecho()) {
            actualMinimo = nodo;
        }

        actualMinimo = buscarNodoRecursivo(nodo.hijoDerecha(), actualMinimo);

        return actualMinimo;
    }

}