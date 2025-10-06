public class Dalsy {
    private Nino[] fila;
    private int cantidad;

    public Dalsy() {
        fila = new Nino[10];
        cantidad = 0;
    }

    public void recibirNino(Nino n) {
        if (cantidad == fila.length) crecer();
        fila[cantidad++] = n;
    }

    private void crecer() {
        Nino[] nueva = new Nino[fila.length * 2];
        System.arraycopy(fila, 0, nueva, 0, fila.length);
        fila = nueva;
    }

    public Nino[] pasarNinos() {
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(fila, 0, copia, 0, cantidad);
        cantidad = 0;
        return copia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Nino[] getFila() {
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(fila, 0, copia, 0, cantidad);
        return copia;
    }
}
