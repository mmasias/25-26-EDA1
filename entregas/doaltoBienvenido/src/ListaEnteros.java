class ListaEnteros {
    private int[] elementos;
    private int cantidadElementos;
    private static final int TAMANIO_INICIAL = 1;

    public ListaEnteros() {
        elementos = new int[TAMANIO_INICIAL];
        cantidadElementos = 0;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void mostrarPosicionesValidas() {
        System.out.print("Posiciones válidas para insertar: ");
        for (int i = 0; i <= cantidadElementos; i++) {
            System.out.print(i);
            if (i < cantidadElementos) System.out.print(", ");
        }
        System.out.println();
    }

    public void insertar(int valor, int posicion) {
        if (cantidadElementos == elementos.length) {
            expandir();
        }

        if (posicion < 0 || posicion > cantidadElementos) {
            System.out.println("Posición inválida.");
            return;
        }

        for (int i = cantidadElementos - 1; i >= posicion; i--) {
            elementos[i + 1] = elementos[i];
        }

        elementos[posicion] = valor;
        cantidadElementos++;
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= cantidadElementos) {
            System.out.println("Posición inválida.");
            return;
        }

        for (int i = posicion; i < cantidadElementos - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        elementos[cantidadElementos - 1] = 0;
        cantidadElementos--;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidadElementos) {
            System.out.println("Posición inválida.");
            return -1;
        }
        return elementos[posicion];
    }

    private void expandir() {
        int[] nuevoArray = new int[elementos.length + 1];
        for (int i = 0; i < elementos.length; i++) {
            nuevoArray[i] = elementos[i];
        }
        elementos = nuevoArray;
    }

    public void mostrar() {
        System.out.print("[");
        for (int i = 0; i < cantidadElementos; i++) {
            System.out.print(elementos[i]);
            if (i < cantidadElementos - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}