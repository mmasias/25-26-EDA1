public class Lydia {
    private Nino[] salaEspera;
    private int cantidad;

    public Lydia() {
        salaEspera = new Nino[10];
        cantidad = 0;
    }

    public void recibirNino(Nino nino) {
        if (cantidad == salaEspera.length) {
            crecer();
        }
        salaEspera[cantidad++] = nino;
        System.out.println("Llega " + nino.getNombre() + " (" + nino.getEdad() + " años)");
        System.out.println(nino.getNombre() + " pasa a la cola de Lydia\n");
    }

    private void crecer() {
        Nino[] nueva = new Nino[salaEspera.length * 2];
        System.arraycopy(salaEspera, 0, nueva, 0, salaEspera.length);
        salaEspera = nueva;
    }

    public Nino[] pasarNinos() {
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(salaEspera, 0, copia, 0, cantidad);
        cantidad = 0;
        return copia;
    }

    public boolean tieneNinos() {
        return cantidad > 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Nino[] getFila() {
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(salaEspera, 0, copia, 0, cantidad);
        return copia;
    }

    public void recibirDesde(Lydia otra) {
        for (Nino n : otra.getFila()) {
            recibirNino(n);
        }
    }
}
