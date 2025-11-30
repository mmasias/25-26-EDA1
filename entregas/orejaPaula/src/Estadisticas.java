public class Estadisticas {
    private int pedidosAtendidos;
    private int pedidosPendientes;
    private long tiempoTotalEspera;
    private long comparacionesTotales;

    public void registrarAtencion(Pedido p) {
        pedidosAtendidos++;
        int espera = p.getInstanteInicio() - p.getInstanteLlegada();
        tiempoTotalEspera += espera;
    }

    public void sumarComparaciones(long n) {
        comparacionesTotales += n;
    }

    public void setPedidosPendientes(int n) {
        pedidosPendientes = n;
    }

    public int getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public int getPedidosPendientes() {
        return pedidosPendientes;
    }

    public long getTiempoTotalEspera() {
        return tiempoTotalEspera;
    }

    public long getComparacionesTotales() {
        return comparacionesTotales;
    }

    public double getTiempoMedioEspera() {
        if (pedidosAtendidos == 0) {
            return 0.0;
        }
        return (double) tiempoTotalEspera / pedidosAtendidos;
    }
}




