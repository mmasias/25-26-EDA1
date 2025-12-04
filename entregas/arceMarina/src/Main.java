import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        int idCounter = 1;
        int jornada = 720;

        for (int minuto = 1; minuto <= jornada; minuto++) {
            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            if (Math.random() < 0.40) {
                Pedido nuevo = new Pedido(idCounter++, minuto);
                restaurante.recibirPedido(nuevo);
            }

            restaurante.gestionarTurno(minuto);
            
            System.out.println("COLA: " + restaurante.getCantidadCola() + " pedidos");
            System.out.println(restaurante.getTextoEstadoCocinero());

            if (minuto % 60 == 0) {
                System.out.println("\n>>> Fin de la hora " + (minuto / 60) + ". Pulsa ENTER para continuar...");
                scanner.nextLine();
            }
        }

        restaurante.mostrarReporteFinal();
    }
}