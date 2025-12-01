public class GeneradorDePedidos {
        private static final double PROBABILIDAD_LLEGADA = 0.4;

        public Pedido intentarGenerar(int minutoActual) {
                if (Math.random() < PROBABILIDAD_LLEGADA) {
                        int tipoAleatorio = TipoDePlato.generarTipoAleatorio();
                        return new Pedido(tipoAleatorio, minutoActual);
                }
                return null;
        }

        public static void main(String[] args) {
                Console.imprimirln("=== Pruebas de GeneradorDePedidos ===");
                Console.saltoLinea();

                GeneradorDePedidos generador = new GeneradorDePedidos();

                Console.imprimirln("Generando pedidos durante 20 intentos:");
                int pedidosGenerados = 0;

                for (int intento = 1; intento <= 20; intento++) {
                        Pedido pedido = generador.intentarGenerar(intento);
                        if (pedido != null) {
                                pedidosGenerados++;
                                Console.imprimirln("Intento " + intento + ": Llegó " +
                                                TipoDePlato.obtenerNombre(pedido.obtenerTipo()) +
                                                " (" + pedido.obtenerTiempoPreparacion() + " min)");
                        } else {
                                Console.imprimirln("Intento " + intento + ": No llegó ningún pedido");
                        }
                }

                Console.saltoLinea();
                Console.imprimirln("Total pedidos generados: " + pedidosGenerados + "/20");
                double porcentaje = (double) pedidosGenerados / 20 * 100;
                Console.imprimirln("Porcentaje: " + porcentaje + "% (esperado ~40%)");

                Console.saltoLinea();
                Console.imprimirln("=== Todas las pruebas completadas ===");
        }
}
