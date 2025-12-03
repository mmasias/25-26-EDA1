public class Pedido {
    private String nombre;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(String nombre, int tiempoPreparacion) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoEspera = 0;
    }

    public void incrementarTiempoEspera() {
        this.tiempoEspera++;
    }

    public String getNombre() { return nombre; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoEspera() { return tiempoEspera; }
}