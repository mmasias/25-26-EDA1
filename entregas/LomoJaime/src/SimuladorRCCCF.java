public class SimuladorRCCCF {
    private static final int DURACION_JORNADA = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final String[] TIPOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS = {{1,2}, {2,3}, {2,4}, {3,5}, {5,8}};

    private ArbolPedidos cola;
    private Pedido pedidoEnProceso;
    private int tiempoRestante;
    private int pedidosAtendidos;
    private int tiempoTotalEsperaAtendidos;

    public SimuladorRCCCF() {
        this.cola = new ArbolPedidos();
        this.pedidoEnProceso = null;
        this.tiempoRestante = 0;
        this.pedidosAtendidos = 0;
        this.tiempoTotalEsperaAtendidos = 0;
    }

    public void ejecutar() {
        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            procesarLlegada(minuto);
            avanzarCoccion();
            mostrarEstado(minuto);
        }
        mostrarResumen();
    }

    private void procesarLlegada(int minuto) {
        if (Math.random() < PROBABILIDAD_LLEGADA) {
            int idx = (int) (Math.random() * TIPOS.length);
            String tipo = TIPOS[idx];
            int min = RANGOS[idx][0];
            int max = RANGOS[idx][1];
            int tiempo = min + (int) (Math.random() * (max - min + 1));
            Pedido nuevo = new Pedido(tipo, tiempo);
            System.out.println("Llega pedido: " + tipo + " (" + tiempo + " min)");

            if (pedidoEnProceso == null) {
                pedidoEnProceso = nuevo;
                tiempoRestante = tiempo;
            } else {
                cola.insertar(nuevo);
            }
        }
    }

    private void avanzarCoccion() {
        if (pedidoEnProceso != null) {
            tiempoRestante--;
            if (tiempoRestante == 0) {
                pedidosAtendidos++;
                tiempoTotalEsperaAtendidos += pedidoEnProceso.getTiempoEspera();
                if (!cola.estaVacio()) {
                    pedidoEnProceso = cola.extraerMinimo();
                    tiempoRestante = pedidoEnProceso.getTiempoPreparacion();
                } else {
                    pedidoEnProceso = null;
                }
            }
        }
        cola.incrementarEspera();
        if (pedidoEnProceso != null) {
            pedidoEnProceso.incrementarEspera();
        }
    }

    private void mostrarEstado(int minuto) {
        System.out.println("========================================");
        System.out.println("[" + minuto + ".0]");
        System.out.println("COLA: " + cola.tamaño() + " pedidos");
        if (pedidoEnProceso != null) {
            System.out.println("Cocinero: [" + pedidoEnProceso.getTipo() + " - " + tiempoRestante + " min restantes]");
        } else {
            System.out.println("Cocinero: [Libre]");
        }
    }

    private void mostrarResumen() {
        System.out.println("\n\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        int pendientes = cola.tamaño() + (pedidoEnProceso != null ? 1 : 0);
        System.out.println("Pedidos pendientes       : " + pendientes);
        int totalEspera = tiempoTotalEsperaAtendidos + cola.tiempoTotalEspera();
        if (pedidoEnProceso != null) {
            totalEspera += pedidoEnProceso.getTiempoEspera();
        }
        System.out.println("Tiempo total de espera   : " + totalEspera + " minutos");
        double media = pendientes + pedidosAtendidos > 0 ? (double) totalEspera / (pendientes + pedidosAtendidos) : 0;
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", media);
        System.out.println("Comparaciones totales    : " + cola.getComparaciones());
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        new SimuladorRCCCF().ejecutar();
    }
}
    
