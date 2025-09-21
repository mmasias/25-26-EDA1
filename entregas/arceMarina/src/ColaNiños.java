public class ColaNiños {
    private Niño[] cola;
    private int inicio;
    private int fin;
    private int cantidad;

    public ColaNiños() {
        cola = new Niño[100];
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public void añadirNiño(Niño nino) {
        if (cantidad < cola.length) {
            cola[fin] = nino;
            fin = (fin + 1) % cola.length;
            cantidad++;
        }
    }

    public Niño[] obtenerNiñosCola() {
        Niño[] resultado = new Niño[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = cola[(inicio + i) % cola.length];
        }
        return resultado;
    }

    public int tamañoCola() {
        return cantidad;
    }

    public void limpiarCola() {
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }
}
