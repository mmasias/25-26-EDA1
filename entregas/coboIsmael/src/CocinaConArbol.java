public class CocinaConArbol {

    private ArbolPedidos pedidosPendientes;
    private Pedido pedidoEnCocina;
    private int pedidosAtendidos;

    public CocinaConArbol() {
        this.pedidosPendientes = new ArbolPedidos();
        this.pedidoEnCocina = null;
        this.pedidosAtendidos = 0;
    }

    public void anadirPedido(Pedido pedido) {
        pedidosPendientes.insertar(pedido);
    }

    public int getNumeroPedidosPendientes() {
        return pedidosPendientes.getTamano();
    }

    public Pedido getPedidoEnCocina() {
        return pedidoEnCocina;
    }

    public int getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public int getComparacionesInsercion() {
        return pedidosPendientes.getComparacionesInsercion();
    }

    public int getComparacionesSeleccion() {
        return pedidosPendientes.getComparacionesSeleccion();
    }

    public void seleccionarSiguientePedido() {
        if (pedidoEnCocina != null) {
            return;
        }
        if (pedidosPendientes.estaVacio()) {
            return;
        }
        pedidoEnCocina = pedidosPendientes.extraerMinimo();
    }

    public void procesarUnMinuto() {
        if (pedidoEnCocina == null) {
            return;
        }
        int tiempoRestante = pedidoEnCocina.getTiempoRestante();
        tiempoRestante--;
        pedidoEnCocina.setTiempoRestante(tiempoRestante);
        if (tiempoRestante == 0) {
            pedidosAtendidos++;
            pedidoEnCocina = null;
        }
    }
}
