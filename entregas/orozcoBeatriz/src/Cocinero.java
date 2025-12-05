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
        Pedido resultado = null;

        if (pedidoActual == null) {
            return resultado;
        }

        pedidoActual.reducirTiempoRestante();

        if (pedidoActual.getTiempoRestantePreparacion() == 0) {
            resultado = pedidoActual;
            pedidoActual = null;
        }

        return resultado;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}
