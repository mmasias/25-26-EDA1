class ArrayDeListas {
    private ListaEnteros[] listas;
    private int cantidadListas;
    private static final int TAMANIO_INICIAL = 1;

    public ArrayDeListas() {
        listas = new ListaEnteros[TAMANIO_INICIAL];
        cantidadListas = 0;
    }

    public int getCantidadListas() {
        return cantidadListas;
    }

    public void insertarLista(ListaEnteros lista, int posicion) {
        if (posicion < 0 || posicion > cantidadListas) {
            System.out.println("Posición inválida.");
            return;
        }

        if (cantidadListas == listas.length) {
            expandir();
        }

        for (int i = cantidadListas - 1; i >= posicion; i--) {
            listas[i + 1] = listas[i];
        }

        listas[posicion] = lista;
        cantidadListas++;
    }

    public void eliminarLista(int posicion) {
        if (posicion < 0 || posicion >= cantidadListas) {
            System.out.println("Posición inválida.");
            return;
        }

        for (int i = posicion; i < cantidadListas - 1; i++) {
            listas[i] = listas[i + 1];
        }

        listas[cantidadListas - 1] = null;
        cantidadListas--;
    }

    public ListaEnteros obtenerLista(int posicion) {
        if (posicion < 0 || posicion >= cantidadListas) {
            System.out.println("Posición inválida.");
            return null;
        }
        return listas[posicion];
    }

    private void expandir() {
        ListaEnteros[] nuevoArray = new ListaEnteros[listas.length + 1];
        for (int i = 0; i < listas.length; i++) {
            nuevoArray[i] = listas[i];
        }
        listas = nuevoArray;
    }

    public void mostrar() {
        System.out.println("Contenido del array de listas:");
        for (int i = 0; i < cantidadListas; i++) {
            System.out.print("Lista " + i + ": ");
            listas[i].mostrar();
            System.out.println();
        }
    }
}
