public class ListaEnlazada {

    private Nodo cabeza;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (cabeza == null) {
            cabeza = nuevo;
            tamanio++;
            return;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);
        tamanio++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamanio : "Indice fuera de rango";
        return localizar(indice).getValor();
    }

    public void modificar(int indice, int valor) {
        assert indice >= 0 && indice < tamanio : "Indice fuera de rango";
        localizar(indice).setValor(valor);
    }

    public int tamanio() {
        return tamanio;
    }

    private Nodo localizar(int indice) {
        Nodo actual = cabeza;
        int posicion = 0;

        while (posicion < indice) {
            actual = actual.getSiguiente();
            posicion++;
        }

        return actual;
    }
}