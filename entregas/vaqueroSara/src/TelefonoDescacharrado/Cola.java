public class Cola {
    private static final int CAPACIDAD_MAXIMA = 100;
    private Nino[] ninos;
    private int inicio;
    private int fin;
    private int cantidad;

    public Cola() {
        ninos = new Nino[CAPACIDAD_MAXIMA];
        inicio = fin = cantidad = 0;
    }

    public void add(Nino nino) {
        if (cantidad < CAPACIDAD_MAXIMA) {
            ninos[fin] = nino;
            fin = (fin + 1) % CAPACIDAD_MAXIMA;
            cantidad++;
        }
    }

    public Nino remove() {
        if (cantidad == 0) return null;
        Nino n = ninos[inicio];
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return n;
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public int size() {
        return cantidad;
    }

    public Nino get(int pos) {
        if (pos >= cantidad) return null;
        return ninos[(inicio + pos) % CAPACIDAD_MAXIMA];
    }
}
