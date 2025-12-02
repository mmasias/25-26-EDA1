public class Simulation {

    public static void main(String[] args) {

        final int DURACION = 120;

        colaPrioridadPedidos cola = new colaPrioridadPedidos();
        Cocinero cocinero = new Cocinero();

        System.out.println("========================================");

        for (int minuto = 1; minuto <= DURACION; minuto++) {

            System.out.println("[" + minuto + ".0]");

            if (generadorPedidos.llegaPedido()) {
                Pedido nuevo = generadorPedidos.generarPedido(minuto);
                System.out.println("Llega pedido: " + nuevo.getTipo() + " (" + nuevo.getTiempoTotal() + " min)");
                cola.insert(nuevo);
            }

            if (cocinero.getPedidoActual() == null && cola.size() > 0) {
                Pedido siguiente = cola.extractMin();
                cocinero.asignarPedido(siguiente, minuto);
            }

            System.out.println("COLA: " + cola.size() + " pedidos");

            if (cocinero.getPedidoActual() != null)
                System.out.println("Cocinero: [" + cocinero.getPedidoActual() + "]");
            else
                System.out.println("Cocinero: [Libre]");

            cocinero.procesar();

            System.out.println("----------------------------------------");
        }

        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + cocinero.getPedidosAtendidos());
        System.out.println("Pedidos pendientes       : " + cola.size());
        System.out.println("Tiempo total de espera   : " + cocinero.getTiempoEsperaTotal() + " minutos");

        double media = cocinero.getPedidosAtendidos() > 0
                ? (double) cocinero.getTiempoEsperaTotal() / cocinero.getPedidosAtendidos()
                : 0;

        System.out.printf("Tiempo medio de espera   : %.2f minutos\n", media);
        System.out.println("Comparaciones totales    : " + cola.getComparaciones());
        System.out.println("========================================");
    }
}

