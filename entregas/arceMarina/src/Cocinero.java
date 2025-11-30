class Cocinero {
    private Pedido pedidoActual;

    Cocinero() {
        this.pedidoActual = null;
    }

    boolean estaLibre() {
        return this.pedidoActual == null;
    }

    void asignarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    Pedido dejarPedido() {
        Pedido p = this.pedidoActual;
        this.pedidoActual = null;
        return p;
    }

    Pedido getPedidoActual() {
        return this.pedidoActual;
    }

    Pedido trabajar() {
        if (this.pedidoActual != null) {
            boolean terminado = this.pedidoActual.procesarMinuto();
            
            if (terminado) {
                Pedido finalizado = this.pedidoActual;
                this.pedidoActual = null;
                return finalizado;
            }
        }
        return null;
    }

    String obtenerEstado() {
        if (this.pedidoActual != null) {
            return "[" + this.pedidoActual.getNombrePlato() + " - " + this.pedidoActual.getTiempoRestante() + " min rest]";
        }
        return "[Libre]";
    }
}
