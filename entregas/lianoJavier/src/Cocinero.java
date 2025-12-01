public class Cocinero {
        private Pedido pedidoActual;

        public Cocinero() {
                pedidoActual = null;
        }

        public void procesarMinuto() {
                if (pedidoActual != null) {
                        pedidoActual.procesarMinuto();
                }
        }

        public Pedido tomarNuevoPedido(Arbol colaPedidos) {
                pedidoActual = colaPedidos.extraerMinimo();
                return pedidoActual;
        }

        public boolean estaOcupado() {
                return pedidoActual != null && !pedidoActual.estaCompletado();
        }

        public Pedido obtenerPedidoActual() {
                return pedidoActual;
        }

        public Pedido finalizarPedidoActual() {
                Pedido pedidoCompletado = pedidoActual;
                pedidoActual = null;
                return pedidoCompletado;
        }

}
