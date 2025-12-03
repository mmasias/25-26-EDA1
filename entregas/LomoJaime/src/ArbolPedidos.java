public class ArbolPedidos {
    private NodoArbol raiz;
    private int tamaño;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamaño = 0;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int size() {
        return tamaño;
    }
}