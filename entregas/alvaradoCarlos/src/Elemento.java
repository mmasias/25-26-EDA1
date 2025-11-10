public class Elemento{

    private Elemento siguiente;
    private int valor;

    public Elemento(int valor) {
        this.siguiente = null;
        this.valor = valor;
    }

    public int valor() {
        return valor;
    }

    public void darValor(int valor) {
        this.valor = valor;
    }

    public Elemento siguiente() {
        return siguiente;
    }

    public void setSiguiente(Elemento siguiente) {
        this.siguiente = siguiente;
    }
}