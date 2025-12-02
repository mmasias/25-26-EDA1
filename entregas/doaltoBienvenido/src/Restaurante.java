public class Restaurante {
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private ColaPedidos colaPedidos;
    private Estadisticas estadisticas;
    private Cocinero cocinero;

    public Restaurante() {
        colaPedidos = new ColaPedidos();
        estadisticas = new Estadisticas();
        cocinero = new Cocinero();
    }

    public void ejecutar(int minutos) {
        for (int tiempo = 1; tiempo <= minutos; tiempo++) {
            System.out.println("========================================");
            System.out.println("[Minuto " + tiempo + "]");

            if (Math.random() < PROBABILIDAD_LLEGADA) {
                String tipo = TipoPlato.muestrearTipo();
                int tiempoPreparacion = TipoPlato.generarTiempoParaTipo(tipo);
                Pedido nuevoPedido = new Pedido(tiempo, tipo, tiempoPreparacion, tiempo);
                colaPedidos.insertar(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido.getTipo() + " (" + nuevoPedido.getTiempoPreparacion() + " min)");
            }

            if (!cocinero.estaOcupado() && !colaPedidos.estaVacia()) {
                Pedido pedidoMin = colaPedidos.extraerMin();
                cocinero.asignarPedido(pedidoMin);
                estadisticas.registrarInicioServicio(pedidoMin, tiempo);
            }

            Pedido pedidoTerminado = cocinero.procesarUnMinuto();
            if (pedidoTerminado != null) {
                estadisticas.registrarPedidoAtendido(pedidoTerminado);
                System.out.println("Pedido completado: " + pedidoTerminado.getTipo());
            }

            System.out.println("COLA: " + colaPedidos.tamaño() + " pedidos");
            if (cocinero.estaOcupado()) {
                System.out.println("Cocinero: [" + cocinero.obtenerPedido().getTipo() + " - " + cocinero.obtenerPedido().getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: libre");
            }
        }

        estadisticas.registrarComparaciones(colaPedidos.tamaño());
        System.out.println("========================================");
        System.out.println(estadisticas.generarResumen());
    }
}
