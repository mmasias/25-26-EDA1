public class ArrayPedidos {
    private Pedido[] datos;
    private int size;

    public ArrayPedidos() {
        datos = new Pedido[10];
        size = 0;
    }

    public void add(Pedido p) {
        if (size == datos.length) {
            resize();
        }
        datos[size] = p;
        size++;
    }

    public Pedido get(int i) {
        return datos[i];
    }

    public int length() {
        return size;
    }

    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            datos[i] = datos[i + 1];
        }
        size--;
    }

    private void resize() {
        Pedido[] nuevo = new Pedido[datos.length * 2];
        for (int i = 0; i < datos.length; i++) {
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }
}
