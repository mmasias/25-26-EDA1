public class Cocinero {
    private static final int INSTANTE_NO_INICIADO = -1;
    private static final int TIEMPO_RESTANTE_INICIAL = 0;
    private static final int UMBRAL_PEDIDO_COMPLETADO = 0;

    private Pedido pedidoActual;
    private int tiempoRestanteActual;
    private int instanteInicioActual;

    public Cocinero() {
        this.pedidoActual = null;
        this.tiempoRestanteActual = TIEMPO_RESTANTE_INICIAL;
        this.instanteInicioActual = INSTANTE_NO_INICIADO;
    }

    public boolean estaOcupado() {
        return pedidoActual != null;
    }

    public void asignarPedido(Pedido pedido, int instanteInicio) {
        assert pedido != null : "pedido no puede ser null";
        assert instanteInicio >= 0 : "instanteInicio no puede ser negativo";
        assert pedido.getTiempoPreparacion() >= 0 : "tiempoPreparacion no puede ser negativo";
        this.pedidoActual = pedido;
        this.tiempoRestanteActual = pedido.getTiempoPreparacion();
        this.instanteInicioActual = instanteInicio;
    }

    public Pedido obtenerPedido() {
        return pedidoActual;
    }

    public int getTiempoRestante() {
        return tiempoRestanteActual;
    }

    public Pedido procesarUnMinuto() {
        if (pedidoActual != null) {
            tiempoRestanteActual--;
            if (tiempoRestanteActual <= UMBRAL_PEDIDO_COMPLETADO) {
                Pedido pedidoTerminado = pedidoActual;
                pedidoActual = null;
                tiempoRestanteActual = TIEMPO_RESTANTE_INICIAL;
                instanteInicioActual = INSTANTE_NO_INICIADO;
                return pedidoTerminado;
            }
        }
        return null;
    }
}
