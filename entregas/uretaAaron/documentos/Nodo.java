
public class Nodo {

    private int valor;
    private Nodo siguiente;

    public Nodo(int valorInicial) {
        this.valor = valorInicial;
        this.siguiente = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int nuevoValor) {
        this.valor = nuevoValor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo nodo) {
        this.siguiente = nodo;
    }
}
