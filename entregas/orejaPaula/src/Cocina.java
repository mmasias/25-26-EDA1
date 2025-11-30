public class Cocina {
    private final IEstructuraPedidos estructuraPedidos;
    private Pedido pedidoActual;

    public Cocina(IEstructuraPedidos estructuraPedidos) {
        this.estructuraPedidos = estructuraPedidos;
        this.pedidoActual = null;
    }

    public void recibirPedido(Pedido p, Estadisticas estadisticas) {
        estructuraPedidos.insertar(p);
        estadisticas.sumarComparaciones(estructuraPedidos.getComparaciones());
        estructuraPedidos.resetComparaciones();
    }
}

