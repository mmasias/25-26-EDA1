public class Cocinero {

    private Pedido pedidoActual;
    private int platosAtendidos;

    public Cocinero() {
        this.pedidoActual = null;
        this.platosAtendidos = 0;
    }

    public boolean estaLibre() {
        return this.pedidoActual == null;
    }

    public void asignarPedido(Pedido p) {
        this.pedidoActual = p;
    }

    public Pedido getPedidoActual() {
        return this.pedidoActual;
    }

    public int getPlatosAtendidos() {
        return this.platosAtendidos;
    }

    public void trabajar() {
        if (this.pedidoActual != null) {
            this.pedidoActual.restarMinuto();
            if (this.pedidoActual.estaTerminado()) {
                this.platosAtendidos++;
                this.pedidoActual = null;
            }
        }
    }
}
