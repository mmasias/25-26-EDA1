public class Monitor {
    private String nombreMonitor;
    private Cola colaNinos;

    public Monitor(String nombreMonitor) {
        this.nombreMonitor = nombreMonitor;
        this.colaNinos = new Cola();
    }

    public void agregarNinoACola(Nino nino) {
        colaNinos.encolar(nino);
    }

    public Nino[] transferirTodosLosNinos() {
        int total = colaNinos.tamano();
        Nino[] transferidos = new Nino[total];
        for (int i = 0; i < total; i++) {
            transferidos[i] = colaNinos.desencolar();
        }
        return transferidos;
    }

    public Nino[] obtenerListaNinos() {
        return colaNinos.obtenerArreglo();
    }

    public int obtenerCantidadNinos() {
        return colaNinos.tamano();
    }

    public void reemplazarNinos(Nino[] nuevosNinos) {
        colaNinos.vaciar();
        for (int i = 0; i < nuevosNinos.length; i++) {
            colaNinos.encolar(nuevosNinos[i]);
        }
    }

    public String getNombreMonitor() {
        return nombreMonitor;
    }
}
