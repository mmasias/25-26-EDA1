class MinHeap {
    private Pedido[] heap;
    private int tamaño;
    private int capacidad;
    private int comparaciones = 0;

    public MinHeap(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tamaño = 0;
        this.heap = new Pedido[capacidad];
    }

    public boolean esVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public int comparaciones() {
        return comparaciones;
    }

    public void insertar(Pedido pedido) {
        if (tamaño == capacidad) redimensionar();

        heap[tamaño] = pedido;
        int actual = tamaño;
        tamaño++;
        flotar(actual);
    }

    private void flotar(int indice) {
        int padre = (indice - 1) / 2;

        while (indice > 0 && heap[indice].esMasUrgenteQue(heap[padre])) {
            comparaciones++;
            intercambiar(indice, padre);
            padre = (indice - 1) / 2;
        }
    }

    public Pedido extraerMinimo() {
        if (tamaño == 0) return null;

        Pedido min = heap[0];
        heap[0] = heap[tamaño - 1];
        heap[tamaño - 1] = null;
        tamaño--;
        hundir(0);
        return min;
    }

    private void hundir(int indice) {
        int menor = indice;
        int izquierda = 2 * indice + 1;
        int derecha = 2 * indice + 2;

        if (izquierda < tamaño) {
            comparaciones++;
            if (heap[izquierda].esMasUrgenteQue(heap[menor])) menor = izquierda;
        }
        if (derecha < tamaño) {
            comparaciones++;
            if (heap[derecha].esMasUrgenteQue(heap[menor])) menor = derecha;
        }
        if (menor != indice) {
            intercambiar(indice, menor);
            hundir(menor);
        }
    }

    private void intercambiar(int i, int j) {
        Pedido temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void redimensionar() {
        tamaño *= 2;
        Pedido[] nuevo = new Pedido[tamaño];
        for (int i = 0; i < tamaño; i++) nuevo[i] = heap[i];
        heap = nuevo;
    }
}