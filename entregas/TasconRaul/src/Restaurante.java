import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

class Restaurante {
    private final double OPENING_TIME = 9.0;
    private final double CLOSING_TIME = 21.0;
    private final double MINUTE = 1.0 / 60.0;
    private final double PROBABILITY_ARRIVAL = 0.4;
    

    private PriorityQueue<Pedido> colaPedidos;
    private Pedido pedidoEnProceso; 

    private int minutoActual = 0;
    private int pedidosAtendidos = 0;
    private int tiempoEsperaAcumulado = 0; 

    public Restaurante() {
        this.colaPedidos = new PriorityQueue<>();
    }

    public void run() {
        System.out.println("INICIANDO SIMULACIÓN RCCCF...");
        Scanner sc = new Scanner(System.in);

        for (double time = OPENING_TIME; time < CLOSING_TIME; time = time + MINUTE) {
            minutoActual++;
            
            System.out.println("========================================");
            System.out.println("[" + String.format("%.1f", time) + "h - Minuto " + minutoActual + "]");

            validateArrival(time);
            processQueue();

            showSummary(); 
            System.out.println();
            sc.nextLine();
        }
        sc.close();
        
        printFinalStats();
    }

    private void showSummary() {
        System.out.println("COLA: " + colaPedidos.size() + " pedidos pendientes");
        if (pedidoEnProceso != null) {
            System.out.println("Cocinero: [" + pedidoEnProceso.toString() + "]");
        } else {
            System.out.println("Cocinero: Libre");
        }
    }

    private void processQueue() {
        if (pedidoEnProceso == null && !colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.poll();
        }

        if (pedidoEnProceso != null) {
            pedidoEnProceso.cocinar();

            if (pedidoEnProceso.estaTerminado()) {
                System.out.println(">>> TERMINADO: " + pedidoEnProceso.getDetalle());
                pedidosAtendidos++;
                
                int minutoLlegada = (int)((pedidoEnProceso.getHoraLlegada() - OPENING_TIME) * 60);
                tiempoEsperaAcumulado += (minutoActual - minutoLlegada);

                pedidoEnProceso = null;
                if (!colaPedidos.isEmpty()) {
                    pedidoEnProceso = colaPedidos.poll();
                }
            }
        }
    }

    private void validateArrival(double currentTime) {
        if (Math.random() < PROBABILITY_ARRIVAL) {
            Pedido nuevo = generarPedidoAleatorio(currentTime);
            colaPedidos.add(nuevo); 
            System.out.println("Llega pedido: " + nuevo.getDetalle());
        }
    }
    
    private Pedido generarPedidoAleatorio(double currentTime) {
        Random rnd = new Random();
        int tipoRnd = rnd.nextInt(5);
        
        String nombre;
        int tiempo;

        switch (tipoRnd) {
            case 0: nombre = "Bebida";    tiempo = 1 + rnd.nextInt(2); break; 
            case 1: nombre = "Café";      tiempo = 2 + rnd.nextInt(2); break; 
            case 2: nombre = "Colacao";   tiempo = 2 + rnd.nextInt(3); break; 
            case 3: nombre = "Bocadillo"; tiempo = 3 + rnd.nextInt(3); break; 
            case 4: nombre = "Ensalada";  tiempo = 5 + rnd.nextInt(4); break; 
            default: nombre = "Agua";     tiempo = 1; 
        }
        return new Pedido(nombre, tiempo, currentTime);
    }

    private void printFinalStats() {
        System.out.println("\n########################################");
        System.out.println("RESUMEN FINAL DE LA JORNADA");
        System.out.println("########################################");
        System.out.println("Pedidos atendidos       : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes      : " + colaPedidos.size());
        System.out.println("Tiempo total espera     : " + tiempoEsperaAcumulado + " min");
        
        double media = pedidosAtendidos > 0 ? (double)tiempoEsperaAcumulado / pedidosAtendidos : 0;
        System.out.println("Tiempo medio de espera  : " + String.format("%.2f", media) + " min");
        
        System.out.println("EFICIENCIA (Comparaciones): " + Pedido.comparacionesTotales);
        System.out.println("########################################");
    }
}