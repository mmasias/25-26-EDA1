package entregas.munozManuel.src;

class Arbol {
    private Nodo raiz;
    private int cantidadNodos = 0;

    public Arbol(){

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

    public Nodo buscarNodoMinimo() {
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
                        nodosOrdenado.nombrePedido() + " elemento con timepo: " + nodosOrdenado.tiempoPreparacion());
            }
        }
    }

    private int recorrerInOrden(Nodo nodo, Nodo[] arreglo, int indice) {
        if (nodo == null)
            return indice;

        indice = recorrerInOrden(nodo.hijoIzquerda(), arreglo, indice);

        arreglo[indice] = nodo;
        indice += 1;

        indice = recorrerInOrden(nodo.hijoDerecha(), arreglo, indice);

        return indice;
    }

    private Nodo buscarNodoRecursivo(Nodo nodo, Nodo actualMin) {
        if (nodo == null)
            return actualMin;

        actualMin = buscarNodoRecursivo(nodo.hijoIzquerda(), actualMin);

        if (actualMin == null || nodo.tiempoPreparacion() < actualMin.tiempoPreparacion()) {
            actualMin = nodo;
        }

        actualMin = buscarNodoRecursivo(nodo.hijoDerecha(), actualMin);

        return actualMin;
    }

}