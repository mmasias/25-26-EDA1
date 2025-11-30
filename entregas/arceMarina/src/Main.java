import java.util.*;

public class Main {
    private static final int JORNADA_MINUTOS = 720;
    private static final double PROBABILIDAD_LLEGADA = 0.40;

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Random random = new Random();
        int idCounter = 1;

        System.out.println("========================================");
        System.out.println("   INICIO SIMULACIÓN RCCCF (JAVA)      ");
        System.out.println("========================================");

        for (int minuto = 1; minuto <= JORNADA_MINUTOS; minuto++) {
            System.out.print("[" + minuto + ".0] ");

            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                Pedido nuevoPedido = new Pedido(idCounter++, minuto);
                System.out.print(restaurante.recibirPedido(nuevoPedido) + " | ");
            }

            System.out.println();

            restaurante.gestionarTurno(minuto);

            System.out.println("COLA: " + restaurante.obtenerTamanoCola() + " pedidos");
            System.out.println(restaurante.obtenerEstadoCocinero());
            System.out.println("----------------------------------------");
        }

        restaurante.mostrarReporteFinal();
    }
}