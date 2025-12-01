import java.util.PriorityQueue;
import java.util.Random;

public class Kitchen {

    private PriorityQueue<Order> cola = new PriorityQueue<>();
    private Order enProceso = null;
    private Random rand = new Random();

    private int totalAtendidos = 0;
    private int totalComparaciones = 0;
    private int tiempoEsperaAcumulado = 0;

    private boolean llegaPedido() {
        return rand.nextDouble() < 0.40;
    }

    private Order generarPedido(int minuto) {

        int tipoAleatorio = rand.nextInt(5) + 1;
        String tipo = "";
        int tiempo = 0;

        switch (tipoAleatorio) {
            case 1:
                tipo = "Bebida";
                tiempo = rand.nextInt(2 - 1 + 1) + 1; // 1-2
                break;
            case 2:
                tipo = "Café";
                tiempo = rand.nextInt(3 - 2 + 1) + 2; // 2-3
                break;
            case 3:
                tipo = "Colacao";
                tiempo = rand.nextInt(4 - 2 + 1) + 2; // 2-4
                break;
            case 4:
                tipo = "Bocadillo";
                tiempo = rand.nextInt(5 - 3 + 1) + 3; // 3-5
                break;
            case 5:
                tipo = "Ensalada";
                tiempo = rand.nextInt(8 - 5 + 1) + 5; // 5-8
                break;
        }

        return new Order(tipo, tiempo, minuto);
    }

    public void simular(int minutos) {

        for (int minuto = 1; minuto <= minutos; minuto++) {

            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            
            if (llegaPedido()) {
                Order nuevo = generarPedido(minuto);
                cola.add(nuevo);
                System.out.println("Llega pedido: " + nuevo.getTipo() +
                                   " (" + nuevo.getTiempoRestante() + " min)");
            }

            
            if (enProceso == null && !cola.isEmpty()) {
                totalComparaciones += cola.size();
                enProceso = cola.poll();
            }

            
            if (enProceso != null) {
                enProceso.procesarUnMinuto();

                if (enProceso.getTiempoRestante() == 0) {
                    totalAtendidos++;

                    int espera = (minuto - enProceso.getLlegada() - enProceso.getTiempoTotal());
                    tiempoEsperaAcumulado += espera;

                    enProceso = null;

                    if (!cola.isEmpty()) {
                        totalComparaciones += cola.size();
                        enProceso = cola.poll();
                    }
                }
            }
            System.out.println("COLA: " + cola.size() + " pedidos");

            if (enProceso != null) {
                System.out.println("Cocinero: [" + enProceso.getTipo() +
                                   " - " + enProceso.getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: libre");
            }
        }

        imprimirResumen();
    }

    private void imprimirResumen() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + totalAtendidos);
        System.out.println("Pedidos pendientes       : " + cola.size());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaAcumulado + " minutos");

        double promedio = totalAtendidos > 0 ?
                (double) tiempoEsperaAcumulado / totalAtendidos : 0;

        System.out.printf("Tiempo medio de espera   : %.2f minutos\n", promedio);
        System.out.println("Comparaciones totales    : " + totalComparaciones);
        System.out.println("========================================");
    }
}
