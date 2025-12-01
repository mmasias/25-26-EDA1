public class Pedido {
    private final String tipo;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private final int tiempoLlegada;

    public Pedido(String tipo, int tiempoPreparacion, int tiempoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getTipo() { return tipo; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoRestante() { return tiempoRestante; }
    public void decrementarTiempo() { tiempoRestante--; }
    public int getTiempoLlegada() { return tiempoLlegada; }
    public boolean terminado() { return tiempoRestante <= 0; }
}
