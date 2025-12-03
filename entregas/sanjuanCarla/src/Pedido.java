public class Pedido {
    private final String tipoPedido;
    private final int tiempoPreparacionTotal;
    private int tiempoPreparacionRestante;
    private final int minutoLlegada;

    public Pedido(String tipoPedido, int tiempoPreparacionTotal, int minutoLlegada) {
        this.tipoPedido = tipoPedido;
        this.tiempoPreparacionTotal = tiempoPreparacionTotal;
        this.tiempoPreparacionRestante = tiempoPreparacionTotal;
        this.minutoLlegada = minutoLlegada;
    }

    public String obtenerTipoPedido() { return tipoPedido; }
    public int obtenerTiempoPreparacionTotal() { return tiempoPreparacionTotal; }
    public int obtenerTiempoRestante() { return tiempoPreparacionRestante; }
    public void avanzarUnMinuto() { tiempoPreparacionRestante--; }
    public int obtenerMinutoLlegada() { return minutoLlegada; }
    public boolean estaTerminado() { return tiempoPreparacionRestante <= 0; }
}
