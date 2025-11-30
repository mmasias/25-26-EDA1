package entregas.munozManuel.src;

class Arbol{
    private Nodo raiz;
    private int cantidadNodos = 0;

    public void insertarPedido(Pedido pedido){
        if (raiz == null) {
            raiz = new Nodo(pedido);
            cantidadNodos++;
        }else{
            raiz.insertarHijo(new Nodo(pedido));
            cantidadNodos++;
        }
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
                System.out.println(nodosOrdenado.nombrePedido() + " elemnto con timepo: " + nodosOrdenado.tiempoPreparacion());
            }
        }
    }

    private int recorrerInOrden(Nodo nodo, Nodo[] arreglo, int indice) {
        if (nodo == null) return indice;

        indice = recorrerInOrden(nodo.hijoIzquerda(), arreglo, indice);

        arreglo[indice] = nodo;
        indice += 1;

        indice = recorrerInOrden(nodo.hijoDerecha(), arreglo, indice);

        return indice;
    }

}