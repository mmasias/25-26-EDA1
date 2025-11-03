public class ArrayComoLista {
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
        datos[tamaño] = valor;
        tamaño++;
    }

    public void insertar(int posicion, int valor) {
        if (posicion < 0 || posicion > tamaño) {
            System.out.println("Posición fuera de rango.");
        } else {
            asegurarCapacidad(tamaño + 1);
            for (int i = tamaño; i > posicion; i--) {
                datos[i] = datos[i - 1];
            }
            datos[posicion] = valor;
            tamaño++;
        }
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            System.out.println("Posición fuera de rango.");
            return -1;
        } else {
            return datos[posicion];
        }
    }

    public void establecer(int posicion, int valor) {
        if (posicion < 0 || posicion >= tamaño) {
            System.out.println("Posición fuera de rango.");
        } else {
            datos[posicion] = valor;
        }
    }

    public int eliminarEn(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            System.out.println("Posición fuera de rango.");
            return -1;
        } else {
            int eliminado = datos[posicion];
            for (int i = posicion; i < tamaño - 1; i++) {
                datos[i] = datos[i + 1];
            }
            tamaño--;
            return eliminado;
        }
    }

    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(datos[i]);
            if (i < tamaño - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private void asegurarCapacidad(int minimo) {
        if (minimo > datos.length) {
            int nuevaCapacidad = Math.max(datos.length * 2, minimo);
            int[] nuevo = new int[nuevaCapacidad];
            for (int i = 0; i < tamaño; i++) {
                nuevo[i] = datos[i];
            }
            datos = nuevo;
        }
    }
}