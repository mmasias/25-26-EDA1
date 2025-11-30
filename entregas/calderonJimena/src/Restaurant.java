import java.util.Random;
import java.util.Scanner;

class Restaurant {
    private final double OPENING_TIME = 9.0;
    private final double CLOSING_TIME = 21.0;
    private final double MINUTE = 1.0 / 60.0;
    private final double PROBABILITY_ARRIVAL = 0.4;

    private static final String[] PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS = {
        {1, 2}, 
        {2, 3}, 
        {2, 4}, 
        {3, 5}, 
        {5, 8}  
    };

    private MinHeap colaPedidos;
    private Pedido pedidoEnProceso;
    private int pedidosAtendidos;
    private int tiempoTotalEspera;
    private Random random;
    private Scanner scanner;
    private int minutoActual;

    public Restaurant() {
        colaPedidos = new MinHeap();
        pedidoEnProceso = null;
        pedidosAtendidos = 0;
        tiempoTotalEspera = 0;
        random = new Random();
        scanner = new Scanner(System.in);
        minutoActual = 0;
    }

    public void run() {
        System.out.println("Presiona ENTER para comenzar la simulación...");
        scanner.nextLine();
        System.out.println();

        for (double time = OPENING_TIME; time < CLOSING_TIME; time += MINUTE) {
            minutoActual++; 

            validateArrival();
            processQueue();
            showSummary();

            if (minutoActual < 720) {
                System.out.print("Presiona ENTER para continuar...");
                scanner.nextLine();
                System.out.println();
            }
        }

        int pendientes = colaPedidos.size();
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pendientes);
        System.out.println("Tiempo total de espera   : " + tiempoTotalEspera + " minutos");

        double media = pedidosAtendidos > 0 ? (double) tiempoTotalEspera / pedidosAtendidos : 0;
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", media);
        System.out.println("Comparaciones totales    : " + colaPedidos.getComparaciones());
        System.out.println("========================================");

        scanner.close();
    }

    private void validateArrival() {
        if (random.nextDouble() < PROBABILITY_ARRIVAL) {
            int idx = random.nextInt(PLATOS.length);
            String tipo = PLATOS[idx];
            int minT = RANGOS[idx][0];
            int maxT = RANGOS[idx][1];
            int tiempo = minT + random.nextInt(maxT - minT + 1);
            colaPedidos.insertar(new Pedido(tipo, tiempo, minutoActual));
            int hora = 9 + (minutoActual - 1) / 60;
            int min = (minutoActual - 1) % 60;
            System.out.println("[" + hora + "." + String.format("%02d", min) + "]");
            System.out.println("Llega pedido: " + tipo + " (" + tiempo + " min)");
        } else {
            int hora = 9 + (minutoActual - 1) / 60;
            int min = (minutoActual - 1) % 60;
            System.out.println("[" + hora + "." + String.format("%02d", min) + "]");
        }
    }

    private void processQueue() {
        if (pedidoEnProceso == null && !colaPedidos.estaVacia()) {
            pedidoEnProceso = colaPedidos.extraerMin();
            tiempoTotalEspera += pedidoEnProceso.getTiempoEspera(minutoActual);
        }

        if (pedidoEnProceso != null) {
            pedidoEnProceso.decrementarTiempo();
            if (pedidoEnProceso.getTiempoRestante() == 0) {
                pedidosAtendidos++;
                pedidoEnProceso = null;
                if (!colaPedidos.estaVacia()) {
                    pedidoEnProceso = colaPedidos.extraerMin();
                    tiempoTotalEspera += pedidoEnProceso.getTiempoEspera(minutoActual);
                }
            }
        }
    }

    private void showSummary() {
        System.out.println("COLA: " + colaPedidos.size() + " pedidos");
        if (pedidoEnProceso != null) {
            System.out.println("Cocinero: [" + pedidoEnProceso.getTipo() + " - " +
                    pedidoEnProceso.getTiempoRestante() + " min restantes]");
        } else {
            System.out.println("Cocinero: [INACTIVO]");
        }
        System.out.println("========================================");
    }
}