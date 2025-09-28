public class Ludoteca {

    private Nino[] fila;
    private int numeroDeNinos;
    private Pizarra pizarraSala;
    private Monitora receptora;
    private Monitora organizadora;
    public static final int MIN_NINOS_PARA_JUEGO = 6;

    public Ludoteca() {
        this.fila = new Nino[20];
        this.numeroDeNinos = 0;
        this.pizarraSala = new Pizarra("PizarraSala");
    }

    public void setReceptora(Monitora receptora) {
        this.receptora = receptora;
        if (this.receptora != null && organizadora != null) {
            this.receptora.setOrganizadora(organizadora);
        }
    }

    public void setOrganizadora(Monitora organizadora) {
        this.organizadora = organizadora;
        if (organizadora != null) {
            organizadora.abrirLudoteca(this);
        }
        if (receptora != null && organizadora != null) {
            receptora.setOrganizadora(organizadora);
        }
    }

    public Monitora getOrganizadora() {
        return organizadora;
    }

    public Pizarra getPizarraSala() {
        return pizarraSala;
    }

    public void addNino(Nino nino) {
        Pizarra pizarrin = new Pizarra("Pizarrin de " + nino.getNombre());
        nino.recibirPizarrin(pizarrin);

        if (numeroDeNinos >= fila.length) {
            Nino[] nuevoArray = new Nino[fila.length * 2];
            System.arraycopy(fila, 0, nuevoArray, 0, fila.length);
            fila = nuevoArray;
        }

        fila[numeroDeNinos] = nino;
        numeroDeNinos++;
        System.out.println("La fila ahora tiene " + numeroDeNinos + " niños.");

        intentarIniciarJuego();
    }

    private void intentarIniciarJuego() {
        if (numeroDeNinos >= MIN_NINOS_PARA_JUEGO) {
            System.out.println("Se ha alcanzado el mínimo de niños (" + MIN_NINOS_PARA_JUEGO + "): se inicia el juego.");
            new Juego(this).iniciar();
        }
    }

    public Nino[] getFilaArray() {
        Nino[] copia = new Nino[numeroDeNinos];
        System.arraycopy(fila, 0, copia, 0, numeroDeNinos);
        return copia;
    }

    public int numeroDeNinos() {
        return numeroDeNinos;
    }

    public void vaciarFila() {
        for (int i = 0; i < numeroDeNinos; i++) fila[i] = null;
        numeroDeNinos = 0;
        System.out.println("La fila ha sido vaciada.");
    }
}
