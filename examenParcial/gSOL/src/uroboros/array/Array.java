package uroboros.array;

import listaConArray.l001.Lista;

public class Array {
    private Lista lista;
    private int capacidad;

    public Array(int capacidad) {
        assert capacidad > 0 : "Precondición violada: capacidad debe ser positiva";

        this.capacidad = capacidad;
        this.lista = new Lista();

        for (int i = 0; i < capacidad; i++) {
            lista.agregar(0);
        }
    }

    public int get(int indice) {
        assert indice >= 0 && indice < capacidad : "Precondición violada: índice fuera de rango";
        return lista.obtener(indice);
    }

    public void set(int indice, int valor) {
        assert indice >= 0 && indice < capacidad : "Precondición violada: índice fuera de rango";
        lista.modificar(indice, valor);
    }

    public int longitud() {
        return capacidad;
    }
}
