import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

public class Kitchen {

    // Cola de prioridad según tiempo restante (no desde Order)
    private final PriorityQueue<Order> queue =
            new PriorityQueue<>(Comparator.comparingInt(o -> o.remaining));

    private final Random random = new Random();

    private int totalComparisons = 0;
    private int attended = 0;
    private int totalWait = 0;

    private Order current = null;

    public void simulateDay(int minutes) {

        System.out.println("========================================");

        for (int minute = 1; minute <= minutes; minute++) {
            System.out.println("[" + minute + ".0]");

            // 40% probabilidad de llegada
            if (random.nextDouble() < 0.40) {
                DishType t = DishType.values()[random.nextInt(DishType.values().length)];
                int prep = t.generatePrepTime(random);

                Order o = new Order(t, prep, minute);
                queue.add(o);

                System.out.println("Llega pedido: " + t + " (" + prep + " min)");
            }

            // aumentar espera
            for (Order o : queue) {
                o.waitTime++;
            }

            // si no hay pedido en curso
            if (current == null) {
                current = pollMin();
                if (current != null)
                    System.out.println("Cocinero: [" + current + "]");
            } else {

                current.remaining--;

                if (current.remaining == 0) {
                    attended++;
                    totalWait += current.waitTime;

                    current = pollMin();
                    if (current != null)
                        System.out.println("Cocinero: [" + current + "]");
                } else {
                    System.out.println("Cocinero: [" +
                            current.type + " - " + current.remaining + " min restantes]");
                }
            }

            System.out.println("COLA: " + queue.size() + " pedidos");
            System.out.println("========================================");
        }

        summary();
    }

    private Order pollMin() {
        if (queue.isEmpty()) return null;

        // comparaciones aproximadas de heap
        int n = queue.size();
        totalComparisons += (int) (Math.log(n) / Math.log(2));

        return queue.poll();
    }

    private void summary() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + attended);
        System.out.println("Pedidos pendientes       : " + queue.size());
        System.out.println("Tiempo total de espera   : " + totalWait + " minutos");
        double avg = attended == 0 ? 0 : (double) totalWait / attended;
        System.out.println("Tiempo medio de espera   : " + String.format("%.2f", avg));
        System.out.println("Comparaciones totales    : " + totalComparisons);
        System.out.println("========================================");
    }
}
