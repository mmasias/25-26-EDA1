public class Pedido {

    private Plato plato;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(Plato plato) {
        this.plato = plato;
        this.tiempoPreparacion = generarTiempo(plato);
        this.tiempoEspera = 0;
    }

    private int generarTiempo(Plato plato) {
        int mininmo = plato.obtenerMinTiempo();
        int maximo = plato.obtenerMaxTiempo();
        return mininmo + (int) (Math.random() * (maximo - mininmo + 1));
    }

    public void incrementarTiempoEspera() {
        tiempoEspera++;
    }

    public String getNombre() {
        return plato.obtenerNombre();
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }
}
