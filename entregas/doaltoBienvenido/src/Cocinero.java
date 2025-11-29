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

    public void procesarUnMinuto() {
        if (pedidoActual != null) {
            pedidoActual.decrementarTiempoRestante();
            if (pedidoActual.estaCompleto()) {
                pedidoActual = null;
            }
        }
    }
}
