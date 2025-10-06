public class Cola {
    private static final int CAPACIDAD_MAXIMA = 20;
    private Niño[] niños;
    private int inicio, fin, cantidad;

    public Cola() { niños = new Niño[CAPACIDAD_MAXIMA]; inicio = fin = cantidad = 0; }

    public void addNiño(Niño n) {
        if (cantidad >= CAPACIDAD_MAXIMA) return;
        niños[fin] = n;
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        cantidad++;
    }

    public Niño removeNiño() {
        if (cantidad == 0) return null;
        Niño n = niños[inicio];
        niños[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return n;
    }

    public int size() { return cantidad; }
    public boolean hayNiños() { return cantidad > 0; }
    public Niño getNiño(int pos) { return niños[(inicio + pos) % CAPACIDAD_MAXIMA]; }

    public Niño[] getTodos() {
        Niño[] copia = new Niño[cantidad];
        for (int i = 0; i < cantidad; i++) copia[i] = getNiño(i);
        return copia;
    }
}