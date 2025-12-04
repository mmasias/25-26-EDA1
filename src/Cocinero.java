public class Cocinero {
    private Pedido pedidoActual;
    private boolean ocupado;

    public Cocinero() {
        this.pedidoActual = null;
        this.ocupado = false;
    }

    public void tomarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
        this.ocupado = true;
    }

    public void procesarMinuto() {
        if (this.ocupado && this.pedidoActual != null) {
            this.pedidoActual.tiempoRestante--;
            if (this.pedidoActual.tiempoRestante <= 0) {
                this.pedidoActual = null;
                this.ocupado = false;
            }
        }
    }

    public boolean estaOcupado() {
        return this.ocupado;
    }

    public Pedido getPedidoActual() {
        return this.pedidoActual;
    }
}
