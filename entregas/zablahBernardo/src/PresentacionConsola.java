
public class PresentacionConsola {

    public void imprimirSeparador() {
        System.out.println("========================================");
    }

    public void imprimirCabeceraMinuto(int minuto) {
        imprimirSeparador();
        System.out.println("[" + minuto + ".0]");
    }

    public void imprimirLlegadaPedido(Pedido pedido) {
        System.out.println("Llega pedido: " + pedido.getNombre() + 
                           " (" + pedido.getTiempoPreparacion() + " min)");
    }

    public void imprimirEstado(int tamanoCola, Pedido pedidoEnProceso) {
        System.out.println("COLA: " + tamanoCola + " pedidos");
        
        if (pedidoEnProceso != null) {
            System.out.println("Cocinero: [" + pedidoEnProceso.getNombre() + 
                               " - " + pedidoEnProceso.getTiempoRestante() + " min restantes]");
        } else {
            System.out.println("Cocinero: [ESPERANDO]");
        }
    }

    public void imprimirResumen(int pedidosAtendidos, int pendientes, int tiempoEsperaTotal, Object comparaciones) {
        double media = pedidosAtendidos > 0 ? (double) tiempoEsperaTotal / pedidosAtendidos : 0.0;

        System.out.println("\n");
        System.out.println("RESUMEN DE LA JORNADA");
        imprimirSeparador();
        System.out.printf("%-25s: %d%n", "Pedidos atendidos", pedidosAtendidos);
        System.out.printf("%-25s: %d%n", "Pedidos pendientes", pendientes);
        System.out.printf("%-25s: %d minutos%n", "Tiempo total de espera", tiempoEsperaTotal);
        System.out.printf("%-25s: %.1f minutos%n", "Tiempo medio de espera", media);
        System.out.printf("%-25s: %d%n", "Comparaciones totales", comparaciones);
        imprimirSeparador();
    }

}
