public class Cocina {
    private final IEstructuraPedidos estructuraPedidos;
    private Pedido pedidoActual;

    public Cocina(IEstructuraPedidos estructuraPedidos) {
        this.estructuraPedidos = estructuraPedidos;
        this.pedidoActual = null;
    }

    public void recibirPedido(Pedido p, Estadisticas estadisticas) {
        estructuraPedidos.insertar(p);
        estadisticas.sumarComparaciones(estructuraPedidos.getComparaciones());
        estructuraPedidos.resetComparaciones();
    }

    public void seleccionarSiguientePedido(int tiempoActual, Estadisticas estadisticas) {
        if (pedidoActual == null && !estructuraPedidos.estaVacia()) {
            Pedido siguiente = estructuraPedidos.extraerMinimo();
            estadisticas.sumarComparaciones(estructuraPedidos.getComparaciones());
            estructuraPedidos.resetComparaciones();

            if (siguiente != null) {
                siguiente.setInstanteInicio(tiempoActual);
                pedidoActual = siguiente;
            }
        }
    }

    public void procesarMinuto(int tiempoActual, Estadisticas estadisticas) {
        if (pedidoActual != null) {
            pedidoActual.decrementarTiempo();

            if (pedidoActual.estaTerminado()) {
                estadisticas.registrarAtencion(pedidoActual);
                pedidoActual = null;
            }
        }
    }
}



