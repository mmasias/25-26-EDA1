package l000;

public class Lista {
    private int[] elementos;
    private int tamaño;

    public Lista() {
        elementos = new int[10];
        tamaño = 0;
    }

    public void agregar(int valor) {
        if (tamaño == elementos.length) {
            redimensionar();
        }
        elementos[tamaño] = valor;
        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        return elementos[indice];
    }

    public int tamaño() {
        return tamaño;
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        elementos[indice] = nuevoValor;
    }

    public void eliminar(int indice) {
        assert indice >= 0 && indice < tamaño : "Precondición violada: índice fuera de rango";
        for (int i = indice; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tamaño--;
    }

    private void redimensionar() {
        int[] nuevoArray = new int[elementos.length * 2];
        for (int i = 0; i < elementos.length; i++) {
            nuevoArray[i] = elementos[i];
        }
        elementos = nuevoArray;
    }

    public void mostrarEstructura() {
        System.out.println("Índice | Dato ");
        for (int i = 0; i < tamaño; i++) {
            System.out.println("  " + i + "    |  " + elementos[i]);
        }
    }

    public static void main(String[] args) {
        Lista miLista = new Lista();

        miLista.agregar(5);
        miLista.agregar(10);
        miLista.agregar(15);

        miLista.mostrarEstructura();

        System.out.println("Tamaño: " + miLista.tamaño());
        System.out.println("Elemento en 1: " + miLista.obtener(1));

        miLista.eliminar(1);
        System.out.println("Después de eliminar, tamaño: " + miLista.tamaño());
        System.out.println("Nuevo elemento en 1: " + miLista.obtener(1));

        miLista.mostrarEstructura();

    }
}