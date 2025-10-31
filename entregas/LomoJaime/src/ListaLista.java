public class ListaLista extends ListaAbstracta {

    public ListaLista(int numeroMaximo) {
        this.NUMERO_MAXIMO = numeroMaximo;
        this.primerNodo = null;
    }
    public void add(ListaInt lista) {
        NodoLista nuevo = new NodoLista(lista, size());
        if (primerNodo == null) {
            primerNodo = nuevo;
        } else {
            NodoLista actual = (NodoLista) primerNodo;
            while (actual.getSiguiente() != null) {
                actual = (NodoLista) actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
    public NodoLista get(int indice) {
        NodoLista actual = (NodoLista) primerNodo;
        while (actual != null) {
            if (actual.getPosicion() == indice) {
                return actual;
            }
            actual = (NodoLista) actual.getSiguiente();
        }
        return null;
    }
    public int size() {
        int contador = 0;
        NodoLista actual = (NodoLista) primerNodo;
        while (actual != null) {
            contador++;
            actual = (NodoLista) actual.getSiguiente();
        }
        return contador;
    }
}
