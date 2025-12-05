package vaqueroInigo.reto005_refactorizacionMinimalista;

public class Cocinero {
    private Pedido pedidoEnProceso;

    public void asignarPedido(Pedido p) {
        this.pedidoEnProceso = p;
    }

    public void avanzarMinuto() {
        if (pedidoEnProceso != null) {
            pedidoEnProceso.trabajarUnMinuto();
        }
    }

    public boolean estaLibre() {
        return pedidoEnProceso == null || pedidoEnProceso.estaTerminado();
    }

    public Pedido getPedidoEnProceso() {
        return pedidoEnProceso;
    }

    public Pedido finalizarSiTerminado() {
        if (pedidoEnProceso != null && pedidoEnProceso.estaTerminado()) {
            Pedido terminado = pedidoEnProceso;
            pedidoEnProceso = null;
            return terminado;
        }
        return null;
    }
}
