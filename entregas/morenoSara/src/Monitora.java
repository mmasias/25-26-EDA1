public abstract class Monitora {
    protected String nombreMonitora;
    protected ColaNinos colaNinos;

    public Monitora(String nombreMonitora) {
        this.nombreMonitora = nombreMonitora;
        this.colaNinos = new ColaNinos(10);
    }

    public String getNombreMonitora() {
        return nombreMonitora;
    }

    public ColaNinos getColaNinos() {
        return colaNinos;
    }

    public void agregarNino(Nino nuevoNino) {
        colaNinos.encolar(nuevoNino);
    }

    public void transferirNinos(Monitora monitoraDestino) {
        for (Nino infante : colaNinos.obtenerNinos()) {
            monitoraDestino.agregarNino(infante);
        }
        colaNinos.vaciar();
    }

    public abstract void presentarse();
}

