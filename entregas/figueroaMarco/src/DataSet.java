public class DataSet {
    private int[] array;
    private int size;
    private int capacity;
    
    public DataSet(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
    }
    
    public DataSet() {
        this(20);
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Índice fuera de rango: " + index);
        }
        return array[index];
    }
    
    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        array[index] = value;
    }
    
    public void add(int value) {
        if (size >= capacity) {
            resize();
        }
        array[size] = value;
        size++;
    }
    
    public void add(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        if (size >= capacity) {
            resize();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }
    
    public int remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Índice fuera de rango: " + index);
        }
        int removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedValue;
    }
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return capacity;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void resize() {
        capacity = capacity * 2;
        int[] newArray = new int[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    
    public int getBefore(int index) {
        if (index <= 0 || index >= size) {
            System.out.println("No hay elemento anterior para el índice: " + index);
        }
        return index - 1;
    }
    
    public int getAfter(int index) {
        if (index < 0 || index >= size - 1) {
            System.out.println("No hay elemento posterior para el índice: " + index);
        }
        return index + 1;
    }
    
    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

