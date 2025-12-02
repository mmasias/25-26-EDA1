public class Cocinero {

    private Pedido actual = null;
    private int pedidosAtendidos = 0;
    private int tiempoEsperaTotal = 0;

    public Pedido getPedidoActual() {
        return actual;
    }

    public int getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public int getTiempoEsperaTotal() {
        return tiempoEsperaTotal;
    }

    public void asignarPedido(Pedido p, int minutoActual) {
        actual = p;
        tiempoEsperaTotal += (minutoActual - p.getMinutoLlegada());
    }

    public void procesar() {
        if (actual == null) return;

        actual.procesarMinuto();

        if (actual.getTiempoRestante() == 0) {
            pedidosAtendidos++;
            actual = null;
        }
    }
}
