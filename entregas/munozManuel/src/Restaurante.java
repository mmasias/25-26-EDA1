package entregas.munozManuel.src;

class Restaurante {
    private final double HORA_APERTURA = 9.0;
    private final double HORA_CIERRE = 21.0;
    private final String nombreRestaurante;
    private final Chef chef;
    private final Arbol ordenes;

    public Restaurante(String nombre, Chef chef){
        this.chef = chef;
        nombreRestaurante = nombre;
        ordenes = new Arbol();
    }

    public String nombreRestaurante(){
        return nombreRestaurante;
    }

    public int cantidadOrdenes(){
        return ordenes.cantidadNodos();
    }
    
    public void tomarPedido(Cliente cliente){
        System.out.println("Cliente en fila");
        if(!chef.ocupado()){
            Nodo pedidoDeCliente = ordenes.buscarNodoMinimo();
            chef.tomarPedido(pedidoDeCliente);
            System.out.println("Haciendo pedido de cliente");
        }else{
            aceptarOrdenDeCliente(cliente);
        }
    }
    
    private void aceptarOrdenDeCliente(Cliente cliente){
        ordenes.insertarPedido(new Nodo(cliente.darPedido()));
    }

    public double apertura(){
        return HORA_APERTURA;
    }

    public double cierre(){
        return HORA_CIERRE;
    }



    
}
