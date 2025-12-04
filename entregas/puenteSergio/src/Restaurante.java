package entregas.puenteSergio.src;

public class Restaurante {
    private ArbolPedidos cola;
    private Pedido pedidoActual;
    private int pedidosCompletados;
    private int tiempoTotalEspera;
    private long contadorComparaciones;

    public Restaurante() {
        this.cola = new ArbolPedidos();
        this.pedidosCompletados = 0;
        this.tiempoTotalEspera = 0;
        this.contadorComparaciones = 0;
    }

    public void anadirPedido(Pedido pedido) {
        cola.insertar(pedido);
    }

    public void tic(double tiempoActual) {
        if (pedidoActual == null && !cola.estaVacio()) {
            pedidoActual = cola.eliminarMinimo();
        }

        if (pedidoActual != null) {
            pedidoActual.disminuirTiempoRestante();

            if (pedidoActual.estaTerminado()) {
                pedidosCompletados++;
                tiempoTotalEspera += (tiempoActual - pedidoActual.getTiempoLlegada());
                pedidoActual = null;

                if (!cola.estaVacio()) {
                    pedidoActual = cola.eliminarMinimo();
                }
            }
        }
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public int getTamanoCola() {
        return cola.getTamano();
    }

    public long getContadorComparaciones() {
        return contadorComparaciones;
    }

    public int getPedidosCompletados() {
        return pedidosCompletados;
    }

    public int getTiempoTotalEspera() {
        return tiempoTotalEspera;
    }

    public int getContadorPedidosPendientes() {
        return cola.getTamano() + (pedidoActual != null ? 1 : 0);
    }
}
