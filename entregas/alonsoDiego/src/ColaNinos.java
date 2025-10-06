class ColaNinos {
    private Nino[] cola;
    private int tamaño;
    private int capacidad;

    public ColaNinos() {
        capacidad = 100;
        cola = new Nino[capacidad];
        tamaño = 0;
    }

    public void encolar(Nino nino) {
        if (tamaño < capacidad) {
            cola[tamaño] = nino;
            tamaño++;
        }
    }

    public Nino desencolar() {
        if (estaVacia()) {
            return null;
        }
        Nino primero = cola[0];
        for (int i = 0; i < tamaño - 1; i++) {
            cola[i] = cola[i + 1];
        }
        tamaño--;
        cola[tamaño] = null;
        return primero;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Nino getNino(int i) {
        if (i >= 0 && i < tamaño) {
            return cola[i];
        }
        return null;
    }

    public void limpiar() {
        for (int i = 0; i < tamaño; i++) {
            cola[i] = null;
        }
        tamaño = 0;
    }

    public void transferirTodos(ColaNinos destino) {
        while (!estaVacia()) {
            destino.encolar(desencolar());
        }
    }
}
