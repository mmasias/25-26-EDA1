import java.util.Scanner;

public class Simulacion {

    public void iniciarJornada() {
        Cocina cocina = new Cocina();
        GeneradorPedidos generador = new GeneradorPedidos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   SIMULACIÓN RCCCF (Interactivo)       ");
        System.out.println("========================================");
        System.out.println("Instrucciones:");
        System.out.println(" - Presiona [ENTER] para avanzar un minuto.");
        System.out.println(" - Escribe 'b' y [ENTER] para ver la cola de prioridad.");
        System.out.println("========================================");

        for (int minuto = 1; minuto <= 120; minuto++) {
            System.out.println("\n[" + minuto + ".0]");

            if (generador.llegaPedido()) {
                Pedido nuevo = generador.crearPedido(minuto);
                cocina.agregarPedido(nuevo);
                System.out.println("Llega pedido: " + nuevo.getTIPO() + " (" + nuevo.getTIEMPO_PREP() + " min)");
            }

            cocina.procesarMinuto();

            System.out.println("COLA: " + cocina.getTamanoCola() + " pedidos");

            Pedido actual = cocina.getPedidoActual();
            if (actual != null) {
                System.out.println(
                        "Cocinero: [" + actual.getTIPO() + " - " + actual.getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: [Libre]");
            }

            System.out.println("----------------------------------------");

            boolean avanzar = false;
            while (!avanzar) {
                System.out.print(">> [Enter] Avanzar | [B] Ver prioridad: ");
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("b")) {
                    cocina.mostrarColaConPrioridad();
                } else {
                    avanzar = true;
                }
            }
        }

        mostrarResumen(cocina);
        scanner.close();
    }

    private void mostrarResumen(Cocina cocina) {
        System.out.println("\n========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos      : " + cocina.getTotalAtendidos());
        System.out.println("Pedidos pendientes     : " + cocina.getPendientes());
        System.out.println("Tiempo total espera    : " + cocina.getTiempoEsperaTotal() + " min");

        double media = 0;
        if (cocina.getTotalAtendidos() > 0) {
            media = (double) cocina.getTiempoEsperaTotal() / cocina.getTotalAtendidos();
        }
        System.out.println("Tiempo medio espera    : " + media + " min");
        System.out.println("Comparaciones totales  : " + cocina.getNumeroComparaciones());
        System.out.println("========================================");
    }
}