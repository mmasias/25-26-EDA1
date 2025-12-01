public class Cocinero {

    private Pedido pedidoActual;

    public void asignarPedido(Pedido pedido, int minutoActual) {
        this.pedidoActual = pedido;
        this.pedidoActual.setMinutoInicioPreparacion(minutoActual);
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public Pedido cocinarDuranteUnMinuto() {
        if (pedidoActual == null) {
            return null;
        }

        pedidoActual.reducirTiempoRestante();

        if (pedidoActual.getTiempoRestantePreparacion() == 0) {
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
