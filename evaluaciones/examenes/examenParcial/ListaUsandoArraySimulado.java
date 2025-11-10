package evaluaciones.examenes.examenParcial;

public class ListaUsandoArraySimulado {
    private ArraySimulado datos;
    private int size;

    public ListaUsandoArraySimulado() {
        this.datos = new ArraySimulado(10);
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            return 0;
        return datos.get(index);
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size)
            return;
        datos.set(index, value);
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        datos.set(size, value);
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size)
            return;
        ensureCapacity(size + 1);

        int i = size - 1;
        while (i >= index) {
            int v = datos.get(i);
            datos.set(i + 1, v);
            i--;
        }
        datos.set(index, value);
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size)
            return 0;
        int old = datos.get(index);
        int i = index + 1;
        while (i < size) {
            int v = datos.get(i);
            datos.set(i - 1, v);
            i++;
        }
        size--;
        if (size < capacidad())
            datos.set(size, 0);
        return old;
    }

    private void ensureCapacity(int minCap) {
        if (minCap <= capacidad())
            return;
        int cap = capacidad();
        int nueva = cap * 2;
        if (nueva < minCap)
            nueva = minCap;
        if (nueva <= 0)
            nueva = 1;

        ArraySimulado nuevo = new ArraySimulado(nueva);
        int i = 0;
        while (i < size) {
            nuevo.set(i, datos.get(i));
            i++;
        }
        datos = nuevo;
    }

    private int capacidad() {
        return datos.length();
    }
}