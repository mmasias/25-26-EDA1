public class Simulador {

        private static final int INICIO = 0;
        private static final int EJECUTANDO = 1;
        private static final int FINALIZADO = 2;

        private Arbol colaPedidos;
        private Cocinero cocinero;
        private EstadisticasJornada estadisticas;
        private Reloj reloj;
        private int estadoActual;

        public Simulador() {
                this.colaPedidos = new Arbol();
                this.cocinero = new Cocinero();
                this.estadisticas = new EstadisticasJornada();
                this.reloj = new Reloj();
                this.estadoActual = INICIO;
        }

        public static void main(String[] args) {
                new Simulador().simular();
        }

        public void simular() {
                boolean simulacionActiva = true;

                while (simulacionActiva) {
                        switch (estadoActual) {
                                case INICIO -> estadoActual = EJECUTANDO;
                                case EJECUTANDO -> {
                                        ejecutarCiclo();
                                        if (reloj.esFinDeJornada())
                                                estadoActual = FINALIZADO;
                                }
                                case FINALIZADO -> {
                                        finalizarJornada();
                                        simulacionActiva = false;
                                }
                        }
                }
        }

        private void ejecutarCiclo() {
                reloj.avanzarMinuto();
                int minuto = reloj.obtenerMinutoActual();

                VisualizadorDeSimulacion.mostrarEncabezadoMinuto(minuto);

                gestionarLlegadaPedidos(minuto);
                gestionarCocina(minuto);

                VisualizadorDeSimulacion.mostrarEstadoCocina(cocinero, colaPedidos, minuto);
        }

        private void gestionarLlegadaPedidos(int minuto) {
                Pedido nuevoPedido;
                final double PROBABILIDAD_LLEGADA = 0.4;

                if (Math.random() < PROBABILIDAD_LLEGADA) {
                        nuevoPedido = generarPedido(minuto);
                        colaPedidos.insertar(nuevoPedido);
                        VisualizadorDeSimulacion.mostrarLlegadaPedido(nuevoPedido, minuto);
                }
        }

        private void gestionarCocina(int minuto) {
                if (!cocinero.estaOcupado() && !colaPedidos.estaVacia())
                        cocinero.tomarNuevoPedido(colaPedidos);

                if (!cocinero.estaOcupado())
                        return;

                cocinero.procesarMinuto();

                if (!cocinero.obtenerPedidoActual().estaCompletado())
                        return;

                Pedido pedidoCompletado = cocinero.finalizarPedidoActual();
                estadisticas.registrarPedidoAtendido(pedidoCompletado, minuto);

                if (!colaPedidos.estaVacia())
                        cocinero.tomarNuevoPedido(colaPedidos);
        }

        private void finalizarJornada() {
                contarPedidosPendientes();
                estadisticas.actualizarComparaciones(colaPedidos.obtenerComparaciones());
                VisualizadorDeSimulacion.mostrarResumen(estadisticas);
        }

        private void contarPedidosPendientes() {
                while (!colaPedidos.estaVacia()) {
                        colaPedidos.extraerMinimo();
                        estadisticas.registrarPedidoPendiente();
                }
        }

	private Pedido generarPedido(int minuto) {
		Pedido nuevoPedido;
		int tipoAleatorio = TipoDePlato.generarTipoAleatorio();
		nuevoPedido = new Pedido(tipoAleatorio, minuto);
		return nuevoPedido;
	}
}