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
            return String.format("[%s - %d min rest]", 
                this.pedidoActual.getNombrePlato(), 
                this.pedidoActual.getTiempoRestante());
        }
        return "[Libre]";
    }
}
