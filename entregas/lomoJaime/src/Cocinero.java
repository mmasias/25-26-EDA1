public class Cocinero {
    private Pedido pedidoActual;

    public boolean estaLibre() {
        return pedidoActual == null || pedidoActual.getTiempoRestante() <= 0;
    }

    public void asignarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public void avanzarMinuto() {
        if (pedidoActual != null && pedidoActual.getTiempoRestante() > 0) {
            pedidoActual.setTiempoRestante(pedidoActual.getTiempoRestante() - 1);
        }
    }

    public Pedido finalizarSiEstaListo() {
        if (pedidoActual != null && pedidoActual.getTiempoRestante() <= 0) {
            Pedido terminado = pedidoActual;
            pedidoActual = null;
            return terminado;
        }
        return null;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}