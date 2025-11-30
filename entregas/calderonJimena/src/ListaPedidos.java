class ListaPedidos {
    private static final int CAPACIDAD_INICIAL = 4;
    private static final int FACTOR_REDIMENSION = 2;

    private Pedido[] datos;
    private int tamaño;
    private int capacidad;

    public ListaPedidos() {
        capacidad = CAPACIDAD_INICIAL;
        datos = new Pedido[capacidad];
        tamaño = 0;
    }

    public void agregar(Pedido p) {
        if (tamaño == capacidad) {
            redimensionar();
        }
        datos[tamaño] = p;
        tamaño++;
    }

    public Pedido obtener(int i) {
        if (i < 0 || i >= tamaño) return null;
        return datos[i];
    }

    public void establecer(int i, Pedido p) {
        if (i >= 0 && i < tamaño) {
            datos[i] = p;
        }
    }

    public Pedido eliminarUltimo() {
        if (tamaño == 0) return null;
        tamaño--;
        return datos[tamaño];
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    private void redimensionar() {
        capacidad *= FACTOR_REDIMENSION;
        Pedido[] nuevoArreglo = new Pedido[capacidad];
        for (int i = 0; i < tamaño; i++) {
            nuevoArreglo[i] = datos[i];
        }
        datos = nuevoArreglo;
    }
}