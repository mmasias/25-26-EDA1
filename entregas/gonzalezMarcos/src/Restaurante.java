public class Restaurante {
    private final int DURACION_JORNADA = 8 * 60;
    private final double PROBABILIDAD_LLEGADA = 0.4;

    private ArbolPedidos cola;
    private Pedido pedidoActual;
    private int tiempoRestanteCocina;
    private int minutoActual;

    private int pedidosAtendidos;
    private int tiempoTotalEsperaAtendidos;

    private final String[] TIPOS = { "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada" };
    private final int[][] RANGOS = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 5, 8 } };

    public Restaurante() {
        this.cola = new ArbolPedidos();
        this.pedidoActual = null;
        this.tiempoRestanteCocina = 0;
        this.pedidosAtendidos = 0;
        this.tiempoTotalEsperaAtendidos = 0;
    }

    public void run() {
        System.out.println("Iniciando simulación del RCCCF...");

        for (minutoActual = 1; minutoActual <= DURACION_JORNADA; minutoActual++) {
            validarLlegada();
            procesarCocina();

            cola.incrementarEspera();

            if (minutoActual % 60 == 0) {
                mostrarEstado();
            }
        }
        mostrarResumen();
    }

    private void validarLlegada() {
        if (Math.random() < PROBABILIDAD_LLEGADA) {
            int index = (int) (Math.random() * TIPOS.length);
            int tiempo = RANGOS[index][0] + (int) (Math.random() * (RANGOS[index][1] - RANGOS[index][0] + 1));

            Pedido nuevoPedido = new Pedido(TIPOS[index], tiempo);
            System.out.println("[Min " + minutoActual + "] LLEGA: " + nuevoPedido);

            cola.insertar(nuevoPedido);
        }
    }

    private void procesarCocina() {
        if (pedidoActual == null && !cola.isEmpty()) {
            pedidoActual = cola.extraerMinimo();
            tiempoRestanteCocina = pedidoActual.getTiempoPreparacion();
            System.out.println("   >>> Cocinero empieza: " + pedidoActual.getTipo() + " (Tiempo prep: "
                    + tiempoRestanteCocina + ")");
        }

        if (pedidoActual != null) {
            tiempoRestanteCocina--;
            pedidoActual.incrementarEspera();

            if (tiempoRestanteCocina == 0) {
                pedidosAtendidos++;
                tiempoTotalEsperaAtendidos += pedidoActual.getTiempoEspera();
                System.out.println("   *** TERMINADO: " + pedidoActual.getTipo());

                if (!cola.isEmpty()) {
                    pedidoActual = cola.extraerMinimo();
                    tiempoRestanteCocina = pedidoActual.getTiempoPreparacion();
                    System.out.println("   >>> Cocinero empalma con: " + pedidoActual.getTipo());
                } else {
                    pedidoActual = null;
                }
            }
        }
    }

    private void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("ESTADO MINUTO " + minutoActual);
        System.out.println("Pedidos en cola: " + cola.tamaño());
        if (pedidoActual != null) {
            System.out.println("Cocinando: " + pedidoActual.getTipo() + " (Faltan " + tiempoRestanteCocina + " min)");
        } else {
            System.out.println("Cocinero: Libre");
        }
        System.out.println("--- Árbol de Prioridades ---");
        cola.printTree();
        System.out.println("========================================");
    }

    private void mostrarResumen() {
        System.out.println("\n\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);

        int pendientes = cola.tamaño() + (pedidoActual != null ? 1 : 0);
        System.out.println("Pedidos pendientes       : " + pendientes);

        int esperaTotal = tiempoTotalEsperaAtendidos + cola.tiempoTotalEspera();
        if (pedidoActual != null)
            esperaTotal += pedidoActual.getTiempoEspera();

        int totalPedidosGestionados = pedidosAtendidos + pendientes;
        double media = totalPedidosGestionados > 0 ? (double) esperaTotal / totalPedidosGestionados : 0;

        System.out.println("Tiempo total espera      : " + esperaTotal + " min");
        System.out.println("Tiempo medio espera      : " + String.format("%.2f", media) + " min");
        System.out.println("Comparaciones (Algoritmo): " + cola.getComparaciones());
        System.out.println("========================================");
    }
}