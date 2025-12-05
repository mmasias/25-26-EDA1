public class Vista {
    public void mostrarTiempo(double minuto) {
        System.out.println("========================================");
        System.out.println("[" + minuto + "]");
    }

    public void mostrarLlegadaPedido(Pedido pedido) {
        System.out.println("Llega pedido: " + pedido.getTipoDePlato() + 
                           " (" + pedido.getTiempoDePreparacion() + " min)");
    }

    public void mostrarEstado(int tamanoCola, Cocinero cocinero) {
        System.out.println("COLA: " + tamanoCola + " pedidos");
        if (cocinero.estaLibre()) {
            System.out.println("Cocinero: [Libre]");
        } else {
            Pedido p = cocinero.getPedidoActual();
            int tiempo = cocinero.getTiempoRestante();
            System.out.println("Cocinero: [" + p.getTipoDePlato() + " - " + tiempo + " min restantes]");
        }
    }

    public void mostrarResumen(int atendidos, int pendientes, int tiempoTotal, 
                               double tiempoMedio, int comparaciones) {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos      : " + atendidos);
        System.out.println("Pedidos pendientes     : " + pendientes);
        System.out.println("Tiempo total de espera : " + tiempoTotal + " minutos");
        System.out.println("Tiempo medio de espera : " + String.format("%.1f", tiempoMedio) + " minutos");
        System.out.println("Comparaciones totales  : " + comparaciones);
        System.out.println("========================================");
    }
}