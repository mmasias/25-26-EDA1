public class Insertar {
    private int[] array;
    private int size;
    private static final int CAPACIDAD_INICIAL = 10;

    public Insertar() {
        array = new int[CAPACIDAD_INICIAL];
        size = 0;
    }

    public void insertar(int valor) {
        if (size == array.length) {
            int[] nuevoArray = new int[array.length * 2];
            System.arraycopy(array, 0, nuevoArray, 0, array.length);
            array = nuevoArray;
        }
        array[size] = valor;
        size++;
    }

    public void insertarEnPosicion(int valor, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }

        if (size == array.length) {
            int[] nuevoArray = new int[array.length * 2];
            System.arraycopy(array, 0, nuevoArray, 0, array.length);
            array = nuevoArray;
        }

        for (int i = size; i > posicion; i--) {
            array[i] = array[i-1];
        }
        array[posicion] = valor;
        size++;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        return array[posicion];
    }

    public int tamaño() {
        return size;
    }
}
