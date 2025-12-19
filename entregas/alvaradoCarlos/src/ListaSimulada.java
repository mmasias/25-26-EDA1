public class ListaSimulada {
    private int[] datos;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 4;
    public final int TAMAÑO_INICIAL = 0;
    public final int MULTIPLICADOR_ARRAY = 2;

    public ListaSimulada() {
        datos = new int[CAPACIDAD_INICIAL];
        tamaño = TAMAÑO_INICIAL;
    }

    private void expandir() {
        int nuevaCapacidad = datos.length * MULTIPLICADOR_ARRAY;
        int[] nuevoArray = new int[nuevaCapacidad];

        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = datos[i];
        }

        datos = nuevoArray;
    }

    public void agregar(int elemento) {
        if (tamaño == datos.length) {
            expandir();
        }
        datos[tamaño] = elemento;
        tamaño++;
    }

    public void agregar(int indice, int elemento) {
        if (indice < 0 || indice > tamaño) {
            System.out.println("Índice fuera de rango");
            return;
        }

        if (tamaño == datos.length) {
            expandir();
        }

        for (int i = tamaño; i > indice; i--) {
            datos[i] = datos[i - 1];
        }

        datos[indice] = elemento;
        tamaño++;
    }

    public int obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Índice fuera de rango");
            return -1;
        }
        return datos[indice];
    }

    public void modificar(int indice, int elemento) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Índice fuera de rango");
            return;
        }
        datos[indice] = elemento;
    }

    public int remover(int indice) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Índice fuera de rango");
            return -1;
        }

        int eliminado = datos[indice];

        for (int i = indice; i < tamaño - 1; i++) {
            datos[i] = datos[i + 1];
        }

        tamaño--;
        return eliminado;
    }

    public int getTamaño() {
        return tamaño;
    }

    public int getCapacidad() {
        return datos.length;
    }
    
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        for (int i = 0; i < tamaño; i++) {
            texto.append(datos[i]);
            if (i < tamaño - 1) {
                texto.append(", ");
            }
        }
        texto.append("]");
        return texto.toString();
    }
}
