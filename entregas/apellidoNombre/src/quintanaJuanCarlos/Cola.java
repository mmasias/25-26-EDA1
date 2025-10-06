public class Cola {
    private final Nino[] datos;
    private int inicio;
    private int fin;
    private int cantidad;

    public Cola(int capacidad) {
        this.datos = new Nino[capacidad];
        this.inicio = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    public boolean encolar(Nino n) {
        if (cantidad == datos.length) return false;
        datos[fin] = n;
        fin = (fin + 1) % datos.length;
        cantidad++;
        return true;
    }

    public Nino desencolar() {
        if (cantidad == 0) return null;
        Nino n = datos[inicio];
        datos[inicio] = null;
        inicio = (inicio + 1) % datos.length;
        cantidad--;
        return n;
    }

    public Nino get(int pos) {
        if (pos < 0 || pos >= cantidad) return null;
        int idx = (inicio + pos) % datos.length;
        return datos[idx];
    }

    public int tamano() { return cantidad; }
    public boolean vacia() { return cantidad == 0; }
    public int capacidad() { return datos.length; }

    public void vaciar() {
        while (cantidad > 0) {
            datos[inicio] = null;
            inicio = (inicio + 1) % datos.length;
            cantidad--;
        }
        inicio = fin = 0;
    }
}
