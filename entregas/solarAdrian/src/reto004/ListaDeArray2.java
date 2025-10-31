package reto004;

class ListaDeArray2 {
    private int[] datos;
    private int cantidad;

    public ListaDeArray2(int capacidadInicial) {
        if (capacidadInicial < 1) capacidadInicial = 1;
        datos = new int[capacidadInicial];
        cantidad = 0;
    }

    public int size() { return cantidad; }
    public boolean isEmpty() { return cantidad == 0; }
    public int capacity() { return datos.length; }

    public int get(int i) { return datos[i]; }
    public void set(int i, int v) { datos[i] = v; }

    public void add(int v) {
        asegurarCapacidad(cantidad + 1);
        datos[cantidad++] = v;
    }

    public void insert(int i, int v) {
        asegurarCapacidad(cantidad + 1);
        for (int k = cantidad; k > i; k--) datos[k] = datos[k - 1];
        datos[i] = v;
        cantidad++;
    }

    public int removeAt(int i) {
        int val = datos[i];
        for (int k = i; k < cantidad - 1; k++) datos[k] = datos[k + 1];
        cantidad--;
        return val;
    }

    public void clear() { cantidad = 0; }

    public int[] toArray() {
        int[] r = new int[cantidad];
        for (int i = 0; i < cantidad; i++) r[i] = datos[i];
        return r;
    }

    private void asegurarCapacidad(int minCap) {
        if (minCap <= datos.length) return;
        int nuevaCap = Math.max(datos.length * 2, minCap);
        int[] nuevo = new int[nuevaCap];
        for (int i = 0; i < cantidad; i++) nuevo[i] = datos[i];
        datos = nuevo;
    }

    public static void main(String[] args) {
        ListaDeArray2 lista = new ListaDeArray2(4);

        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.insert(1, 99);  
        lista.removeAt(3);     
        for (int i = 0; i < lista.size(); i++) System.out.print(lista.get(i) + " ");
        System.out.println();

        System.out.println("size=" + lista.size() + " capacity=" + lista.capacity());

        lista.add(50);
        lista.add(60);
        lista.add(70);
        lista.add(80);
        lista.add(90);
        lista.add(100);

        for (int i = 0; i < lista.size(); i++) System.out.print(lista.get(i) + " ");
        System.out.println();
        System.out.println("size=" + lista.size() + " capacity=" + lista.capacity());
    }
}


