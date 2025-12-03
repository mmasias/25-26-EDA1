package Restaurante;

import java.util.Random;

public class RCCCFArbol {

    public static void main(String[] args) {

        ArbolPedidos cola = new ArbolPedidos();
        Random random = new Random();
        Pedido cocinando = null;

        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;

        String[] tipos = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
        int[][] rangos = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

        for (int minuto = 0; minuto < 720; minuto++) {
            
            System.out.println("========================================");
            System.out.printf("[%d:%02d]\n", 9 + minuto / 60, minuto % 60);

            if (random.nextDouble() < 0.4) {
                int idx = random.nextInt(tipos.length);
                int tiempo = rangos[idx][0] + random.nextInt(rangos[idx][1] - rangos[idx][0] + 1);
                Pedido nuevo = new Pedido(tipos[idx], tiempo, minuto);
                cola.insertar(nuevo);
                System.out.println("Llega pedido: " + nuevo.tipo + " (" + tiempo + " min)");
            }

            System.out.println("COLA: " + cola.contarNodos() + " pedidos");

            if (cocinando == null && !cola.estaVacio()) {
                cocinando = cola.extraerMin();
            }

            if (cocinando != null) {
                System.out.println("Cocinero: " + cocinando);
                cocinando.tiempoPreparacion--;
                if (cocinando.tiempoPreparacion == 0) {
                    pedidosAtendidos++;
                    tiempoTotalEspera += (minuto - cocinando.tiempoLlegada);
                    cocinando = null;
                }
            } else {
                System.out.println("Cocinero: libre");
            }
        }

        System.out.println("\n========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cola.contarNodos());
        System.out.println("Tiempo total de espera   : " + tiempoTotalEspera + " minutos");
        System.out.printf("Tiempo medio de espera   : %.2f minutos\n",
                pedidosAtendidos > 0 ? (double) tiempoTotalEspera / pedidosAtendidos : 0);
        System.out.println("Comparaciones totales    : " + cola.comparaciones);
        System.out.println("========================================");
    }
}
