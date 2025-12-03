package entregas.munozManuel.src;

class Restaurante {
    private final double HORA_APERTURA = 9.0;
    private final double HORA_CIERRE = 21.0;
    private final String nombreRestaurante;
    private final Chef chef;
    private final Arbol ordenes;
    private int personasEnFila;

    public Restaurante(String nombre, Chef chef){
        this.chef = chef;
        nombreRestaurante = nombre;
        ordenes = new Arbol();
        personasEnFila = 0;
    }

    public String nombreRestaurante(){
        return nombreRestaurante;
    }

    public int cantidadOrdenes(){
        return ordenes.cantidadNodos();
    }
    
    public void tomarPedido(Cliente cliente){
        System.out.println("Nuevo cliente en fila");
        personasEnFila += 1;
        aceptarOrdenDeCliente(cliente);
        System.out.println("Pedido de cliente en cola");
    }

    public void asignarPedido(){
        Nodo pedidoDeCliente = ordenes.buscarNodoConTiempoMinimo();
        chef.tomarPedido(pedidoDeCliente.pedido());
        personasEnFila -= 1;
    }
    
    public double apertura(){
        return HORA_APERTURA;
    }

    public double cierre(){
        return HORA_CIERRE;
    }

    public int personasEnFila(){
        return  personasEnFila;
    }

    private void aceptarOrdenDeCliente(Cliente cliente){
        ordenes.insertarPedido(new Nodo(cliente.darPedido()));
    }
    
}
