public class Aisha {
    private Nino[] fila;
    private int cantidad;
    private boolean jugando;

    public Aisha() {
        fila = new Nino[10]; 
        cantidad = 0;
        jugando = false;
    }

    public void recibirNinos(Nino[] nuevos, int numNuevos) {
        for (int i = 0; i < numNuevos; i++) {
            agregar(nuevos[i]);
        }
    }

    private void agregar(Nino nino) {
        if (cantidad == fila.length) {
            crecer();
        }
        fila[cantidad++] = nino;
    }

    private void crecer() {
        Nino[] nueva = new Nino[fila.length * 2];
        System.arraycopy(fila, 0, nueva, 0, fila.length);
        fila = nueva;
    }

    public boolean puedeJugar() {
        return cantidad > 5;
    }

    public Nino[] getFila() {
        jugando = true;
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(fila, 0, copia, 0, cantidad);
        return copia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void vaciarFila() {
        cantidad = 0;
        jugando = false;
    }

    public boolean estaJugando() {
        return jugando;
    }
}
