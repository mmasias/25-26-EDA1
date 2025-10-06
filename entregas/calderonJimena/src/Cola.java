public class Cola {
    private Nino[] arregloNinos;
    private int cantidad;

    private static final int MAX_NINOS = 100;

    public Cola() {
        arregloNinos = new Nino[MAX_NINOS];
        cantidad = 0;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public boolean estaLlena() {
        return cantidad == MAX_NINOS;
    }

    public void encolar(Nino nino) {
        if (!estaLlena()) {
            arregloNinos[cantidad] = nino;
            cantidad++;
        }
    }

    public Nino desencolar() {
        if (estaVacia()) return null;
        Nino primerNino = arregloNinos[0];
        for (int i = 1; i < cantidad; i++) {
            arregloNinos[i - 1] = arregloNinos[i];
        }
        arregloNinos[cantidad - 1] = null;
        cantidad--;
        return primerNino;
    }

    public int tamano() {
        return cantidad;
    }

    public Nino[] obtenerArreglo() {
        Nino[] copia = new Nino[cantidad];
        for (int i = 0; i < cantidad; i++) copia[i] = arregloNinos[i];
        return copia;
    }

    public void vaciar() {
        for (int i = 0; i < cantidad; i++) arregloNinos[i] = null;
        cantidad = 0;
    }
}

