public class ArrayComoLista {
    private int[] datos;
    private int size;

    public ArrayComoLista(int capacidad) {
        datos = new int[capacidad];
        size = 0;
    }

    public void agregar(int valor) {
        if (size < datos.length) {
            datos[size] = valor;
            size++;
        } else {
            System.out.println("La lista está llena");
        }
    }

    public void insertar(int posicion, int valor) {
        if (posicion < 0 || posicion > size || size == datos.length) {
            System.out.println("Posición inválida o lista llena");
            return;
        }
        for (int i = size; i > posicion; i--) {
            datos[i] = datos[i - 1];
        }
        datos[posicion] = valor;
        size++;
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= size) {
            System.out.println("Posición inválida");
            return;
        }
        for (int i = posicion; i < size - 1; i++) {
            datos[i] = datos[i + 1];
        }
        size--;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            System.out.println("Posición inválida");
            return -1;
        }
        return datos[posicion];
    }

    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(datos[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        ArrayComoLista lista = new ArrayComoLista(5);
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);
        lista.insertar(1, 15);
        lista.imprimir();
        lista.eliminar(2);
        lista.imprimir();
        System.out.println("Elemento en posición 1: " + lista.obtener(1));
    }
}
