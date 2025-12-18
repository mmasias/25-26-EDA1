public class Lista {
    private int[] datos;
    private int[] siguiente;
    private int cabeza;
    private int libre;
    private int tamaño;

    public Lista(int capacidad) {
        datos = new int[capacidad];
        siguiente = new int[capacidad];

        for (int i = 0; i < capacidad - 1; i++) {
            siguiente[i] = i + 1;
        }
        siguiente[capacidad - 1] = -1;

        cabeza = -1;
        libre = 0;
        tamaño = 0;
    }

    public void agregar(int valor) {
        assert libre != -1 : "Mal: lista llena";

        int nuevo = libre;
        libre = siguiente[libre];

        datos[nuevo] = valor;
        siguiente[nuevo] = -1;

        if (cabeza == -1) {
            cabeza = nuevo;
        } else {
            int actual = cabeza;
            while (siguiente[actual] != -1) {
                actual = siguiente[actual];
            }
            siguiente[actual] = nuevo;
        }

        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño : "Mal: el índice esta fuera de rango";

        int actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = siguiente[actual];
        }
        return datos[actual];
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño : "Mal: el índice esta fuera de rango";

        int actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = siguiente[actual];
        }
        datos[actual] = nuevoValor;
    }

    public int tamaño() {
        return tamaño;
    }
}