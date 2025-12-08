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
        if(ocupado()){
            int tiempoRestante = pedidoActual.tiempoPreparacion() - tiempoDeTrabajoEnPedido;
            System.out.println("Se esta haciendo el pedido " + pedidoActual.nombreDelPedido() + " le falta " + tiempoRestante + " minuto");
        }
    }
    
    private void actualizarEstadoDelPedido(){
        if(pedidoActual.tiempoPreparacion() == tiempoDeTrabajoEnPedido){
            System.out.println("Se termino el pedido " + pedidoActual.nombreDelPedido());
            terminarPedido();
        }
    }

    public void trabajarEnPedido(){
        if(ocupado()){
            tiempoDeTrabajoEnPedido += 1;
        }
    }

    public void tomarPedido(Nodo pedido){
        pedidoActual = pedido.pedido();
    }

    public boolean ocupado(){
        return pedidoActual != null;
    }

    private void terminarPedido(){
        pedidoActual.terminarPedido();
        pedidoActual = null;
        tiempoDeTrabajoEnPedido = 0;
    }

}
