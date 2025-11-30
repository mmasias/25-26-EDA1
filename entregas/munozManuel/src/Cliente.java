package entregas.munozManuel.src;

class Cliente {
    private final String nombre;
    private final Pedido pedido;

    public Cliente(String nombre){
        this.nombre = nombre;
        pedido = new Pedido();
    }

    public String nombre(){
        return nombre;
    }

    public Pedido darPedido(){
        return pedido;
    }
}
