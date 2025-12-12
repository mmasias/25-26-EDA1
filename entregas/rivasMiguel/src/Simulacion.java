public class Simulacion {

    private static final double PROBABILIDAD_LLEGADA = 0.40;

    private final int duracion;
    private final Cocinero cocinero;
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;

    public Simulacion(int duracionMinutos) {
        this.duracion = duracionMinutos;
        this.cocinero = new Cocinero();
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
    }

    public void iniciar() {
        for (int minuto = 1; minuto <= duracion; minuto++) {
            simularMinuto(minuto);
        }
        mostrarResumen();
    }

    private void simularMinuto(int minuto) {
        gestionarLlegadaPedidos(minuto);

        registrarEstadisticas(minuto);

        cocinero.procesarMinuto();

        imprimirEstado(minuto);
    }

    private void gestionarLlegadaPedidos(int minuto) {
        if (Main.rnd.nextDouble() < PROBABILIDAD_LLEGADA) {
            
            Pedido.Tipo[] tiposDisponibles = Pedido.Tipo.values();
            int indiceAleatorio = Main.rnd.nextInt(tiposDisponibles.length);
            Pedido.Tipo tipoSeleccionado = tiposDisponibles[indiceAleatorio];

            Pedido pedido = new Pedido(tipoSeleccionado);
            pedido.setTiempoLlegada(minuto);
            
            cocinero.agregarPedido(pedido);

            System.out.println("[Min " + minuto + "] Llega pedido: " + 
                               pedido.getTipo() + " (" + pedido.getTiempoPreparacion() + " min)");
        }
    }

    private void registrarEstadisticas(int minuto) {
        Pedido actual = cocinero.getPedidoActual();
        
        if (actual != null) {
            if (actual.getTiempoRestante() == 1) {
                pedidosAtendidos++;
                tiempoEsperaTotal += (minuto - actual.getTiempoLlegada());
            }
        }
    }

    private void imprimirEstado(int minuto) {
        System.out.println("COLA: " + cocinero.getPedidosPendientes() + " pedidos");
        
        Pedido p = cocinero.getPedidoActual();
        if (p == null) {
            System.out.println("Cocinero: [libre]");
        } else {
            System.out.println("Cocinero: [" + p + "]");
        }
        
        System.out.println("------------------------------------");
    }

    private void mostrarResumen() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("=====================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cocinero.getPedidosPendientes());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal);
        System.out.println("Tiempo medio de espera   : " + getTiempoMedioEspera());
        System.out.println("Comparaciones totales    : " + cocinero.getComparacionesTotales());
        System.out.println("=====================================");
    }

    public int getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public int getPedidosPendientes() {
        return cocinero.getPedidosPendientes();
    }

    public int getTiempoTotalEspera() {
        return tiempoEsperaTotal;
    }

    public double getTiempoMedioEspera() {
        if (pedidosAtendidos == 0) {
            return 0.0;
        }
        return (double) tiempoEsperaTotal / pedidosAtendidos;
    }

    public int getComparacionesTotales() {
        return cocinero.getComparacionesTotales();
    }
}