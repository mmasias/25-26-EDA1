public class Cola {
    private static final int CAPACIDAD_MAXIMA = 20;
    private final Niño[] niños;
    private int inicio;
    private int fin;
    private int cantidad;

    public Cola() {
        niños = new Niño[CAPACIDAD_MAXIMA];
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public void addNiño(Niño niño) {
        if (cantidad < CAPACIDAD_MAXIMA) {
            niños[fin] = niño;
            fin = (fin + 1) % CAPACIDAD_MAXIMA;
            cantidad++;
        }
    }

    public Niño removeNiño() {
        if (cantidad == 0)
            return null;
        Niño n = niños[inicio];
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return n;
    }

    public Niño removeNiñoPorPosicion(int pos) {
        if (pos < 0 || pos >= cantidad)
            return null;
        int idx = (inicio + pos) % CAPACIDAD_MAXIMA;
        Niño eliminado = niños[idx];
        for (int i = pos; i < cantidad - 1; i++) {
            niños[(inicio + i) % CAPACIDAD_MAXIMA] = niños[(inicio + i + 1) % CAPACIDAD_MAXIMA];
        }
        fin = (fin - 1 + CAPACIDAD_MAXIMA) % CAPACIDAD_MAXIMA;
        cantidad--;
        return eliminado;
    }

    public Niño getNiño(int pos) {
        if (pos < 0 || pos >= cantidad)
            return null;
        return niños[(inicio + pos) % CAPACIDAD_MAXIMA];
    }

    public boolean hayNiños() {
        return cantidad > 0;
    }

    public int size() {
        return cantidad;
    }
}
