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
            avanzarUnMinuto(tiempo);
        }

        finalizar();
    }

    public void avanzarUnMinuto(int tiempo) {
        if (Math.random() < PROBABILIDAD_LLEGADA) {
            String tipo = TipoPlato.muestrearTipo();
            int tiempoPreparacion = TipoPlato.generarTiempoParaTipo(tipo);
            Pedido nuevoPedido = new Pedido(tiempo, tipo, tiempoPreparacion, tiempo);
            colaPedidos.insertar(nuevoPedido);
            System.out.println("Nuevo pedido: " + nuevoPedido);
        }

        if (!cocinero.estaOcupado() && !colaPedidos.estaVacia()) {
            Pedido pedidoMin = colaPedidos.extraerMin();
            cocinero.asignarPedido(pedidoMin);
            estadisticas.registrarInicioServicio(pedidoMin, tiempo);
            System.out.println("El cocinero comienza a preparar: " + pedidoMin);
        }

        cocinero.procesarUnMinuto();
    }

    public void finalizar() {
        estadisticas.registrarComparaciones(colaPedidos.tamaño());
        System.out.println(estadisticas.generarResumen());
    }
}
