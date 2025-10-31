package reto004;


class ListaDeArray {
    private int[] datos;
    private int cantidad;

    public ListaDeArray(int capacidad) {
        datos = new int[capacidad];
        cantidad = 0;
    }

    public int size() { return cantidad; }
    public int get(int i) { return datos[i]; }
    public void set(int i, int v) { datos[i] = v; }

    public void add(int v) {
        asegurarEspacio();
        datos[cantidad] = v;
        cantidad++;
    }

    public void insert(int i, int v) {
        asegurarEspacio();
        for (int k = cantidad; k > i; k--) datos[k] = datos[k - 1];
        datos[i] = v;
        cantidad++;
    }

    public void removeAt(int i) {
        for (int k = i; k < cantidad - 1; k++) datos[k] = datos[k + 1];
        cantidad--;
    }

    private void asegurarEspacio() {
        if (cantidad == datos.length) {
            int[] nuevo = new int[datos.length * 2];
            for (int i = 0; i < datos.length; i++) nuevo[i] = datos[i];
            datos = nuevo;
        }
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) System.out.print(datos[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        ListaDeArray lista = new ListaDeArray(4);
        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.insert(1, 99);
        lista.removeAt(4);
        lista.mostrar(); 
    }
}
