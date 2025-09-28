public class Ludoteca {
    private Nino[] fila;
    private int numero;

    private Pizarra pizarraSala;
    private Monitora receptora;
    private Monitora organizadora;

    public static final int MIN_NINOS_PARA_JUEGO = 6;

    private boolean juegoEnCurso = false;

    public Ludoteca() {
        this.fila = new Nino[20];
        this.numero = 0;
        this.pizarraSala = new Pizarra("PizarraSala");
    }

    public void setReceptora(Monitora receptora) {
        this.receptora = receptora;
        if (this.receptora != null) {
            this.receptora.abrirLudoteca(this);
        }
        if (this.receptora != null && this.organizadora != null) {
            this.receptora.setOrganizadora(this.organizadora);
        }
    }

    public void setOrganizadora(Monitora organizadora) {
        this.organizadora = organizadora;
        if (this.organizadora != null) {
            this.organizadora.abrirLudoteca(this);
        }
        if (this.receptora != null && this.organizadora != null) {
            this.receptora.setOrganizadora(this.organizadora);
        }
    }

    public Monitora getOrganizadora() {
        return organizadora;
    }

    public Pizarra getPizarraSala() {
        return pizarraSala;
    }

    public void addNino(Nino n) {
        Pizarra pizarrin = new Pizarra("Pizarrin de " + n.getNombre());
        n.recibirPizarrin(pizarrin);

        if (numero >= fila.length) {
            Nino[] nuevo = new Nino[fila.length * 2];
            for (int i = 0; i < fila.length; i++) {
                nuevo[i] = fila[i];
            }
            fila = nuevo;
        }

        fila[numero] = n;
        numero++;
        System.out.println("La fila ahora tiene " + numero + " niños.");
    }

    public Nino[] getFilaArray() {
        Nino[] copia = new Nino[numero];
        for (int i = 0; i < numero; i++) copia[i] = fila[i];
        return copia;
    }

    public int numeroDeNinos() {
        return numero;
    }

    public void vaciarFila() {
        for (int i = 0; i < numero; i++) fila[i] = null;
        numero = 0;
        System.out.println("La fila ha sido vaciada.");
    }

    public boolean isJuegoEnCurso() {
        return juegoEnCurso;
    }

    public void setJuegoEnCurso(boolean juegoEnCurso) {
        this.juegoEnCurso = juegoEnCurso;
    }

    public void transferirNinosDesdeReceptora() {
        if (receptora != null) {
            receptora.pasarNinosAOrganizadora();
        }
    }
}
