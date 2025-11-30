public class ColaDePrioridad {
    private Pedido[] heap;
    private int numElementos;
    private int comparaciones;

    public ColaDePrioridad() {
        this.heap = new Pedido[100];
        this.numElementos = 0;
        this.comparaciones = 0;
    }

    public void agregarPedido(Pedido nuevoPedido) {
        if (numElementos >= heap.length) {
            System.out.println("¡Error! La cola está llena.");
            return;
        }
        heap[numElementos] = nuevoPedido;
        numElementos++;
        siftUp(numElementos - 1);
    }

    public Pedido sacarPedidoPrioritario() {
        if (estaVacia()) {
            return null;
        }
        Pedido prioritario = heap[0];
        numElementos--;
        Pedido ultimo = heap[numElementos]; 
        heap[0] = ultimo;
        heap[numElementos] = null; 
        if (numElementos > 0) {
            siftDown(0);
        }
        return prioritario;
    }

    public boolean estaVacia() {
        return numElementos == 0;
    }

    public int getNumeroPedidos() {
        return numElementos;
    }

    public int getComparacionesTotales() {
        return comparaciones;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int padreIndex = (index - 1) / 2;
            if (esMenor(heap[index], heap[padreIndex])) {
                intercambiar(index, padreIndex);
                index = padreIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index * 2 + 1 < numElementos) {
            int hijoIzq = index * 2 + 1;
            int hijoDer = index * 2 + 2;
            int hijoMasRapido = hijoIzq;
            if (hijoDer < numElementos && esMenor(heap[hijoDer], heap[hijoIzq])) {
                hijoMasRapido = hijoDer;
            }
            if (esMenor(heap[hijoMasRapido], heap[index])) {
                intercambiar(index, hijoMasRapido);
                index = hijoMasRapido;
            } else {
                break;
            }
        }
    }

    private boolean esMenor(Pedido p1, Pedido p2) {
        comparaciones++;
        return p1.getTiempoDePreparacion() < p2.getTiempoDePreparacion();
    }

    private void intercambiar(int i, int j) {
        Pedido temporal = heap[i];
        heap[i] = heap[j];
        heap[j] = temporal;
    }
}
