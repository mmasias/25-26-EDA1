public class ColaCircular {
    private final int[] datos;
    private int inicio;
    private int fin;
    private int tamaño;

    public ColaCircular(int capacidad) {
        datos = new int[capacidad];
        inicio = 0; fin = 0; tamaño = 0;
    }

    public boolean encolar(int valor) {
        if (tamaño == datos.length) return false;
        datos[fin] = valor;
        fin = (fin + 1) % datos.length;
        tamaño++;
        return true;
    }

    public int desencolar() {
        if (tamaño == 0) return -1;
        int v = datos[inicio];
        inicio = (inicio + 1) % datos.length;
        tamaño--;
        return v;
    }

    public int tamaño() { return tamaño; }
    public boolean vacia() { return tamaño == 0; }
}
