public class MinHeap {
    private Order[] heap;
    private int size;
    private long comparisons; 

    public MinHeap(int capacity) {
        this.heap = new Order[capacity + 1];
        this.size = 0;
        this.comparisons = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void insert(Order order) {
        size++;
        heap[size] = order;
        swim(size);
    }

    public Order extractMin() {
        Order min = heap[1];
        swap(1, size);
        heap[size] = null;
        size--;
        sink(1);
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        boolean moved = true;
        while (2 * k <= size && moved) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) j++;
            if (!greater(k, j)){}
                swap(k, j);
                k = j;
            } else {
                moved = false;
            }
        }
    }

    private boolean greater(int i, int j) {
        comparisons++; 
        return heap[i].remainingTime > heap[j].remainingTime;
    }

    private void swap(int i, int j) {
        Order temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}