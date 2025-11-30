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
}


