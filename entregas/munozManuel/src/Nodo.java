class Nodo {
    private Pedido pedido;
    private Nodo hijoIzquerda;
    private Nodo hijoDerecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
    }

    public String nombrePedido(){
        return pedido.nombre();
    }

    public int tiempoPreparacion(){
        return pedido.tiempoPreparacion();
    }

    public Nodo hijoIzquerda(){
        return hijoIzquerda;
    }

    public Nodo hijoDerecha(){
        return hijoDerecha;
    }

    public void hijoIzquerda(Nodo pedido){
        if(this.hijoIzquerda == null){
            this.hijoIzquerda = pedido;
        }else{
            this.hijoIzquerda.hijoIzquerda(pedido);
        }
    }

    public void hijoDerecha(Nodo pedido){
        if(this.hijoDerecha == null){
            this.hijoDerecha = pedido;
        }else{
            this.hijoDerecha.hijoDerecha(pedido);
        }
    }

    public void terminarPedido(){
        this.pedido = null;
    }

}