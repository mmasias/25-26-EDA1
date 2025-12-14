package Lista;

import Array.Array;

public class Lista {
    private Array elementos;
    private int cantidadElementos;
    private static final int TAMANIO_INICIAL = 1;

    public Lista() {
        elementos = new Array(TAMANIO_INICIAL);
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
        if (cantidadElementos == elementos.longitud()) {
            expandir();
        }

        if (posicion < 0 || posicion > cantidadElementos) {
            System.out.println("Posición inválida.");
            return;
        }

        for (int i = cantidadElementos - 1; i >= posicion; i--) {
            elementos.set((i + 1), elementos.get(i));
        }

        elementos.set(posicion, valor);
        cantidadElementos++;
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= cantidadElementos) {
            System.out.println("Posición inválida.");
            return;
        }

        for (int i = posicion; i < cantidadElementos - 1; i++) {
            elementos.set(i, elementos.get(i + 1));
        }

        elementos.set(cantidadElementos - 1, 0);
        cantidadElementos--;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidadElementos) {
            System.out.println("Posición inválida.");
            return -1;
        }
        return elementos.get(posicion);
    }

    private void expandir() {
        Array nuevoArray = new Array(elementos.longitud() + 1);
        for (int i = 0; i < elementos.longitud(); i++) {
            nuevoArray.set(i, elementos.get(i));
        }
        elementos = nuevoArray;
    }

    public void mostrar() {
        System.out.print("[");
        for (int i = 0; i < cantidadElementos; i++) {
            System.out.print(elementos.get(i));
            if (i < cantidadElementos - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
