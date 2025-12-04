public class Restaurante {
    private static final int JORNADA_TOTAL = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final String[] TIPOS_PLATO = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] TIEMPOS_PLATO = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

    private ArbolPedido colaPedidos;
    private Cocinero cocinero;
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;
    private int tiempoActual;

    public Restaurante() {
        this.colaPedidos = new ArbolPedido();
        this.cocinero = new Cocinero();
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
        this.tiempoActual = 0;
    }

    public void run() {
        for (tiempoActual = 1; tiempoActual <= JORNADA_TOTAL; tiempoActual++) {
            System.out.println("========================================");
            System.out.println("[" + tiempoActual + ".0]");

            if (Math.random() < PROBABILIDAD_LLEGADA) {
                Pedido nuevoPedido = generarPedido();
                colaPedidos.add(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido.tipo + " (" + nuevoPedido.tiempoPreparacion + " min)");
            }

            if (cocinero.estaOcupado()) {
                cocinero.procesarMinuto();
                if (!cocinero.estaOcupado()) {
                    pedidosAtendidos++;
                }
            }

            if (!cocinero.estaOcupado() && !colaPedidos.isEmpty()) {
                Pedido siguientePedido = colaPedidos.poll();
                cocinero.tomarPedido(siguientePedido);
                tiempoEsperaTotal += (tiempoActual - siguientePedido.tiempoLlegada);
            }

            System.out.println("COLA: " + colaPedidos.size() + " pedidos");
            if (cocinero.estaOcupado()) {
                Pedido actual = cocinero.getPedidoActual();
                System.out.println("Cocinero: [" + actual.tipo + " - " + actual.tiempoRestante + " min restantes]");
            } else {
                System.out.println("Cocinero: Libre");
            }
        }
        
        mostrarResumen();
    }

    private Pedido generarPedido() {
        int tipoIndex = (int) (Math.random() * TIPOS_PLATO.length);
        String tipo = TIPOS_PLATO[tipoIndex];
        int minTiempo = TIEMPOS_PLATO[tipoIndex][0];
        int maxTiempo = TIEMPOS_PLATO[tipoIndex][1];
        int tiempoPreparacion = minTiempo + (int) (Math.random() * ((maxTiempo - minTiempo) + 1));
        return new Pedido(tipo, tiempoPreparacion, tiempoActual);
    }

    private void mostrarResumen() {
        System.out.println("========================================");
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + colaPedidos.size());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        
        double tiempoMedio = 0;
        if (pedidosAtendidos > 0) {
            tiempoMedio = (double) tiempoEsperaTotal / pedidosAtendidos;
        }
        
        long parteEntera = (long) tiempoMedio;
        long parteDecimal = Math.round((tiempoMedio - parteEntera) * 10);
        String tiempoMedioStr = parteEntera + "." + parteDecimal;

        System.out.println("Tiempo medio de espera   : " + tiempoMedioStr + " minutos");
        System.out.println("Comparaciones totales    : " + colaPedidos.getComparaciones());
        System.out.println("========================================");
    }
}
