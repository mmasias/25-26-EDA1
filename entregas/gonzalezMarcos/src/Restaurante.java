import java.util.Random;

public class Restaurante {

    private static final int DURACION_JORNADA = 240;
    private static final double PROBABILIDAD_LLEGADA = 0.40;

    private static final Object[][] PLATOS = {
            { "Café", 1, 2 },
            { "Bocadillo", 3, 5 },
            { "Ensalada", 5, 8 },
            { "Pasta", 10, 15 },
            { "Cocido", 20, 25 }
    };

    public void iniciar() {
        Monticulo colaPedidos = new Monticulo(100);
        Random random = new Random();

        Pedido pedidoEnCocina = null;

        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;

        System.out.println("========================================");

        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            boolean eventoOcurrido = false;
            String log = "";
            log += "[" + minuto + ".0]\n";

            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                Object[] platoInfo = PLATOS[random.nextInt(PLATOS.length)];
                String nombre = (String) platoInfo[0];
                int minT = (int) platoInfo[1];
                int maxT = (int) platoInfo[2];
                int tiempoPrep = minT + random.nextInt(maxT - minT + 1);

                Pedido nuevoPedido = new Pedido(nombre, tiempoPrep, minuto);
                colaPedidos.insert(nuevoPedido);

                log += "Llega pedido: " + nombre + " (" + tiempoPrep + " min)\n";
                eventoOcurrido = true;
            }

            if (pedidoEnCocina == null && !colaPedidos.isEmpty()) {
                pedidoEnCocina = colaPedidos.extractMin();
            }

            if (pedidoEnCocina != null) {
                pedidoEnCocina.tiempoRestante--;

                if (pedidoEnCocina.tiempoRestante == 0) {
                    pedidosAtendidos++;
                    int tiempoEnSistema = (minuto - pedidoEnCocina.minutoLlegada + 1);
                    tiempoTotalEspera += (tiempoEnSistema - pedidoEnCocina.tiempoTotal);

                    if (!colaPedidos.isEmpty()) {
                        pedidoEnCocina = colaPedidos.extractMin();
                    } else {
                        pedidoEnCocina = null;
                    }
                }
            }

            if (eventoOcurrido || minuto <= 5 || minuto % 60 == 0) {
                System.out.print(log);
                System.out.println("COLA: " + colaPedidos.size() + " pedidos");
                if (pedidoEnCocina != null) {
                    System.out.println("Cocinero: [" + pedidoEnCocina.tipo + " - " + pedidoEnCocina.tiempoRestante
                            + " min restantes]");
                } else {
                    System.out.println("Cocinero: [Esperando]");
                }
                System.out.println(minuto % 60 == 0 ? "----------------------------------------"
                        : "========================================");
            }
        }

        int pedidosPendientes = colaPedidos.size() + (pedidoEnCocina != null ? 1 : 0);

        double tiempoMedioEspera = pedidosAtendidos > 0 ? (double) tiempoTotalEspera / pedidosAtendidos : 0;

        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pedidosPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoTotalEspera + " minutos (solo completados)");
        System.out.println("Tiempo medio de espera   : " + tiempoMedioEspera + " minutos");
        System.out.println("Comparaciones totales    : " + colaPedidos.getComparaciones());
        System.out.println("========================================");
    }
}