public class Elemento{

    private final int INDICE;
    private int valor;

    public Elemento(int indice, int valor) {
        this.INDICE = indice;
        this.valor = valor;
    }

    public int obtenerIndice() {
        return INDICE;
    }

    public int valor() {
        return valor;
    }

    public void darValor(int valor) {
        this.valor = valor;
    }

    public String toString() {
        return "INDICE " + INDICE + ": " + valor;
    }
    
}