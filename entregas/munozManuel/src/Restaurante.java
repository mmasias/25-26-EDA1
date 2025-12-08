package entregas.munozManuel.src;

class Restaurante {
    private final int HORA_APERTURA = 9;
    private final int HORA_CIERRE = 21;
    private final String nombreRestaurante;
    private final Chef chef;
    private final Arbol ordenes;
    private int personasEnFila;

    public Restaurante(String nombre, Chef chef) {
        this.chef = chef;
        nombreRestaurante = nombre;
        ordenes = new Arbol();
        personasEnFila = 0;
    }

    public void mostrarPedidosEnFila() {
        ordenes.recorrerOrdenes();
    }

    public void tomarPedido(Cliente cliente) {
        System.out.println("Nuevo cliente en fila");
        personasEnFila += 1;
        aceptarOrdenDeCliente(cliente);
    }

    public void asignarPedido() {
        Nodo nodoConPedido = ordenes.buscarNodoConTiempoMinimo();
        Pedido pedidoDelCliente = nodoConPedido.pedido();
        System.out.println("Se ha tardado " + ordenes.contarComparaciones() + " comparaciones para encontrar su orden");
        chef.tomarPedido(pedidoDelCliente);
        personasEnFila -= 1;
    }

    public int pedidosAtendidos(){
        return ordenes.recorrerOrdenes(true);
    }

    public int pedidosPendientes(){
        return ordenes.recorrerOrdenes(false);
    }


    public String nombreRestaurante() {
        return nombreRestaurante;
    }

    public int cantidadOrdenes() {
        return ordenes.cantidadNodos();
    }

    public double apertura() {
        return HORA_APERTURA;
    }

    public double cierre() {
        return HORA_CIERRE;
    }

    public int personasEnFila() {
        return personasEnFila;
    }

    private void aceptarOrdenDeCliente(Cliente cliente) {
        Pedido pedidoDelCliente = cliente.darPedido();
        ordenes.insertarPedido(new Nodo(pedidoDelCliente));
        System.out.println("Se ha aceptado la orden " + pedidoDelCliente.nombreDelPedido() + " del cliente "
                + cliente.nombre() + " y esta esperando en fila");
    }

}
