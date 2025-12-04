public class Restaurante {

    private final double HORA_APERTURA = 9.0;
    private final double HORA_CIERRE = 21.0;
    private final double MINUTO = 1.0 / 60.0;
    private final double PROBABILIDAD_LLEGADA = 0.4;

    private Cocinero cocinero;
    private GeneradorPedidos generadorPedidos;
    private ListaPedidos listaPedidos;

    private int minutoActual;
    private int pedidosAtendidos;
    private long tiempoEsperaTotal;

    public Restaurante() {
        this.cocinero = new Cocinero();
        this.generadorPedidos = new GeneradorPedidos(PROBABILIDAD_LLEGADA);
        this.listaPedidos = new ListaPedidos();
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

            if (cocinero.estaLibre() && !listaPedidos.estaVacia()) {
                Pedido siguiente = seleccionarPedido();
                cocinero.asignarPedido(siguiente, minutoActual);
            }

            System.out.println("LISTA: " + listaPedidos.tamaño() + " pedidos");

            Pedido terminado = procesarCocina();
            if (terminado != null) {
                pedidosAtendidos++;
                tiempoEsperaTotal += terminado.calcularTiempoEspera();
            }

            if (cocinero.estaLibre()) {
                System.out.println("Cocinero: [libre]");
            } else {
                Pedido actual = cocinero.getPedidoActual();
                System.out.println("Cocinero: [" + actual.getTipoPlato().getNombre()
                        + " - " + actual.getTiempoRestantePreparacion() + " min restantes]");
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
            System.out.println("Llega pedido: " + pedido.getTipoPlato().getNombre()
                    + " (" + pedido.getTiempoTotalPreparacion() + " min)");
        }
    }

    private void recibirPedido(Pedido pedido) {
        listaPedidos.insertarOrdenado(pedido);
    }

    private Pedido seleccionarPedido() {
        Pedido seleccionado = listaPedidos.extraerPrimero();
        return seleccionado;
    }

    private Pedido procesarCocina() {
        Pedido terminado = cocinero.cocinarDuranteUnMinuto();
        return terminado;
    }

    private void mostrarResumen() {
        int pendientes = listaPedidos.tamaño();
        if (!cocinero.estaLibre()) pendientes++;

        double media = pedidosAtendidos > 0
                ? (double) tiempoEsperaTotal / pedidosAtendidos
                : 0.0;

        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pendientes);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", media);
        System.out.println("Comparaciones totales    : " + Pedido.getComparaciones());
        System.out.println("========================================");
    }
}
