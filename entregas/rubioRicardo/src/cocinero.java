
public class Cocinero {
    private Pedido pedidoActual;
    private int tiempoRestante;

    public Cocinero() {
        this.pedidoActual = null;
        this.tiempoRestante = 0;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

 
    public void asignarPedido(Pedido pedidoAsignado) {
        this.pedidoActual = pedidoAsignado;
        // ¡REFACTORIZADO! - Se usa el nombre de parámetro descriptivo
        this.tiempoRestante = pedidoAsignado.getTiempoPreparacion();
    }

  
    public Pedido procesarMinuto() {
        if (estaLibre()) {
            return null;
        }

        this.tiempoRestante--;

        if (this.tiempoRestante == 0) {
            Pedido pedidoTerminado = this.pedidoActual;
            this.pedidoActual = null; // Queda libre
            return pedidoTerminado;
        }

        return null;
    }

 
    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
}
