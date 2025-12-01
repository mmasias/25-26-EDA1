import java.util.Scanner;

public class Restaurant {
    private final int DURACION_JORNADA = 720;
    private final double PROBABILITY_ARRIVAL = 0.4;

    private ArbolPedidos cola;
    private Pedido pedidoActual;
    private int tiempoRestante;
    private int pedidosAtendidos;
    private int tiempoTotalEsperaAtendidos;
    private int minutoActual;
    private Scanner scanner;

    private final String[] TIPOS_PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final int[][] RANGOS_TIEMPO = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

    public Restaurant() {
        cola = new ArbolPedidos();
        pedidoActual = null;
        tiempoRestante = 0;
        pedidosAtendidos = 0;
        tiempoTotalEsperaAtendidos = 0;
        minutoActual = 0;
        scanner = new Scanner(System.in);
    }

    public void run() {
        for (minutoActual = 1; minutoActual <= DURACION_JORNADA; minutoActual++) {


            validarLlegada();
            procesarCocina();
            mostrarEstado(minutoActual);
            System.out.println("(Presiona ENTER para continuar, o 'V' para ver el árbol)");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("V")) {
                System.out.println("--- VISUALIZACIÓN DEL ÁRBOL ---");
                cola.printTree();
                System.out.println("-".repeat(40));
                System.out.println("(Presiona ENTER para continuar)");
                scanner.nextLine();
            }
        }
        mostrarResumen();
    }

    private void validarLlegada() {
        if (Math.random() < PROBABILITY_ARRIVAL) {
            int tipoIndex = (int)(Math.random() * TIPOS_PLATOS.length);
            String tipo = TIPOS_PLATOS[tipoIndex];
            int tiempoMin = RANGOS_TIEMPO[tipoIndex][0];
            int tiempoMax = RANGOS_TIEMPO[tipoIndex][1];
            int tiempo = tiempoMin + (int)(Math.random() * (tiempoMax - tiempoMin + 1));

            Pedido nuevoPedido = new Pedido(tipo, tiempo);

            System.out.println("Llega pedido: " + tipo + " (" + tiempo + " min)");

            if (pedidoActual == null) {
                pedidoActual = nuevoPedido;
                tiempoRestante = tiempo;
            } else {
                cola.insertar(nuevoPedido);
            }
        }
    }

    private void procesarCocina() {
        if (pedidoActual != null) {
            tiempoRestante--;

            if (tiempoRestante == 0) {
                pedidosAtendidos++;
                tiempoTotalEsperaAtendidos += pedidoActual.getTiempoEspera();

                if (!cola.isEmpty()) {
                    pedidoActual = cola.extraerMinimo();
                    tiempoRestante = pedidoActual.getTiempoPreparacion();
                } else {
                    pedidoActual = null;
                }
            }
        }

        cola.incrementarEspera();
        if (pedidoActual != null) {
            pedidoActual.incrementarEspera();
        }
    }

    private void mostrarEstado(int minuto) {
        cleanScreen();
        System.out.println("=".repeat(40));
        System.out.println("[" + minuto + ".0]");
        System.out.println("-".repeat(40));
        System.out.println("COLA: " + cola.tamaño() + " pedidos");
        if (pedidoActual != null) {
            System.out.println("Cocinero: [" + pedidoActual.getTipo() + " - " + tiempoRestante + " min restantes]");
        } else {
            System.out.println("Cocinero: [Inactivo]");
        }
    }

    private void mostrarResumen() {
        cleanScreen();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("=".repeat(40));
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + (cola.tamaño() + (pedidoActual != null ? 1 : 0)));

        int tiempoTotalPendientes = cola.tiempoTotalEspera();
        if (pedidoActual != null) {
            tiempoTotalPendientes += pedidoActual.getTiempoEspera();
        }
        int tiempoTotalGlobal = tiempoTotalEsperaAtendidos + tiempoTotalPendientes;

        System.out.println("Tiempo total de espera   : " + tiempoTotalGlobal + " minutos");

        int totalPedidos = pedidosAtendidos + cola.tamaño() + (pedidoActual != null ? 1 : 0);
        double tiempoMedio = totalPedidos > 0 ? (double)tiempoTotalGlobal / totalPedidos : 0;
        System.out.println("Tiempo medio de espera   : " + String.format("%.1f", tiempoMedio) + " minutos");
        System.out.println("Comparaciones totales    : " + cola.getComparaciones());
        System.out.println("=".repeat(40));
    }

  private void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}
