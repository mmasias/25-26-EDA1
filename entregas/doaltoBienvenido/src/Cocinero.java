public class Cocinero {
    private Pedido pedidoActual;

    public Cocinero() {
        this.pedidoActual = null;
    }

    public boolean estaOcupado() { 
        return pedidoActual != null; 
    }

    public void asignarPedido(Pedido pedido) { 
        pedidoActual = pedido; 
    }

    public Pedido obtenerPedido() { 
        return pedidoActual; 
    }

    public Pedido procesarUnMinuto() {
        if (pedidoActual != null) {
            pedidoActual.decrementarTiempoRestante();
            if (pedidoActual.estaCompleto()) {
                Pedido pedidoTerminado = pedidoActual;
                pedidoActual = null;
                return pedidoTerminado;
            }
        }
        return null;
    }
}
