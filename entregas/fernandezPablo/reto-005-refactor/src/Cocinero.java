public class Cocinero {

    private Pedido pedidoActual = null;
    private int pedidosAtendidos = 0;
    private int tiempoEsperaTotal = 0;

    public Pedido getPedidoActual() { return pedidoActual; }
    public int getPedidosAtendidos() { return pedidosAtendidos; }
    public int getTiempoEsperaTotal() { return tiempoEsperaTotal; }

    public void asignarPedido(Pedido pedidoAsignado, int minutoActual) {
        pedidoActual = pedidoAsignado;
        tiempoEsperaTotal += minutoActual - pedidoAsignado.getMinutoLlegada();
    }

    public void procesar() {
        if (pedidoActual == null) {
            return;
        }

        pedidoActual.procesarMinuto();

        if (pedidoActual.getTiempoRestante() == 0) {
            pedidosAtendidos++;
            pedidoActual = null;
        }
    }
}


