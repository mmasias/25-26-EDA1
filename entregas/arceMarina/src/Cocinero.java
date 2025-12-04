public class Cocinero {
    private Pedido pedidoActual;

    public Cocinero() {
        this.pedidoActual = null;
    }

    public boolean estaLibre() {
        return this.pedidoActual == null;
    }

    public void asignarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public Pedido trabajar() {
        if (this.pedidoActual != null) {
            if (this.pedidoActual.procesarMinuto()) {
                Pedido terminado = this.pedidoActual;
                this.pedidoActual = null;
                return terminado;
            }
        }
        return null;
    }

    public String getTextoEstado() {
        if (this.pedidoActual != null) {
            return "[" + this.pedidoActual.getNombrePlato() + " - " + this.pedidoActual.getTiempoRestante() + " min restantes]";
        }
        return "[Libre]";
    }
}