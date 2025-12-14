package uroboros.lista;

import arrayConLista.a000.Array;

public class Lista {
    private Array elementos;
    private int tamaño;

    public Lista() {
        elementos = new Array(10);
        tamaño = 0;
    }

    public void agregar(int valor) {
        if (tamaño == elementos.longitud()) {
            redimensionar();
        }
        elementos.set(tamaño, valor);
        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        return elementos.get(indice);
    }

    public int tamaño() {
        return tamaño;
    }

    public void eliminar(int indice) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        for (int i = indice; i < tamaño - 1; i++) {
            elementos.set(i, elementos.get(i + 1));
        }
        tamaño--;
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        elementos.set(indice, nuevoValor);
    }

    private void redimensionar() {
        Array nuevoArray = new Array(elementos.longitud() * 2);
        for (int i = 0; i < elementos.longitud(); i++) {
            nuevoArray.set(i, elementos.get(i));
        }
        elementos = nuevoArray;
    }

    public void mostrarEstructura() {
        System.out.println("Índice | Dato ");
        for (int i = 0; i < tamaño; i++) {
            System.out.println("  " + i + "    |  " + elementos.get(i));
        }
    }
}
