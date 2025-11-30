import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

public class Simulacion {
    private static final int DURACION_JORNADA = 120;
    private static final double PROB_LLEGADA = 0.4;
    private static final Random rand = new Random();
    private static final String[] TIPOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS = {{1,2},{2,3},{2,4},{3,5},{5,8}};

    public static void main(String[] args) {
        Comparator<Pedido> comparadorPedidos = (a, b) -> {
            int cmp = Integer.compare(a.getTiempoPreparacion(), b.getTiempoPreparacion());
            if (cmp == 0) return Integer.compare(a.getTiempoLlegada(), b.getTiempoLlegada());
            return cmp;
        };

        PriorityQueue<Pedido> cola = new PriorityQueue<>(comparadorPedidos);
        Pedido cocinero = null;
        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;
        int totalComparaciones = 0;

        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            System.out.println("========================================");
            System.out.printf("[%d.0]\n", minuto);

            if (rand.nextDouble() < PROB_LLEGADA) {
                int tipoIdx = rand.nextInt(TIPOS.length);
                String tipo = TIPOS[tipoIdx];
                int[] r = RANGOS[tipoIdx];
                int tiempoPrep = r[0] + rand.nextInt(r[1] - r[0] + 1);

                Pedido nuevo = new Pedido(tipo, tiempoPrep, minuto);
                cola.offer(nuevo);

                int n = cola.size();
                int comp = (int) Math.ceil(Math.log(n) / Math.log(2));
                totalComparaciones += Math.max(1, comp);

                System.out.printf("Llega pedido: %s (%d min)\n", tipo, tiempoPrep);
            }

            if (cocinero != null && cocinero.terminado()) {
                int espera = (minuto - 1) - cocinero.getTiempoLlegada();
                tiempoTotalEspera += espera;
                pedidosAtendidos++;

                if (!cola.isEmpty()) {
                    cocinero = cola.poll();
                    int n = cola.size() + 1;
                    int comp = (int) Math.ceil(Math.log(n) / Math.log(2));
                    totalComparaciones += Math.max(1, comp);
                } else {
                    cocinero = null;
                }
            }

            if (cocinero == null && !cola.isEmpty()) {
                cocinero = cola.poll();
                int n = cola.size() + 1;
                int comp = (int) Math.ceil(Math.log(n) / Math.log(2));
                totalComparaciones += Math.max(1, comp);
            }

            System.out.printf("COLA: %d pedidos\n", cola.size());

            if (cocinero != null) {
                cocinero.decrementarTiempo();
                System.out.printf("Cocinero: [%s - %d min restantes]\n",
                        cocinero.getTipo(),
                        cocinero.getTiempoRestante());
            } else {
                System.out.println("Cocinero: [OCIOSO]");
            }
        }

        int pendientes = cola.size();
        if (cocinero != null && !cocinero.terminado()) {
            pendientes++;
        }

        double tiempoMedio = (double) tiempoTotalEspera / Math.max(1, pedidosAtendidos);

        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.printf("Pedidos atendidos        : %d\n", pedidosAtendidos);
        System.out.printf("Pedidos pendientes       : %d\n", pendientes);
        System.out.printf("Tiempo total de espera   : %d minutos\n", tiempoTotalEspera);
        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", tiempoMedio);
        System.out.printf("Comparaciones totales    : %d\n", totalComparaciones);
        System.out.println("========================================");
    }
}
