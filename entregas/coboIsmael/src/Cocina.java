public class Cocina {

    private ListaPedidos pedidosPendientes;
    private Pedido pedidoEnCocina;
    private int pedidosAtendidos;
    private int comparacionesSeleccion;

    public Cocina() {
        this.pedidosPendientes = new ListaPedidos();
        this.pedidoEnCocina = null;
        this.pedidosAtendidos = 0;
        this.comparacionesSeleccion = 0;
    }

    public void anadirPedido(Pedido pedido) {
        pedidosPendientes.insertarAlFinal(pedido);
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

    public int getComparacionesSeleccion() {
        return comparacionesSeleccion;
    }

    public void seleccionarSiguientePedido() {
        if (pedidoEnCocina != null) {
            return;
        }
        if (pedidosPendientes.estaVacia()) {
            return;
        }

        int indiceMinimo = 0;
        Pedido pedidoMinimo = pedidosPendientes.obtenerEn(0);

        for (int posicionActual = 1; posicionActual < pedidosPendientes.getTamano(); posicionActual++) {
            Pedido pedidoActual = pedidosPendientes.obtenerEn(posicionActual);
            comparacionesSeleccion++;
            if (pedidoActual.getTiempoPreparacion() < pedidoMinimo.getTiempoPreparacion()) {
                indiceMinimo = posicionActual;
                pedidoMinimo = pedidoActual;
            }
        }

        pedidoEnCocina = pedidosPendientes.eliminarEn(indiceMinimo);
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