
public class Lista {
    private Nodo cabeza;
    private int tamaño;

    public Lista() {
        cabeza = null;
        tamaño = 0;
    }

    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño
                : "Mal: el índice esta fuera de rango";

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño
                : "Mal: el índice esta fuera de rango";

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        actual.setDato(nuevoValor);
    }

    public int tamaño() {
        return tamaño;
    }
}
