public class Cocina {
    private ArbolPedidos colaPedidos;
    private Pedido pedidoEnProceso;

    private int pedidosAtendidos;
    private int tiempoTotalEspera;

    public Cocina() {
        this.colaPedidos = new ArbolPedidos();
        this.pedidoEnProceso = null;
        this.pedidosAtendidos = 0;
        this.tiempoTotalEspera = 0;
    }

    public void recibirPedido(Pedido p) {
        colaPedidos.insertar(p);
    }

    public void trabajar(int minutoActual) {
        if (pedidoEnProceso == null && !colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.extraerMinimo();
        }

        if (pedidoEnProceso != null) {
            pedidoEnProceso.procesarMinuto();

            if (pedidoEnProceso.estaTerminado()) {
                finalizarPedido(minutoActual);

                if (!colaPedidos.isEmpty()) {
                    pedidoEnProceso = colaPedidos.extraerMinimo();
                }
            }
        }
    }

    private void finalizarPedido(int minutoActual) {
        pedidosAtendidos++;

        // Cálculo de espera exacta
        int tiempoEnSistema = minutoActual - pedidoEnProceso.getMinutoLlegada();
        int esperaReal = tiempoEnSistema - pedidoEnProceso.getTiempoTotal();

        if (esperaReal < 0)
            esperaReal = 0;

        tiempoTotalEspera += esperaReal;
        pedidoEnProceso = null;
    }

    public void imprimirEstado() {
        System.out.print("COLA: " + colaPedidos.getTamano() + " pedidos | ");
        if (pedidoEnProceso != null) {
            System.out.println(
                    "Cocinando: [" + pedidoEnProceso + " - Restan: " + pedidoEnProceso.getTiempoRestante() + " min]");
        } else {
            System.out.println("Cocinero: [Libre]");
        }
    }

    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DE LA JORNADA (Estructura Árbol) ===");
        System.out.println("Pedidos atendidos      : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes     : " + (colaPedidos.getTamano() + (pedidoEnProceso != null ? 1 : 0)));
        System.out.println("Tiempo total de espera : " + tiempoTotalEspera + " minutos");

        double media = pedidosAtendidos > 0 ? (double) tiempoTotalEspera / pedidosAtendidos : 0;
        System.out.printf("Tiempo medio de espera : %.2f minutos\n", media);

        System.out.println("Comparaciones totales (Eficiencia): " + ArbolPedidos.comparacionesTotales);
        System.out.println("==========================================");
    }
}