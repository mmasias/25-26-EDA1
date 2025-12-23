package entregas.caicedoFernando.src;

public class Cocinero {
    private Pedido pedidoActual;

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void tomarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public Pedido obtenerPedidoActual() {
        return pedidoActual;
    }

    public Pedido trabajar() {
        if (pedidoActual == null) return null;

        pedidoActual.tiempoRestante--;

        if (pedidoActual.tiempoRestante <= 0) {
            Pedido terminado = pedidoActual;
            pedidoActual = null;
            return terminado;
        }
        return null;
    }
}