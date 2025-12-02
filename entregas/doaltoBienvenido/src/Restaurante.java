public class Restaurante {
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final int MINUTO_INICIAL = 1;
    private ArbolPedidos arbolPedidos;
    private Estadisticas estadisticas;
    private Cocinero cocinero;

    public Restaurante() {
        arbolPedidos = new ArbolPedidos();
        estadisticas = new Estadisticas();
        cocinero = new Cocinero();
    }

    public void ejecutar(int minutos) {
        assert minutos > 0 : "minutos debe ser mayor que 0";
        for (int tiempo = MINUTO_INICIAL; tiempo <= minutos; tiempo++) {
            System.out.println("========================================");
            System.out.println("[Minuto " + tiempo + "]");

            if (Math.random() < PROBABILIDAD_LLEGADA) {
                String tipo = TipoPlato.muestrearTipo();
                int tiempoPreparacion = TipoPlato.generarTiempoParaTipo(tipo);
                Pedido nuevoPedido = new Pedido(tiempo, tipo, tiempoPreparacion);
                arbolPedidos.insertar(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido.getTipo() + " (" + nuevoPedido.getTiempoPreparacion() + " min)");
            }

            if (!cocinero.estaOcupado() && !arbolPedidos.estaVacia()) {
                Pedido pedidoMin = arbolPedidos.extraerMin();
                cocinero.asignarPedido(pedidoMin, tiempo);
                estadisticas.registrarInicioServicio(pedidoMin, tiempo);
            }

            Pedido pedidoTerminado = cocinero.procesarUnMinuto();
            if (pedidoTerminado != null) {
                estadisticas.registrarPedidoAtendido(pedidoTerminado);
                System.out.println("Pedido completado: " + pedidoTerminado.getTipo());
            }

            System.out.println("COLA: " + arbolPedidos.tamaño() + " pedidos");
            if (cocinero.estaOcupado()) {
                System.out.println("Cocinero: [" + cocinero.obtenerPedido().getTipo() + " - " + cocinero.getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: libre");
            }
        }

        estadisticas.registrarComparaciones(arbolPedidos.tamaño());
        System.out.println("========================================");
        System.out.println(estadisticas.generarResumen());
    }
}
