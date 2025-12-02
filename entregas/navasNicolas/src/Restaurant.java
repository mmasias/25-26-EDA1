import java.util.Random;
import java.util.Scanner;

class Restaurant {
    private final double OPENING_TIME = 9.0;
    private final double CLOSING_TIME = 21.0;
    private final double MINUTE = 1.0 / 60.0;
    private final double PROBABILITY_ARRIVAL = 0.4;
    
    private MinHeap orderQueue;
    private Order currentOrder; 
    private Random random;

    private int completedOrders = 0;
    private double totalWaitTime = 0;
    private double currentTime; 

    public Restaurant() {
        this.orderQueue = new MinHeap(350);
        this.random = new Random();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== INICIO DE JORNADA RCCCF ===");
        
        for (double time = OPENING_TIME; time < CLOSING_TIME; time = time + MINUTE) {
            this.currentTime = time; 
            
            validateArrival();
            
            processQueue();
            
            showSummary(); 
            
            orderQueue.printTree();
            scanner.nextLine();
        }
        
        printFinalReport();
        scanner.close();
    }

    private void showSummary() {
        String timeStr = String.format("%.2f", currentTime).replace(',', ':');
        String chefStatus = (currentOrder == null) ? "LIBRE" : currentOrder.toString();
        
        System.out.printf("[%s] Cola: %d | Cocinero: %s%n", 
            timeStr, orderQueue.size(), chefStatus);
    }

    private void processQueue() {
        if (currentOrder == null && !orderQueue.isEmpty()) {
            currentOrder = orderQueue.extractMin();
            double waitHours = currentTime - currentOrder.arrivalTime;
            totalWaitTime += (waitHours * 60); 
        }

        if (currentOrder != null) {
            currentOrder.remainingTime--;

            if (currentOrder.remainingTime <= 0) {
                completedOrders++;
                System.out.println("   >>> ¡Pedido Terminado: " + currentOrder.type + "!");
                currentOrder = null;
            }
        }
    }

    private void validateArrival() {
        if (random.nextDouble() < PROBABILITY_ARRIVAL) {
            Order newOrder = generateRandomOrder();
            orderQueue.insert(newOrder);
            System.out.println("   >>> Nuevo Pedido: " + newOrder.type + " (" + newOrder.totalTime + " min)");
        }
    }

    private Order generateRandomOrder() {
        int type = random.nextInt(5);
        return switch (type) {
            case 0 -> new Order("Bebida", 1 + random.nextInt(2), currentTime);
            case 1 -> new Order("Café", 2 + random.nextInt(2), currentTime);
            case 2 -> new Order("Colacao", 2 + random.nextInt(3), currentTime);
            case 3 -> new Order("Bocadillo", 3 + random.nextInt(3), currentTime);
            case 4 -> new Order("Ensalada", 5 + random.nextInt(4), currentTime);
            default -> new Order("Desconocido", 1, currentTime);
        };
    }

    private void printFinalReport() {
        System.out.println("\n========================================");
        System.out.println("RESUMEN FINAL DE JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos      : " + completedOrders);
        System.out.println("Pedidos pendientes     : " + orderQueue.size());
        if (completedOrders > 0) {
            System.out.printf("Tiempo medio espera    : %.2f minutos%n", (totalWaitTime / completedOrders));
        }
        System.out.println("Comparaciones (Heap)   : " + orderQueue.getComparisons());
        System.out.println("========================================");
    }
}