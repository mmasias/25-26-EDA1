class Arbol{
    private Nodo raiz;

    public void insertarNodo(Nodo nodo){
        if (raiz == null) {
            raiz = nodo;
        }else{
            if(raiz.tiempoPreparacion() >= nodo.tiempoPreparacion()){
                raiz.hijoIzquerda(nodo);
            }else{
                raiz.hijoDerecha(nodo);
            }
        }
    }

    private void recorerArbol(){
    }

    private void organizarArbol(){

    }
}