class MinHeap {
    private ListaPedidos heap;
    private int comparaciones;

    public MinHeap() {
        heap = new ListaPedidos();
        comparaciones = 0;
    }

    public void insertar(Pedido p) {
        heap.agregar(p);
        flotar(heap.tamaño() - 1);
    }

    public Pedido extraerMin() {
        if (heap.estaVacia()) return null;
        Pedido min = heap.obtener(0);
        Pedido ultimo = heap.eliminarUltimo();
        if (!heap.estaVacia()) {
            heap.establecer(0, ultimo);
            hundir(0);
        }
        return min;
    }

    public boolean estaVacia() { return heap.estaVacia(); }
    public int size() { return heap.tamaño(); }
    public int getComparaciones() { return comparaciones; }

    private void flotar(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            comparaciones++;
            if (heap.obtener(i).getTiempoPreparacion() < heap.obtener(padre).getTiempoPreparacion()) {
                intercambiar(i, padre);
                i = padre;
            } else {
                break;
            }
        }
    }

    private void hundir(int i) {
        int menor = i;
        int izq = 2 * i + 1;
        int der = 2 * i + 2;

        if (izq < heap.tamaño()) {
            comparaciones++;
            if (heap.obtener(izq).getTiempoPreparacion() < heap.obtener(menor).getTiempoPreparacion()) {
                menor = izq;
            }
        }

        if (der < heap.tamaño()) {
            comparaciones++;
            if (heap.obtener(der).getTiempoPreparacion() < heap.obtener(menor).getTiempoPreparacion()) {
                menor = der;
            }
        }

        if (menor != i) {
            intercambiar(i, menor);
            hundir(menor);
        }
    }

    private void intercambiar(int i, int j) {
        Pedido tmp = heap.obtener(i);
        heap.establecer(i, heap.obtener(j));
        heap.establecer(j, tmp);
    }
}