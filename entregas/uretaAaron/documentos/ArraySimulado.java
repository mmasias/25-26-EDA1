
public class ArraySimulado {

    private int capacidad;
    private Nodo inicio;

    public ArraySimulado(int capacidad) {
        if (capacidad < 0) capacidad = 0;
        this.capacidad = capacidad;
        construir(capacidad);
    }

    private void construir(int n) {
        Nodo anterior = null;
        for (int i = 0; i < n; i++) {
            Nodo nuevo = new Nodo(0);
            if (i == 0) inicio = nuevo;
            else anterior.setSiguiente(nuevo);
            anterior = nuevo;
        }
    }

    public int length() {
        return capacidad;
    }

    private Nodo nodoEn(int pos) {
        if (pos < 0 || pos >= capacidad) return null;
        Nodo aux = inicio;
        int k = 0;
        while (k < pos) {
            aux = aux.getSiguiente();
            k++;
        }
        return aux;
    }

    public int get(int index) {
        Nodo n = nodoEn(index);
        return n == null ? 0 : n.getValor();
    }

    public void set(int index, int valor) {
        Nodo n = nodoEn(index);
        if (n != null) n.setValor(valor);
    }
}
