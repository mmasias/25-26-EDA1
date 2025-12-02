public class EstadisticasJornada {

    private int cantidadPedidosAtendidos;
    private int cantidadPedidosPendientes;
    private int sumaTotalTiemposEspera;
    private double tiempoMedioEspera;

    public EstadisticasJornada(int cantidadPedidosAtendidos, int cantidadPedidosPendientes, int sumaTotalTiemposEspera) {
        this.cantidadPedidosAtendidos = cantidadPedidosAtendidos;
        this.cantidadPedidosPendientes = cantidadPedidosPendientes;
        this.sumaTotalTiemposEspera = sumaTotalTiemposEspera;
        this.tiempoMedioEspera = 0.0;
    }

    public void calcularTiempoMedioEspera() {
        if (cantidadPedidosAtendidos > 0) {
            tiempoMedioEspera = (double) sumaTotalTiemposEspera / (double) cantidadPedidosAtendidos;
        } else {
            tiempoMedioEspera = 0.0;
        }
    }

    public void mostrarEnConsola() {
        System.out.println("----- Resumen de la jornada -----");
        System.out.println("Pedidos atendidos: " + cantidadPedidosAtendidos);
        System.out.println("Pedidos pendientes: " + cantidadPedidosPendientes);
        System.out.println("Tiempo total de espera: " + sumaTotalTiemposEspera + " minutos");
        System.out.println("Tiempo medio de espera: " + tiempoMedioEspera + " minutos");
        System.out.println("---------------------------------");
    }
}
