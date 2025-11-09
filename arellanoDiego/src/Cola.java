public class Cola {
    private static final int CAPACIDAD_MAXIMA = 15;
    private final Niño[] datos = new Niño[CAPACIDAD_MAXIMA];
    private int inicio = 0;
    private int size = 0;

    public boolean estaVacia() { return size == 0; }
    public int tamaño() { return size; }

    public boolean add(Niño n) {
        if (size == CAPACIDAD_MAXIMA) return false;
        int fin = (inicio + size) % CAPACIDAD_MAXIMA;
        datos[fin] = n;
        size++;
        return true;
    }

    public Niño remove() {
        if (size == 0) return null;
        Niño n = datos[inicio];
        datos[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        size--;
        return n;
    }

    public Niño get(int i) {
        if (i < 0 || i >= size) return null;
        int idx = (inicio + i) % CAPACIDAD_MAXIMA;
        return datos[idx];
    }

    public void forEach(java.util.function.Consumer<Niño> f) {
        for (int i = 0; i < size; i++) f.accept(get(i));
    }

    public String listarNombres() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(" / ");
            Niño n = get(i);
            sb.append(n == null ? "(null)" : n.getNombre());
        }
        return sb.toString();
    }
}
