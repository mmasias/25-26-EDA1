class Cocinero {
    private Pedido pedidoActual;
    
    public Cocinero() {
        this.pedidoActual = null;
    }
    
    public void tomarPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }
    
    public void procesar() {
        if (pedidoActual != null) {
            pedidoActual.decrementarTiempo();
        }
    }
    
    public boolean haTerminado() {
        return pedidoActual != null && pedidoActual.estaCompletado();
    }
    
    public void liberarPedido() {
        this.pedidoActual = null;
    }
    
    public boolean tienePedidoActual() {
        return pedidoActual != null;
    }
    
    public Pedido getPedidoActual() {
        return pedidoActual;
    }
}