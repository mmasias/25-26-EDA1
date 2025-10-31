class ArrayComoLista {
    private int[] datos;
    private int tamaño;

    public ArrayComoLista(int capacidadInicial) {
        if (capacidadInicial <= 0) capacidadInicial = 4;
        this.datos = new int[capacidadInicial];
        this.tamaño = 0;
    }

    public int obtenerTamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public void agregar(int valor) {
        asegurarCapacidad(tamaño + 1);
        datos[tamaño++] = valor;
    }

    public void insertar(int posicion, int valor) {
        if (posicion < 0 || posicion > tamaño) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        asegurarCapacidad(tamaño + 1);
        for (int i = tamaño; i > posicion; i--) {
            datos[i] = datos[i - 1];
        }
        datos[posicion] = valor;
        tamaño++;
    }

    public int obtener(int posicion) {
        comprobarIndice(posicion);
        return datos[posicion];
    }

    public void establecer(int posicion, int valor) {
        comprobarIndice(posicion);
        datos[posicion] = valor;
    }

    public int eliminarEn(int posicion) {
        comprobarIndice(posicion);
        int eliminado = datos[posicion];
        for (int i = posicion; i < tamaño - 1; i++) {
            datos[i] = datos[i + 1];
        }
        tamaño--;
        return eliminado;
    }

    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(datos[i]);
            if (i < tamaño - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private void asegurarCapacidad(int minimo) {
        if (minimo <= datos.length) return;
        int nuevaCapacidad = Math.max(datos.length * 2, minimo);
        int[] nuevo = new int[nuevaCapacidad];
        System.arraycopy(datos, 0, nuevo, 0, tamaño);
        datos = nuevo;
    }

    private void comprobarIndice(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
    }
}

public class EjemploArrayComoLista {
    public static void main(String[] args) {
        ArrayComoLista lista = new ArrayComoLista(4);
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(40);
        lista.insertar(2, 30);
        lista.imprimir();
        lista.establecer(0, 99);
        lista.imprimir();
        int eliminado = lista.eliminarEn(1);
        System.out.println("Elemento eliminado: " + eliminado);
        lista.imprimir();
    }
}
