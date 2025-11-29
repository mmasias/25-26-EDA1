public class Restaurante {
    private ColaPedidos colaPedidos;
    private Cocinero cocinero;
    private Estadisticas estadisticas;
    private double semilla;
    private static final double PROBABILIDAD_LLEGADA = 0.4;

    public Restaurante(double semilla) {
        colaPedidos = new ColaPedidos();
        cocinero = new Cocinero();
        estadisticas = new Estadisticas();
        this.semilla = semilla;
    }

    public void ejecutar(int minutos) {
        int instante = 0;

        while (instante < minutos) {
            if (Math.random() < PROBABILIDAD_LLEGADA) {
                String tipo = TipoPlato.muestrearTipo(semilla + instante);
                int tiempo = TipoPlato.generarTiempoParaTipo(tipo, semilla + instante);
                Pedido pedido = new Pedido(instante, tipo, tiempo, instante);
                colaPedidos.insertar(pedido);
            }

            if (cocinero.estaLibre() && !colaPedidos.estaVacia()) {
                Pedido siguiente = colaPedidos.extraerMin();
                cocinero.asignarPedido(siguiente, instante);
                estadisticas.registrarInicioServicio(siguiente, instante);
            }

            Pedido terminado = cocinero.avanzarTiempo();
            if (terminado != null) {
                estadisticas.registrarPedidoAtendido(terminado);
            }

            instante++;
        }
    }
}