public class Restaurante {

    private final double HORA_APERTURA = 9.0;
    private final double HORA_CIERRE = 21.0;
    private final double MINUTO = 1.0 / 60.0;
    private final double PROBABILIDAD_LLEGADA = 0.4;

    private Cocinero cocinero;
    private GeneradorPedidos generadorPedidos;
    private ArbolPedidos arbolPedidos;

    private int minutoActual;
    private int pedidosAtendidos;
    private long tiempoEsperaTotal;

    public Restaurante() {
        this.cocinero = new Cocinero();
        this.generadorPedidos = new GeneradorPedidos(PROBABILIDAD_LLEGADA);
        this.arbolPedidos = new ArbolPedidos();
        this.minutoActual = 0;
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
    }

    public void ejecutar() {
        for (double tiempo = HORA_APERTURA; tiempo < HORA_CIERRE; tiempo += MINUTO) {
            minutoActual++;

            System.out.println("========================================");
            System.out.println("[" + minutoActual + ".0]");

            validarLlegada();

            if (cocinero.estaLibre() && !arbolPedidos.estaVacio()) {
                Pedido siguiente = seleccionarPedido();
                cocinero.asignarPedido(siguiente, minutoActual);
            }

            System.out.println("ARBOL: " + arbolPedidos.tamaño() + " pedidos");

            Pedido terminado = procesarCocina();
            if (terminado != null) {
                pedidosAtendidos++;
                tiempoEsperaTotal += terminado.calcularTiempoEspera();
            }

            if (cocinero.estaLibre()) {
                System.out.println("Cocinero: [libre]");
            } else {
                Pedido actual = cocinero.getPedidoActual();
                System.out.println("Cocinero: [" + actual.getTipoPlato().getNombre() + " - " + actual.getTiempoRestantePreparacion() + " min restantes]");
            }
        }

        System.out.println("========================================");
        System.out.println();
        mostrarResumen();
    }

    private void validarLlegada() {
        Pedido pedido = generadorPedidos.generarPedido(minutoActual);
        if (pedido != null) {
            recibirPedido(pedido);
            System.out.println("Llega pedido: " + pedido.getTipoPlato().getNombre() + " (" + pedido.getTiempoTotalPreparacion() + " min)");
        }
    }

    private void recibirPedido(Pedido pedido) {
        arbolPedidos.insertar(pedido);
    }

    private Pedido seleccionarPedido() {
        Pedido seleccionado = arbolPedidos.extraerMinimo();
        return seleccionado;
    }

    private Pedido procesarCocina() {
        Pedido terminado = cocinero.cocinarDuranteUnMinuto();
        return terminado;
    }

    private void mostrarResumen() {
        int pedidosPendientes = arbolPedidos.tamaño();
        if (!cocinero.estaLibre()) {
            pedidosPendientes++;
        }

        double tiempoMedioEspera = 0.0;
        if (pedidosAtendidos > 0) {
            tiempoMedioEspera = (double) tiempoEsperaTotal / pedidosAtendidos;
        }

        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pedidosPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", tiempoMedioEspera);
        System.out.println("Comparaciones totales    : " + Pedido.getComparaciones());
        System.out.println("========================================");
    }
}
