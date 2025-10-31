package NuestraPropuesta;

public class Array {
    private class Nodo {
        private int dato;
        private Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        public int getDato() {
            return dato;
        }

        public void setDato(int dato) {
            this.dato = dato;
        }

        public Nodo getSiguienteNodo() {
            return siguiente;
        }

        public void setSiguienteNodo(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }

    private Nodo inicial;
    private int tamañoFijo;

    public Array() {
        inicial = null;
        tamañoFijo = 0;
    }

    public void fijarTamaño(int tamaño) {
        if (tamañoFijo > 0) return;
        tamañoFijo = tamaño;
        Nodo actual = null;
        for (int i = 0; i < tamañoFijo; i++) {
            Nodo nuevo = new Nodo(0);
            if (inicial == null) {
                inicial = nuevo;
                actual = inicial;
            } else {
                actual.setSiguienteNodo(nuevo);
                actual = nuevo;
            }
        }
    }

    public boolean tipoDato() {
        return true; 
    }

    public void Acceso(int indice) {
        if (indice < 0 || indice >= tamañoFijo) {
            System.out.println("Índice fuera de rango");
            return;
        }
        Nodo actual = inicial;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguienteNodo();
        }
        System.out.println("Valor en posición " + indice + ": " + actual.getDato());
    }

    public void modificar(int indice, int valor) {
        if (indice < 0 || indice >= tamañoFijo) return;
        Nodo actual = inicial;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguienteNodo();
        }
        actual.setDato(valor);
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
        return tamañoFijo;
    }
}
