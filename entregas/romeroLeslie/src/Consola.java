public class Consola {
    public void imprimirSeparador() {
        System.out.println("========================================");
    }

    public void imprimirCabeceraMinuto(int minuto) {
        imprimirSeparador();
        System.out.println("[MINUTO: " + minuto + "]");
    }

    public void imprimirLlegadaPedido(Pedido pedido) {
        System.out.println("-> Llega pedido: " + pedido.getNombre() +
                " (" + pedido.getTiempoPreparacionTotal() + " min)");
    }

    public void imprimirEstado(int tamanoCola, Pedido pedidoEnProceso) {
        System.out.println("COLA COCINA: " + tamanoCola + " pedidos esperando");

        if (pedidoEnProceso != null) {
            System.out.println("Cocinero: [" + pedidoEnProceso.getNombre() +
                    " - " + pedidoEnProceso.getTiempoRestante() + " min restantes]");
        } else {
            System.out.println("Cocinero: [ESPERANDO NUEVO PEDIDO]");
        }
    }

    public void imprimirResumen(int pedidosAtendidos, int pendientes, int tiempoEsperaTotal, Object comparaciones) {
        double media = pedidosAtendidos > 0 ? (double) tiempoEsperaTotal / pedidosAtendidos : 0.0;

        System.out.println("\n");
        System.out.println("RESUMEN DEL SERVICIO");
        imprimirSeparador();
        System.out.printf("%-28s: %d%n", "Pedidos Atendidos", pedidosAtendidos);
        System.out.printf("%-28s: %d%n", "Pedidos Pendientes (Cola + Cocina)", pendientes);
        System.out.printf("%-28s: %d minutos%n", "Tiempo Total de Espera", tiempoEsperaTotal);
        System.out.printf("%-28s: %.1f minutos%n", "Tiempo Medio de Espera", media);
        System.out.printf("%-28s: %d%n", "Comparaciones del Árbol", comparaciones);
        imprimirSeparador();
    }
}