public class Cocina {
    private final IEstructuraPedidos estructuraPedidos;
    private Pedido pedidoActual;

    public Cocina(IEstructuraPedidos estructuraPedidos) {
        this.estructuraPedidos = estructuraPedidos;
        this.pedidoActual = null;
    }
}

