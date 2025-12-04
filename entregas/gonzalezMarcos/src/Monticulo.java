public class Monticulo {
    private Pedido[] monticulo;
    private int tamaño;
    private long comparaciones;

    public Monticulo(int capacity) {
        this.monticulo = new Pedido[capacity];
        this.tamaño = 0;
        this.comparaciones = 0;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public int size() {
        return tamaño;
    }

    public long getComparaciones() {
        return comparaciones;
    }

    public void insert(Pedido p) {
        if (tamaño >= monticulo.length) {
            resize();
        }
        monticulo[tamaño] = p;
        siftUp(tamaño);
        tamaño++;
    }

    public Pedido extractMin() {
        if (tamaño == 0)
            return null;
        Pedido min = monticulo[0];
        monticulo[0] = monticulo[tamaño - 1];
        tamaño--;
        if (tamaño > 0) {
            siftDown(0);
        }
        return min;
    }

    private void siftUp(int index) {
        boolean continuar = true;
        while (index > 0 && continuar) {
            int padreIndex = (index - 1) / 2;
            comparaciones++;
            if (monticulo[index].tiempoRestante < monticulo[padreIndex].tiempoRestante) {
                Pedido temp = monticulo[index];
                monticulo[index] = monticulo[padreIndex];
                monticulo[padreIndex] = temp;
                index = padreIndex;
            } else {
                continuar = false;
            }
        }
    }

    private void siftDown(int index) {
        int minIndex = index;
        int hijoIzquierda = 2 * index + 1;
        int hijoDerecha = 2 * index + 2;

        if (hijoIzquierda < tamaño) {
            comparaciones++;
            if (monticulo[hijoIzquierda].tiempoRestante < monticulo[minIndex].tiempoRestante) {
                minIndex = hijoIzquierda;
            }
        }

        if (hijoDerecha < tamaño) {
            comparaciones++;
            if (monticulo[hijoDerecha].tiempoRestante < monticulo[minIndex].tiempoRestante) {
                minIndex = hijoDerecha;
            }
        }

        if (index != minIndex) {
            Pedido temp = monticulo[index];
            monticulo[index] = monticulo[minIndex];
            monticulo[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    private void resize() {
        Pedido[] newMonticulo = new Pedido[monticulo.length * 2];
        for (int i = 0; i < monticulo.length; i++) {
            newMonticulo[i] = monticulo[i];
        }
        monticulo = newMonticulo;
    }
}