public class Cocinero {
    private Pedido pedidoActual;
    private int tiempoRestante;

    public Cocinero() {
        this.pedidoActual = null;
        this.tiempoRestante = 0;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void asignarPedido(Pedido nuevoPedido) {
        if (estaLibre()) {
            this.pedidoActual = nuevoPedido;
            this.tiempoRestante = nuevoPedido.getTiempoDePreparacion();
        }
    }

    public void trabajar() {
        if (!estaLibre()) {
            tiempoRestante--;
            if (tiempoRestante <= 0) {
                pedidoActual = null; 
                tiempoRestante = 0;
            }
        }
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
}