package Array;

class ListaEnlazada {
    private Nodo cabeza;
    private int total;

    public ListaEnlazada() {
        this.cabeza = null;
        this.total = 0;
    }

    public void agregar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        total++;
    }

    public int obtener(int indice) {
        assert (indice >= 0 && indice < total) : "Índice fuera de rango";
        return localizarNodo(indice).getDato();
    }

    public void modificar(int indice, int valor) {
        assert (indice >= 0 && indice < total) : "Índice fuera de rango";
        localizarNodo(indice).setDato(valor);
    }

    private Nodo localizarNodo(int indice) {
        Nodo actual = cabeza;
        for (int contador = 0; contador < indice; contador++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public int getTotal() { 
        return total; 
    }
}