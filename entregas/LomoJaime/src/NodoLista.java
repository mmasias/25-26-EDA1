public class NodoLista extends NodoAbstracto {
    private ListaInt dato;

    public NodoLista(ListaInt dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
    }

    public ListaInt getDato() {
        return dato;
    }
}
