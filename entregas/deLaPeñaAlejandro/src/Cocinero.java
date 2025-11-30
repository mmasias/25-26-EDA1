public class Cocinero {

    private Pedido pedidoActual;

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void asignar(Pedido pedido) {
        assert estaLibre() : "El cocinero ya está ocupado, no puede recibir otro pedido.";
        this.pedidoActual = pedido;
    }

    public void trabajar() {
        if (pedidoActual != null) {
            pedidoActual.procesarMinuto();
            
            if (pedidoActual.estaTerminado()) {
                pedidoActual = null;
            }
        }
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}