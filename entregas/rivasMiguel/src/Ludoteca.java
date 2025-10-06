public class Ludoteca {

    private Pizarra pizarraSala;
    private Monitora receptora;
    private Monitora organizadora;

    public static final int MIN_NINOS_PARA_JUEGO = 6;

    private boolean juegoEnCurso = false;

    public Ludoteca() {
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

    public Monitora getReceptora() {
        return receptora;
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
