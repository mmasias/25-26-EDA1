public class Estadisticas {
    private int totalPedidosAtendidos;
    private int totalPedidosPendientes;
    private double tiempoEsperaAcumulado;
    private double comparacionesRealizadas;

    public Estadisticas() {
        totalPedidosAtendidos = 0;
        totalPedidosPendientes = 0;
        tiempoEsperaAcumulado = 0;
        comparacionesRealizadas = 0;
    }

    public void registrarInicioServicio(Pedido pedido, int instanteInicio) {
        tiempoEsperaAcumulado += (instanteInicio - pedido.getInstanteLlegada());
    }

    public void registrarPedidoAtendido(Pedido pedido) {
        totalPedidosAtendidos++;
    }

    public void registrarComparaciones(double cantidadComparaciones) {
        comparacionesRealizadas += cantidadComparaciones;
    }

    public int getTotalPedidosAtendidos() { 
        return totalPedidosAtendidos; 
    }

    public int getTotalPedidosPendientes() { 
        return totalPedidosPendientes; 
    }

    public double getTiempoEsperaAcumulado() { 
        return tiempoEsperaAcumulado; 
    }

    public double getTiempoEsperaMedio() {
        return totalPedidosAtendidos == 0 ? 0 : tiempoEsperaAcumulado / totalPedidosAtendidos;
    }

    public double getComparacionesRealizadas() { 
        return comparacionesRealizadas; 
    }

    public String generarResumen() {
        return "Pedidos atendidos: " + totalPedidosAtendidos +
                "\nPedidos pendientes: " + totalPedidosPendientes +
                "\nTiempo total espera: " + tiempoEsperaAcumulado +
                "\nTiempo medio espera: " + getTiempoEsperaMedio() +
                "\nComparaciones totales: " + comparacionesRealizadas;
    }
}