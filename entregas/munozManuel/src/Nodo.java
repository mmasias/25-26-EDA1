package entregas.munozManuel.src;

class Nodo {
    private final Pedido pedido;
    private Nodo hijoIzquerda;
    private Nodo hijoDerecha;

    public Nodo(Pedido pedido) {
        this.pedido = pedido;
    }

    public String nombrePedido(){
        return pedido.pedido();
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
    
    public void insertarHijo(Nodo pedido){
        if(this.tiempoPreparacion() >= pedido.tiempoPreparacion()){
            this.hijoIzquerda(pedido);
        } else {
            this.hijoDerecha(pedido);
        }
    } 

    public void restarTiempoDePreparacion(){
        pedido.restarTiempo();
    }

    private void hijoIzquerda(Nodo pedido){
        if(this.hijoIzquerda == null){
            this.hijoIzquerda = pedido;
        }else{
            this.hijoIzquerda.insertarHijo(pedido);
        }
    }
    
    private void hijoDerecha(Nodo pedido){
        if(this.hijoDerecha == null){
            this.hijoDerecha = pedido;
        }else{
            this.hijoDerecha.insertarHijo(pedido);
        }
    }

}