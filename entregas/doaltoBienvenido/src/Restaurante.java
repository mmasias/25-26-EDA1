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
        for (int t = 1; t <= minutos; t++) {
            if (Math.random() < PROBABILIDAD_LLEGADA) {
                String tipo = TipoPlato.muestrearTipo();
                int tiempoPrep = TipoPlato.generarTiempoParaTipo(tipo);
                Pedido nuevoPedido = new Pedido(t, tipo, tiempoPrep, t);
                colaPedidos.insertar(nuevoPedido);
            }

            if (!cocinero.estaOcupado() && !colaPedidos.estaVacia()) {
                Pedido pedidoMin = colaPedidos.extraerMin();
                cocinero.asignarPedido(pedidoMin);
                estadisticas.registrarInicioServicio(pedidoMin, t);
            }

            cocinero.procesarUnMinuto();
        }

        estadisticas.registrarComparaciones(colaPedidos.tamaño());
        System.out.println(estadisticas.generarResumen());
    }
}
