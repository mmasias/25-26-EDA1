package entregas.puenteSergio.src;

import java.util.Random;

public class SimulacionRCCCF {
    private static final int DURACION_SIMULACION = 480;
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final Random aleatorio = new Random();

    public static void main(String[] args) {
        Restaurante restaurant = new Restaurante();

        for (int tiempo = 1; tiempo <= DURACION_SIMULACION; tiempo++) {
            System.out.println("========================================");
            System.out.printf("[%.1f]\n", (double) tiempo);

            if (aleatorio.nextDouble() < PROBABILIDAD_LLEGADA) {
                Pedido nuevoPedido = generarPedidoAleatorio(tiempo);
                restaurant.anadirPedido(nuevoPedido);
                System.out.printf("Llega pedido: %s\n", nuevoPedido);
            }

            restaurant.tic(tiempo);

            System.out.printf("COLA: %d pedidos\n", restaurant.getTamanoCola());
            Pedido actual = restaurant.getPedidoActual();
            if (actual != null) {
                System.out.printf("Cocinero: %s\n", actual.aCadenaEstado());
            } else {
                System.out.println("Cocinero: [Esperando pedido]");
            }
        }

        imprimirResumen(restaurant);
    }

    private static Pedido generarPedidoAleatorio(double tiempoLlegada) {
        int indiceTipo = aleatorio.nextInt(5);
        String tipo;
        int tiempoMin, tiempoMax;

        switch (indiceTipo) {
            case 0:
                tipo = "Bebida";
                tiempoMin = 1;
                tiempoMax = 2;
                break;
            case 1:
                tipo = "Café";
                tiempoMin = 2;
                tiempoMax = 3;
                break;
            case 2:
                tipo = "Colacao";
                tiempoMin = 2;
                tiempoMax = 4;
                break;
            case 3:
                tipo = "Bocadillo";
                tiempoMin = 3;
                tiempoMax = 5;
                break;
            case 4:
                tipo = "Ensalada";
                tiempoMin = 5;
                tiempoMax = 8;
                break;
            default:
                tipo = "Desconocido";
                tiempoMin = 1;
                tiempoMax = 1;
        }

        int tiempoPrep = tiempoMin + aleatorio.nextInt(tiempoMax - tiempoMin + 1);
        return new Pedido(tipo, tiempoPrep, tiempoLlegada);
    }

    private static void imprimirResumen(Restaurante restaurant) {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.printf("Pedidos atendidos        : %d\n", restaurant.getPedidosCompletados());
        System.out.printf("Pedidos pendientes       : %d\n", restaurant.getContadorPedidosPendientes());
        System.out.printf("Tiempo total de espera   : %d minutos\n", restaurant.getTiempoTotalEspera());

        double esperaMedia = restaurant.getPedidosCompletados() > 0
                ? (double) restaurant.getTiempoTotalEspera() / restaurant.getPedidosCompletados()
                : 0.0;

        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", esperaMedia);
        System.out.printf("Comparaciones totales    : %d\n", restaurant.getContadorComparaciones());
        System.out.println("========================================");
    }
}
