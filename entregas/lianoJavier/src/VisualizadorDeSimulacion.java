public class VisualizadorDeSimulacion {

        public static void mostrarLlegadaPedido(Pedido pedido, int minuto) {
                Console.imprimirln("Llega pedido: " + TipoDePlato.obtenerNombre(pedido.obtenerTipo()) +
                                " (" + pedido.obtenerTiempoPreparacion() + " min)");
        }

        public static void mostrarEstadoCocina(Cocinero cocinero, Arbol cola, int minuto) {
                Console.imprimirln("COLA: " + cola.obtenerTamaño() + " pedidos");

                if (cocinero.estaOcupado()) {
                        Pedido pedidoActual = cocinero.obtenerPedidoActual();
                        Console.imprimirln("Cocinero: [" + TipoDePlato.obtenerNombre(pedidoActual.obtenerTipo()) +
                                        " - " + pedidoActual.obtenerTiempoPreparacion() + " min restantes]");
                } else {
                        Console.imprimirln("Cocinero: [Libre]");
                }
        }

        public static void mostrarResumen(EstadisticasJornada estadisticas) {
                Console.saltoLinea();
                Console.imprimirln("RESUMEN DE LA JORNADA");
                Console.separador();

                Console.imprimirln("Pedidos atendidos        : " + estadisticas.obtenerPedidosAtendidos());
                Console.imprimirln("Pedidos pendientes       : " + estadisticas.obtenerPedidosPendientes());
                Console.imprimirln(
                                "Tiempo total de espera   : " + estadisticas.obtenerTiempoTotalEspera() + " minutos");

                double tiempoMedio = estadisticas.obtenerTiempoMedioEspera();
                Console.imprimirln("Tiempo medio de espera   : " + String.format("%.1f", tiempoMedio) + " minutos");
                Console.imprimirln("Comparaciones totales    : " + estadisticas.obtenerComparacionesTotales());
                Console.separador();
        }

        public static void mostrarEncabezadoMinuto(int minuto) {
                Console.separador();
                Console.imprimirln("[" + minuto + "]");
        }
}
