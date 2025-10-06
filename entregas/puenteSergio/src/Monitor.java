

class Monitor {
    private static final int MAX_NINOS = 50;

    private final String nombre;
    private final Nino[] niños;
    private int uso;
    private boolean jugando;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new Nino[MAX_NINOS];
        this.uso = 0;
        this.jugando = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibeNiño(Nino n) {
        if (n == null) return;
        if (uso < niños.length) {
            niños[uso++] = n;
            n.recibirPizarrin(new Pizarra());
        } else {
            System.out.println("La cola de " + nombre + " está llena. No se puede añadir a " + n.getNombre());
        }
    }

    public void entregaNiños(Monitor destino) {
        if (destino == null) return;
        for (int i = 0; i < uso; i++) {
            destino.recibeNiño(niños[i]);
        }
        uso = 0;
    }

    public boolean puedeJugar() {
        return uso >= 5;
    }

    public void jugar() {
        if (!puedeJugar() || jugando) return;
        jugando = true;
        System.out.println(nombre + " empieza a jugar con " + uso + " niños.");
        for (int i = 0; i < uso; i++) {
            Nino n = niños[i];
            n.recibirMensaje("Vamos a jugar");
        }
        System.out.println(nombre + " ha terminado el juego.");
        jugando = false;
    }

    public void mostrarListaNiños() {
        System.out.println(nombre + " (niños: " + uso + "):");
        for (int i = 0; i < uso; i++) {
            System.out.println(" - " + niños[i].getNombre());
        }
    }

    public int numeroNiños() {
        return uso;
    }

    public Nino[] getNiños() {
        Nino[] copia = new Nino[uso];
        for (int i = 0; i < uso; i++) copia[i] = niños[i];
        return copia;
    }

    public void vaciar() {
        uso = 0;
    }

    public int getUsoMemoria() {
        return uso;
    }

    public boolean estaJugando() {
        return jugando;
    }
}
