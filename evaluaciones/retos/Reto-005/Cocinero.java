
public class Cocinero {
    private Pedido pedidoActual;

    public Cocinero() {
    }

    public Cocinero(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void asignarPedido(Pedido pedido, int minutoActual) {
        if (estaLibre() && pedido != null) {
            this.pedidoActual = pedido;
            this.pedidoActual.setMinutoInicio(minutoActual);
        }
    }

    public Pedido procesarMinuto() {
        if (pedidoActual == null) {
            return null;
        }

        pedidoActual.reducirUnMinuto();
        
        if (pedidoActual.getTiempoRestante() <= 0) {
            Pedido pedidoTerminado = pedidoActual;
            this.pedidoActual = null;
            return pedidoTerminado;
        }

        return null;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public void setPedidoActual(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }
}