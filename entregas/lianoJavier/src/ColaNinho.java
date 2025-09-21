public class ColaNinho {

    private Nodo frente;
    private Nodo ultimo;

    ColaNinho() {
        this.frente = null;
        this.ultimo = null;
    }

    public void enqueue(Ninho valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (estaVacia()) {
            frente = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
    }

    public Ninho dequeue() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Ninho valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            ultimo = null;
        }
        return valor;
    }

    public Ninho peek() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    private static class Nodo {
        Ninho dato;
        Nodo siguiente;

        public Nodo(Ninho dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
}