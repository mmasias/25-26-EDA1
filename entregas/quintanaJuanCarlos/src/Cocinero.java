public class Cocinero {

    private Pedido pedidoEnPreparacion;
    private int minutosRestantesPedidoActual;
    private int pedidosTerminados;
    private int sumaTiemposEsperaPedidos;

    public Cocinero() {
        this.pedidoEnPreparacion = null;
        this.minutosRestantesPedidoActual = 0;
        this.pedidosTerminados = 0;
        this.sumaTiemposEsperaPedidos = 0;
    }

    public void procesarMinuto(ArbolPedidos arbolPedidos) {
        if (pedidoEnPreparacion == null) {
            if (!arbolPedidos.estaVacio()) {
                pedidoEnPreparacion = arbolPedidos.extraerPedidoConMenorDuracionTotal();
                minutosRestantesPedidoActual = pedidoEnPreparacion.duracionTotal();
            }
        }
        if (pedidoEnPreparacion != null) {
            if (minutosRestantesPedidoActual > 0) {
                minutosRestantesPedidoActual--;
            }
            if (minutosRestantesPedidoActual <= 0) {
                pedidosTerminados++;
                sumaTiemposEsperaPedidos += pedidoEnPreparacion.tiempoEnEspera();
                pedidoEnPreparacion = null;
                minutosRestantesPedidoActual = 0;
            }
        }
    }

    public boolean estaOcupado() {
        return pedidoEnPreparacion != null;
    }

    public Pedido pedidoActual() {
        return pedidoEnPreparacion;
    }

    public int minutosRestantesPedidoActual() {
        return minutosRestantesPedidoActual;
    }

    public int pedidosTerminados() {
        return pedidosTerminados;
    }

    public int tiempoTotalEsperaAcumulada() {
        return sumaTiemposEsperaPedidos;
    }
}
