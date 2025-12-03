package entregas.munozManuel.src;

class Chef {
    private Nodo pedidoActual;

    public Chef(){
    }

    public void processarPedido(){
        actualizarEstadoDelPedido();
        if(pedidoActual.tiempoPreparacion() == 0){
            terminarPedido();
        }
    }
    
    public void tomarPedido(Nodo pedido){
        pedidoActual = pedido;
    }

    public boolean ocupado(){
        return pedidoActual != null;
    }

    private void terminarPedido(){
        pedidoActual = null;
    }

    public void actualizarEstadoDelPedido(){
        if(pedidoActual.tiempoPreparacion() == 1){
            System.out.println("Se termino el pedido " + pedidoActual.nombrePedido());
        }
        pedidoActual.restarTiempoDePreparacion();
    }
}
