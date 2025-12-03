package entregas.munozManuel.src;

class Chef {
    private Pedido pedidoActual;
    private int tiempoDeTrabajoEnPedido;

    public Chef(){
        tiempoDeTrabajoEnPedido = 0;
    }

    public void processarPedido(){
        actualizarEstadoDelPedido();
    }
    
    public void mostrarPedidoActual(){
        int tiempoRestante = pedidoActual.tiempoPreparacion() - tiempoDeTrabajoEnPedido;
        System.out.println("Se esta haciendo el pedido " + pedidoActual.nombreDelPedido() + " le falta " + tiempoRestante);
    }
    
    private void actualizarEstadoDelPedido(){
        if(pedidoActual.tiempoPreparacion() == tiempoDeTrabajoEnPedido){
            System.out.println("Se termino el pedido " + pedidoActual.nombreDelPedido());
            pedidoActual.terminarPedido();
            terminarPedido();
        }
    }

    public void trabajarEnPedido(){
        if(ocupado()){
            tiempoDeTrabajoEnPedido += 1;
        }
    }

    public void tomarPedido(Pedido pedido){
        pedidoActual = pedido;
    }

    public boolean ocupado(){
        return pedidoActual != null;
    }

    private void terminarPedido(){
        pedidoActual = null;
        tiempoDeTrabajoEnPedido = 0;
    }

}
