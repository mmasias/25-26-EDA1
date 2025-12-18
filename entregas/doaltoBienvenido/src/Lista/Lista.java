package Lista;
import Array.Array;

public class Lista {
    private Array datos;
    private int cantidad;

    public Lista() {
        this.datos = new Array(1);
        this.cantidad = 0;
    }

    public void insertar(int valor, int posicion) {
        assert (posicion >= 0 && posicion <= cantidad) : "Posición de inserción fuera de límites";
        
        if (cantidad == datos.longitud()) {
            redimensionar();
        }

        for (int indice = cantidad - 1; indice >= posicion; indice--) {
            datos.set(indice + 1, datos.get(indice));
        }

        datos.set(posicion, valor);
        cantidad++;
    }

    public void eliminar(int posicion) {
        assert (posicion >= 0 && posicion < cantidad) : "Posición de eliminación inválida";

        for (int indice = posicion; indice < cantidad - 1; indice++) {
            datos.set(indice, datos.get(indice + 1));
        }
        cantidad--;
    }

    private void redimensionar() {
        Array nuevoArray = new Array(datos.longitud() + 1);
        for (int indice = 0; indice < datos.longitud(); indice++) {
            nuevoArray.set(indice, datos.get(indice));
        }
        datos = nuevoArray;
    }

    public int obtener(int posicion) {
        assert (posicion >= 0 && posicion < cantidad) : "Índice de consulta inválido";
        return datos.get(posicion);
    }

    public void imprimir() {
        System.out.print("[");
        for (int indice = 0; indice < cantidad; indice++) {
            System.out.print(datos.get(indice) + (indice < cantidad - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}