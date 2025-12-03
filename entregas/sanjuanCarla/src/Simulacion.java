import java.util.Random;

public class Simulacion {
    private static final int DURACION_SIMULACION = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final Random random = new Random();
    private static final String[] TIPOS_PEDIDO = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS_TIEMPO_PREPARACION = {{1,2},{2,3},{2,4},{3,5},{5,8}};

    public static void main(String[] args) {

        ArbolPedidos arbolPedidos = new ArbolPedidos();
        Pedido pedidoEnPreparacion = null;

        int totalPedidosAtendidos = 0;
        int tiempoEsperaTotal = 0;
        int totalComparaciones = 0;

        for (int minutoActual = 1; minutoActual <= DURACION_SIMULACION; minutoActual++) {
            System.out.println("========================================");
            System.out.printf("[%d.0]\n", minutoActual);

            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                int indiceTipo = random.nextInt(TIPOS_PEDIDO.length);
                String tipoPedido = TIPOS_PEDIDO[indiceTipo];
                int[] rango = RANGOS_TIEMPO_PREPARACION[indiceTipo];
                int tiempoPreparacion = rango[0] + random.nextInt(rango[1] - rango[0] + 1);

                Pedido nuevoPedido = new Pedido(tipoPedido, tiempoPreparacion, minutoActual);

                arbolPedidos.reiniciarContadorComparaciones();
                arbolPedidos.insertarPedido(nuevoPedido);
                totalComparaciones += arbolPedidos.obtenerContadorComparaciones();

                System.out.printf("Llega pedido: %s (%d min)\n", tipoPedido, tiempoPreparacion);
            }

            if (pedidoEnPreparacion != null && pedidoEnPreparacion.estaTerminado()) {
                int tiempoEspera = (minutoActual - 1) - pedidoEnPreparacion.obtenerMinutoLlegada();
                tiempoEsperaTotal += tiempoEspera;
                totalPedidosAtendidos++;

                if (!arbolPedidos.estaVacio()) {
                    arbolPedidos.reiniciarContadorComparaciones();
                    pedidoEnPreparacion = arbolPedidos.extraerPedidoMasRapido();
                    totalComparaciones += arbolPedidos.obtenerContadorComparaciones();
                } else {
                    pedidoEnPreparacion = null;
                }
            }

            if (pedidoEnPreparacion == null && !arbolPedidos.estaVacio()) {
                arbolPedidos.reiniciarContadorComparaciones();
                pedidoEnPreparacion = arbolPedidos.extraerPedidoMasRapido();
                totalComparaciones += arbolPedidos.obtenerContadorComparaciones();
            }

            System.out.printf("COLA: %d pedidos\n", arbolPedidos.contarPedidos());

            if (pedidoEnPreparacion != null) {
                pedidoEnPreparacion.avanzarUnMinuto();
                System.out.printf("Cocinero: [%s - %d min restantes]\n",
                        pedidoEnPreparacion.obtenerTipoPedido(),
                        pedidoEnPreparacion.obtenerTiempoRestante());
            } else {
                System.out.println("Cocinero: [OCIOSO]");
            }
        }

        int pedidosPendientes = arbolPedidos.contarPedidos();
        if (pedidoEnPreparacion != null && !pedidoEnPreparacion.estaTerminado()) {
            pedidosPendientes++;
        }

        double tiempoEsperaPromedio = (double) tiempoEsperaTotal / Math.max(1, totalPedidosAtendidos);

        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.printf("Pedidos atendidos        : %d\n", totalPedidosAtendidos);
        System.out.printf("Pedidos pendientes       : %d\n", pedidosPendientes);
        System.out.printf("Tiempo total de espera   : %d minutos\n", tiempoEsperaTotal);
        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", tiempoEsperaPromedio);
        System.out.printf("Comparaciones totales    : %d\n", totalComparaciones);
        System.out.println("========================================");
    }
}

