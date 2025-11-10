public abstract class ListaAbstracta {
    protected NodoAbstracto primerNodo;
    protected int NUMERO_MAXIMO;

    public abstract NodoAbstracto get(int indice);
    public abstract int size();
}