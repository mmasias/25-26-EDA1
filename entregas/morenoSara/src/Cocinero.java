public class Cocinero {

    private FilaPedidos filaPedidos = new FilaPedidos();
    private Pedido pedidoEnPreparacion = null;

    public boolean estaLibre() {
        return pedidoEnPreparacion == null;
    }

    public Pedido colocarPedidosEnLista(Pedido pedido) {
        filaPedidos.insertarPedido(pedido);
        return pedido;
    }

    public Pedido recibirSiguientePedido() {
        pedidoEnPreparacion = filaPedidos.obtenerSiguientePedido();
        return pedidoEnPreparacion;
    }

    public Pedido avanzarUnMinuto() {
        if (pedidoEnPreparacion == null) return null;

        pedidoEnPreparacion.reducirUnMinuto();

        if (pedidoEnPreparacion.getMinutosRestantes() == 0) {
            Pedido pedidoTerminado = pedidoEnPreparacion;
            pedidoEnPreparacion = null;
            return pedidoTerminado;
        }

        return null;
    }

    public String estadoActual() {
        if (pedidoEnPreparacion == null) return "[Cocinero libre]";
        return "[Preparando: " + pedidoEnPreparacion.toString() + "]";
    }

    public FilaPedidos getFilaPedidos() {
        return filaPedidos;
    }
}
