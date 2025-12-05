
public class Cocinero {

    private Pedido pedidoActual;

    public Cocinero() {
        this.pedidoActual = null;
    }

    public void asignarPedido(Pedido p) {
        assert pedidoActual == null : "El cocinero ya está ocupado";
        assert p != null : "No se puede asignar un pedido nulo";
        this.pedidoActual = p;
    }

    public Pedido cocinar() {
        if (pedidoActual == null) {
            return null;
        }

        pedidoActual.decrementarMinuto();

        if (pedidoActual.estaTerminado()) {
            Pedido terminado = pedidoActual;
            pedidoActual = null;
            return terminado;
        }
        return null;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}
