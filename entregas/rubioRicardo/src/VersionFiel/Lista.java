public class Lista {
    Nodo inicial;
    int tamaño;

    public Lista() {
        inicial = null;
        tamaño = 0;
    }

    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (inicial == null) {
            inicial = nuevo;
        } else {
            Nodo actual = inicial;
            while (actual.getSiguienteNodo() != null) {
                actual = actual.getSiguienteNodo();
            }
            actual.setSiguienteNodo(nuevo);
        }
        tamaño++;
    }

    public int obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return 0;
        }
        Nodo actual = inicial;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguienteNodo();
        }
        return actual.getDato();
    }

    public void modificar(int indice, int nuevoValor) {
        if (indice < 0 || indice >= tamaño) {
            return;
        }
        Nodo actual = inicial;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguienteNodo();
        }
        actual.setDato(nuevoValor);
    }

    public void eliminar(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return;
        }
        if (indice == 0) {
            inicial = inicial.getSiguienteNodo();
        } else {
            Nodo actual = inicial;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguienteNodo();
            }
            actual.setSiguienteNodo(actual.getSiguienteNodo().getSiguienteNodo());
        }
        tamaño--;
    }

    public void mostrar() {
        Nodo actual = inicial;
        System.out.print("[ ");
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguienteNodo();
        }
        System.out.println("]");
    }

    public int getTamaño() {
        return tamaño;
    }
}
