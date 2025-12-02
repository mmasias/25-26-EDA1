
public class Cocinero {

    private Pedido pedidoEnPreparacion;
    private int totalPedidosFinalizados;
    private int sumaTiemposEsperaPedidosAtendidos;

    public Cocinero() {
        this.pedidoEnPreparacion = null;
        this.totalPedidosFinalizados = 0;
        this.sumaTiemposEsperaPedidosAtendidos = 0;
    }

    public void procesarMinuto(ArbolPedidos arbolPedidos, int instanteActual) {
        if (pedidoEnPreparacion == null) {
            if (!arbolPedidos.estaVacio()) {
                pedidoEnPreparacion = arbolPedidos.extraerPedidoConMenorDuracionTotal();
            }
        }
        if (pedidoEnPreparacion != null) {
            pedidoEnPreparacion.decrementarUnMinutoDeDuracionRestante();
            if (pedidoEnPreparacion.estaTerminado()) {
                totalPedidosFinalizados++;
                sumaTiemposEsperaPedidosAtendidos += pedidoEnPreparacion.tiempoEnEspera();
                pedidoEnPreparacion = null;
            }
        }
    }

    public boolean estaAtendiendoPedido() {
        return pedidoEnPreparacion != null;
    }

    public Pedido pedidoEnPreparacion() {
        return pedidoEnPreparacion;
    }

    public int pedidosFinalizados() {
        return totalPedidosFinalizados;
    }

    public int tiempoTotalDeEsperaAcumulado() {
        return sumaTiemposEsperaPedidosAtendidos;
    }
}
