package Array.jaime;
public class ArrayDeArrays extends ArrayAbstracto {

    public ArrayDeArrays(int numeroMaximo) {
        this.NUMERO_MAXIMO = numeroMaximo;
        this.primerNodo = null;
    }
    public void add(ArrayInt lista) {
        NodoArray nuevo = new NodoArray(lista, size());
        if (primerNodo == null) {
            primerNodo = nuevo;
        } else {
            NodoArray actual = (NodoArray) primerNodo;
            while (actual.getSiguiente() != null) {
                actual = (NodoArray) actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
    public NodoArray get(int indice) {
        NodoArray actual = (NodoArray) primerNodo;
        while (actual != null) {
            if (actual.getPosicion() == indice) {
                return actual;
            }
            actual = (NodoArray) actual.getSiguiente();
        }
        return null;
    }
    public int size() {
        int contador = 0;
        NodoArray actual = (NodoArray) primerNodo;
        while (actual != null) {
            contador++;
            actual = (NodoArray) actual.getSiguiente();
        }
        return contador;
    }
}
