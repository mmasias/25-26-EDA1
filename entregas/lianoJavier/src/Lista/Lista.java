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

    public String obtenerTextoPosicionesValidas() {
        String resultado = "Posiciones válidas para insertar: ";
        for (int indice = 0; indice <= cantidadElementos; indice++) {
            resultado = resultado + indice;
            if (indice < cantidadElementos) {
                resultado = resultado + ", ";
            }
        }
        return resultado;
    }

    public void insertar(int valor, int posicion) {
        if (cantidadElementos == elementos.longitud()) {
            expandir();
        }

        assert posicion >= 0 && posicion <= cantidadElementos : "Posición inválida";

        for (int i = cantidadElementos - 1; i >= posicion; i--) {
            elementos.set((i + 1), elementos.get(i));
        }

        elementos.set(posicion, valor);
        cantidadElementos++;
    }

    public void eliminar(int posicion) {
        assert posicion >= 0 && posicion < cantidadElementos : "Posición inválida";

        for (int i = posicion; i < cantidadElementos - 1; i++) {
            elementos.set(i, elementos.get(i + 1));
        }

        elementos.set(cantidadElementos - 1, 0);
        cantidadElementos--;
    }

    public int obtener(int posicion) {
        assert posicion >= 0 && posicion < cantidadElementos : "Posición inválida";
        return elementos.get(posicion);
    }

    private void expandir() {
        Array nuevoArray = new Array(elementos.longitud() + 1);
        for (int i = 0; i < elementos.longitud(); i++) {
            nuevoArray.set(i, elementos.get(i));
        }
        elementos = nuevoArray;
    }

    public String obtenerTexto() {
        String resultado = "[";
        for (int indice = 0; indice < cantidadElementos; indice++) {
            resultado = resultado + elementos.get(indice);
            if (indice < cantidadElementos - 1) {
                resultado = resultado + ", ";
            }
        }
        resultado = resultado + "]";
        return resultado;
    }
}
