public class EstadisticasJornada {
        private int pedidosAtendidos;
        private int pedidosPendientes;
        private int tiempoTotalEspera;
        private int comparacionesTotales;

        public EstadisticasJornada() {
                pedidosAtendidos = 0;
                pedidosPendientes = 0;
                tiempoTotalEspera = 0;
                comparacionesTotales = 0;
        }

        public void registrarPedidoAtendido(Pedido pedido, int minutoActual) {
                pedidosAtendidos++;
                tiempoTotalEspera += pedido.calcularTiempoEspera(minutoActual);
        }

        public void registrarPedidoPendiente() {
                pedidosPendientes++;
        }

        public void actualizarComparaciones(int comparaciones) {
                comparacionesTotales = comparaciones;
        }

        public int obtenerPedidosAtendidos() {
                return pedidosAtendidos;
        }

        public int obtenerPedidosPendientes() {
                return pedidosPendientes;
        }

        public int obtenerTiempoTotalEspera() {
                return tiempoTotalEspera;
        }

        public double obtenerTiempoMedioEspera() {
                int totalPedidos = pedidosAtendidos + pedidosPendientes;
                if (totalPedidos == 0) {
                        return 0.0;
                }
                return (double) tiempoTotalEspera / pedidosAtendidos;
        }

        public int obtenerComparacionesTotales() {
                return comparacionesTotales;
        }
}
