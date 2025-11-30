import java.util.*;

public class Simulation {

    private static final double PROB_LLEGADA = 0.4;
    private static final int DURACION = 120;

    public static void main(String[] args) {
        Random rand = new Random();
        Cocina cocina = new Cocina();
        List<Pedido> atendidos = new ArrayList<>();
        List<Pedido> pendientes = new ArrayList<>();

        Pedido current = null;

        for (int minuto = 1; minuto <= DURACION; minuto++) {
            System.out.println("========================================");
            System.out.printf("[%.1f]\n", (double) minuto);

            // Llegada de pedido
            if (rand.nextDouble() < PROB_LLEGADA) {
                Pedido.Plato plato = Pedido.Plato.values()[rand.nextInt(Pedido.Plato.values().length)];
                Pedido p = new Pedido(plato, plato.tiempoAleatorio(rand), minuto);
                cocina.agregarPedido(p);
                System.out.println("Llega pedido: " + p);
            }

            // Si cocinero libre, tomar pedido
            if (current == null && cocina.pedidosEnCola() > 0) {
                current = cocina.obtenerPedido();
                current.inicio = minuto;
            }

            // Mostrar estado
            System.out.println("COLA: " + cocina.pedidosEnCola() + " pedidos");
            if (current != null) {
                System.out.println("Cocinero: [" + current.mostrarEstado() + "]");
            } else {
                System.out.println("Cocinero: [Libre]");
            }

            // Procesar 1 minuto
            if (current != null) {
                current.tiempoRestante--;
                if (current.tiempoRestante <= 0) {
                    current.fin = minuto;
                    atendidos.add(current);
                    current = null;
                }
            }
        }

        // Pedidos pendientes al cierre
        if (current != null) pendientes.add(current);
        while (cocina.pedidosEnCola() > 0) pendientes.add(cocina.obtenerPedido());

        // Calcular métricas
        int totalAtendidos = atendidos.size();
        int totalPendientes = pendientes.size();
        long tiempoTotalEspera = 0;

        for (Pedido p : atendidos) tiempoTotalEspera += (p.inicio - p.llegada);
        for (Pedido p : pendientes) tiempoTotalEspera += (DURACION - p.llegada);

        double tiempoMedio = (totalAtendidos + totalPendientes) > 0
                ? (double) tiempoTotalEspera / (totalAtendidos + totalPendientes) : 0;

        // Resumen
        System.out.println("========================================\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + totalAtendidos);
        System.out.println("Pedidos pendientes       : " + totalPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoTotalEspera + " minutos");
        System.out.printf("Tiempo medio de espera   : %.2f minutos\n", tiempoMedio);
        System.out.println("Comparaciones totales    : " + cocina.comparaciones.get());
        System.out.println("========================================");
    }
}
