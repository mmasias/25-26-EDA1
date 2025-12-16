package RefactorizacionExamenParcial;

public class Lista {
    private int[] miArray;
    private int tamaño;

    public Lista(int capacidad) {
        this.miArray = new int[capacidad];
        this.tamaño = 0;
    }

    public void agregar(int valor) {
        assert tamaño < miArray.length : "Error: La lista está llena";

        miArray[tamaño] = valor;
        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño : "Error: índice fuera de rango";
        return miArray[indice];
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño : "Error: Índice fuera de rango";

        miArray[indice] = nuevoValor;
    }

    public int longitud() {
        return tamaño;

    }
}