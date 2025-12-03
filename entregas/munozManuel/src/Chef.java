package entregas.munozManuel.src;

class Chef {
    private Pedido pedidoActual;
    private int tiempoDeTrabajoEnPedido;

    public Chef(){
        tiempoDeTrabajoEnPedido = 0;
    }

    public void processarPedido(){
        trabajarEnPedido();
        actualizarEstadoDelPedido();
    }
    
    public void tomarPedido(Pedido pedido){
        pedidoActual = pedido;
    }

    private void actualizarEstadoDelPedido(){
        if(pedidoActual.tiempoPreparacion() == tiempoDeTrabajoEnPedido){
            System.out.println("Se termino el pedido " + pedidoActual.nombreDelPedido());
            pedidoActual.terminarPedido();
            terminarPedido();
        }
    }

    public boolean ocupado(){
        return pedidoActual != null;
    }

    public void trabajarEnPedido(){
        if(ocupado()){
            tiempoDeTrabajoEnPedido += 1;
        }
    }

    private void terminarPedido(){
        pedidoActual = null;
        tiempoDeTrabajoEnPedido = 0;
    }

}
