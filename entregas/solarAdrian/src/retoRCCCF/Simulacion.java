package retoRCCCF;

import java.util.PriorityQueue;
import java.util.Random;

public class Simulacion {

    private PriorityQueue<Pedido> cola;
    private Pedido pedidoEnCurso;
    private int tiempoActual;
    private Random random;

    // Estadísticas
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;

    // Configuración
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final int DURACION_JORNADA = 120;

    public Simulacion() {
        this.cola = new PriorityQueue<>();
        this.pedidoEnCurso = null;
        this.tiempoActual = 0;
        this.random = new Random();
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
    }

    public void run() {
        System.out.println("========================================");

        for (tiempoActual = 1; tiempoActual <= DURACION_JORNADA; tiempoActual++) {
            System.out.println("[" + (double) tiempoActual + "]");

            // 1. Llegada de nuevo pedido
            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                Pedido nuevoPedido = generarPedidoAleatorio();
                cola.add(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido);
            }

            // 2. Lógica de la cocina
            if (pedidoEnCurso == null) {
                if (!cola.isEmpty()) {
                    pedidoEnCurso = cola.poll();
                    // Cálculo del tiempo de espera: tiempo actual - tiempo de llegada
                    tiempoEsperaTotal += (tiempoActual - pedidoEnCurso.getMinutoLlegada());
                }
            }

            // 3. Procesar pedido en curso
            if (pedidoEnCurso != null) {
                pedidoEnCurso.decrementarTiempo();

                if (pedidoEnCurso.estaTerminado()) {
                    pedidosAtendidos++;
                    pedidoEnCurso = null;
                    // Intentar tomar el siguiente inmediatamente si está disponible
                    if (!cola.isEmpty()) {
                        pedidoEnCurso = cola.poll();
                        tiempoEsperaTotal += (tiempoActual - pedidoEnCurso.getMinutoLlegada());
                    }
                }
            }

            // 4. Imprimir estado
            System.out.println("COLA: " + cola.size() + " pedidos");
            if (pedidoEnCurso != null) {
                System.out.println("Cocinero: " + pedidoEnCurso.estadoString());
            } else {
                System.out.println("Cocinero: [Esperando]");
            }
            System.out.println("========================================");
        }

        printSummary();
    }

    private Pedido generarPedidoAleatorio() {
        int tipo = random.nextInt(5);
        String nombre = "";
        int duracion = 0;

        switch (tipo) {
            case 0: // Bebida: 1-2
                nombre = "Bebida";
                duracion = 1 + random.nextInt(2);
                break;
            case 1: // Café: 2-3
                nombre = "Café";
                duracion = 2 + random.nextInt(2);
                break;
            case 2: // Colacao: 2-4
                nombre = "Colacao";
                duracion = 2 + random.nextInt(3);
                break;
            case 3: // Bocadillo: 3-5
                nombre = "Bocadillo";
                duracion = 3 + random.nextInt(3);
                break;
            case 4: // Ensalada: 5-8
                nombre = "Ensalada";
                duracion = 5 + random.nextInt(4);
                break;
        }
        return new Pedido(nombre, duracion, tiempoActual);
    }

    private void printSummary() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cola.size());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");

        // Calcular tiempo de espera de los pendientes
        int tiempoEsperaPendientes = 0;
        for (Pedido p : cola) {
            tiempoEsperaPendientes += (DURACION_JORNADA - p.getMinutoLlegada());
        }
        int tiempoEsperaTotalReal = tiempoEsperaTotal + tiempoEsperaPendientes;

        int totalPedidos = pedidosAtendidos + cola.size();
        double promedio = totalPedidos > 0 ? (double) tiempoEsperaTotalReal / totalPedidos : 0;

        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", promedio);
        System.out.println("Comparaciones totales    : " + Pedido.comparaciones);
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        new Simulacion().run();
    }
}
