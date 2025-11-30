public class Cocinero {

    private Pedido pedidoActual;

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void asignar(Pedido pedido) {
        // Aseguramos que no reciba trabajo si ya está ocupado
        assert estaLibre() : "El cocinero ya está ocupado, no puede recibir otro pedido.";
        this.pedidoActual = pedido;
    }

    public void trabajar() {
        if (pedidoActual != null) {
            pedidoActual.procesarMinuto();
            
            // Si el pedido llega a 0, el cocinero queda libre
            if (pedidoActual.estaTerminado()) {
                pedidoActual = null;
            }
        }
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}