package entregas.caicedoFernando.src;

public class Cocinero {
    private Pedido pedidoActual;

    public Cocinero() {
        this.pedidoActual = null;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void tomarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public Pedido trabajar() {
        if (pedidoActual == null) {
            return null;
        }

        pedidoActual.tiempoRestante--;

        if (pedidoActual.tiempoRestante <= 0) {
            Pedido pedidoTerminado = pedidoActual;
            pedidoActual = null;
            return pedidoTerminado;
        }
        
        return null;
    }

    public Pedido obtenerPedidoActual() {
        return pedidoActual;
    }
}