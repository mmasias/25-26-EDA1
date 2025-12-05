package vaqueroInigo.reto005_refactorizacionMinimalista;

import java.util.Random;

public class SimuladorRCCCF {

    private int duracionJornada = 120;
    private double probabilidadLlegada = 0.4;
    private int pedidosAtendidos = 0;
    private int tiempoTotalEspera = 0;

    private Random generador = new Random();
    private ColaPedidos cola = new ColaPedidos();
    private Cocinero cocinero = new Cocinero();

    public static void main(String[] args) {
        SimuladorRCCCF simulador = new SimuladorRCCCF();
        simulador.ejecutar();
    }

    public SimuladorRCCCF() {
    }

    public void ejecutar() {
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            simularMinuto(minuto);
        }
        mostrarResumen();
    }

    public void simularMinuto(int minuto) {
        System.out.println("========================================");
        System.out.printf("[%.1f]%n", (double) minuto);

        if (generador.nextDouble() < probabilidadLlegada) {
            Pedido nuevo = generarPedidoAleatorio(minuto);
            cola.agregar(nuevo);
            System.out.printf("Llega pedido: %s (%d min)%n",
                    nuevo.getTipo(), nuevo.getTiempoPreparacion());
        }

        System.out.printf("COLA: %d pedidos%n", cola.size());

        Pedido terminado = cocinero.finalizarSiTerminado();
        if (terminado != null) {
            pedidosAtendidos++;
        }

        if (cocinero.estaLibre() && !cola.estaVacia()) {
            Pedido siguiente = cola.extraerMinimo();
            int espera = minuto - siguiente.getTiempoLlegada();
            tiempoTotalEspera += espera;
            cocinero.asignarPedido(siguiente);
        }

        Pedido enProceso = cocinero.getPedidoEnProceso();
        if (enProceso != null) {
            System.out.printf("Cocinero: [%s - %d min restantes]%n",
                    enProceso.getTipo(), enProceso.getTiempoRestante());
            cocinero.avanzarMinuto();
        } else {
            System.out.println("Cocinero: [sin pedidos]");
        }
    }

    public Pedido generarPedidoAleatorio(int minutoActual) {
        String[] tipos = { "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada" };
        int[][] rangos = {
                {1, 2},
                {2, 3},
                {2, 4},
                {3, 5},
                {5, 8}
        };

        int indice = generador.nextInt(tipos.length);
        String tipo = tipos[indice];
        int min = rangos[indice][0];
        int max = rangos[indice][1];
        int tiempoPrep = min + generador.nextInt(max - min + 1);

        return new Pedido(tipo, tiempoPrep, minutoActual);
    }

    public void mostrarResumen() {
        int pendientes = cola.size();
        if (cocinero.getPedidoEnProceso() != null &&
                !cocinero.getPedidoEnProceso().estaTerminado()) {
            pendientes++;
        }

        double tiempoMedioEspera = (pedidosAtendidos > 0)
                ? (double) tiempoTotalEspera / pedidosAtendidos
                : 0.0;

        System.out.println();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.printf("Pedidos atendidos        : %d%n", pedidosAtendidos);
        System.out.printf("Pedidos pendientes       : %d%n", pendientes);
        System.out.printf("Tiempo total de espera   : %d minutos%n", tiempoTotalEspera);
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", tiempoMedioEspera);
        System.out.printf("Comparaciones totales    : %d%n", cola.getComparacionesTotales());
        System.out.println("========================================");
    }
}
