public class Simulation {
    public static void main(String[] args) {
        System.out.println("========================================");
        Restaurante r = new Restaurante();
        int DURACION = 120;

        for (int min = 1; min <= DURACION; min++) {
            System.out.println("[" + min + ".0]");
            r.avanzarMinuto(min);
            if (min < DURACION) {
                System.out.println("========================================");
            }
        }

        r.cerrar(DURACION);

        int totalPedidos = r.pedidosAtendidos + r.getPedidosPendientes();
        double tiempoMedio = 0.0;
        if (totalPedidos > 0) {
            tiempoMedio = (double) r.tiempoTotalEspera / totalPedidos;
        }

        System.out.println();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + r.pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + r.getPedidosPendientes());
        System.out.println("Tiempo total de espera   : " + r.tiempoTotalEspera + " minutos");
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", tiempoMedio);
        System.out.println("Comparaciones totales    : " + r.comparaciones);
        System.out.println("========================================");
    }
}