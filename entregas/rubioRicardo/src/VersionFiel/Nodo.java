public class Nodo {
    int dato;
    Nodo siguiente;

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
