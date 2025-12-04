public class ColaPedidos {

    private Pedido[] cola;
    private int size;

    public long comparaciones = 0;

    public ColaPedidos(int capacidadMaxima) {
        cola = new Pedido[capacidadMaxima];
        size = 0;
    }

    public void insertar(Pedido p) {
        cola[size] = p;
        size++;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    
    public Pedido extraerMin() {

        if (size == 0) return null;

        int minIndex = 0;

        for (int i = 1; i < size; i++) {
            comparaciones++;
            if (cola[i].getTiempoPreparacion() < cola[minIndex].getTiempoPreparacion()) {
                minIndex = i;
            }
        }

        Pedido min = cola[minIndex];

        
        for (int i = minIndex; i < size - 1; i++) {
            cola[i] = cola[i + 1];
        }

        size--;
        return min;
    }
}
