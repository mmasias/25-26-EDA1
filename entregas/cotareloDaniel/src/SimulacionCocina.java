public class SimulacionCocina {

    private static final int DURACION_JORNADA_MINUTOS = 120;
    private static final double PROBABILIDAD_LLEGADA_PEDIDO = 0.4;
    private static final int CAPACIDAD_MAXIMA_PEDIDOS = 1000;

    private ColaPrioridadPedidos colaPedidos;
    private Pedido pedidoEnPreparacion;
    private int minutoActual;
    private int numeroPedidosAtendidos;
    private int numeroPedidosPendientes;
    private int tiempoEsperaTotal;

    public SimulacionCocina() {
        colaPedidos = new ColaPrioridadPedidos(CAPACIDAD_MAXIMA_PEDIDOS);
        pedidoEnPreparacion = null;
        minutoActual = 0;
        numeroPedidosAtendidos = 0;
        numeroPedidosPendientes = 0;
        tiempoEsperaTotal = 0;
    }

    public void ejecutar() {
        minutoActual = 1;
        while (minutoActual <= DURACION_JORNADA_MINUTOS) {
            imprimirSeparador();
            imprimirMinutoActual();
            procesarLlegadaPedido();

            if (pedidoEnPreparacion == null) {
                seleccionarSiguientePedido();
            }

            imprimirEstadoCocina();
            procesarTrabajoCocinero();
            minutoActual = minutoActual + 1;
        }
        imprimirResumenJornada();
    }

    private void procesarLlegadaPedido() {
        double numeroAleatorio;
        numeroAleatorio = Math.random();

        if (numeroAleatorio < PROBABILIDAD_LLEGADA_PEDIDO) {
            Plato platoNuevo;
            Pedido pedidoNuevo;

            platoNuevo = generarPlatoAleatorio();
            pedidoNuevo = new Pedido(platoNuevo, minutoActual);

            colaPedidos.agregarPedido(pedidoNuevo);

            System.out.println("Llega pedido: " + platoNuevo.obtenerNombre() + " ("
                    + platoNuevo.obtenerTiempoPreparacion() + " minutos)");
        }
    }

    private Plato generarPlatoAleatorio() {
        int indicePlato;
        Plato platoGenerado;

        indicePlato = (int) (Math.random() * 5);

        switch (indicePlato) {
            case 0:
                platoGenerado = new Bebida();
                break;
            case 1:
                platoGenerado = new Cafe();
                break;
            case 2:
                platoGenerado = new Colacao();
                break;
            case 3:
                platoGenerado = new Bocadillo();
                break;
            default:
                platoGenerado = new Ensalada();
                break;
        }

        return platoGenerado;
    }

    private void seleccionarSiguientePedido() {
        boolean colaVacia;
        colaVacia = colaPedidos.estaVacia();

        if (!colaVacia) {
            Pedido pedidoSeleccionado;
            int tiempoEsperaPedido;

            pedidoSeleccionado = colaPedidos.extraerPedidoMenorTiempo();
            pedidoEnPreparacion = pedidoSeleccionado;

            pedidoEnPreparacion.establecerInstanteComienzo(minutoActual);

            tiempoEsperaPedido = pedidoEnPreparacion.calcularTiempoEspera();
            tiempoEsperaTotal = tiempoEsperaTotal + tiempoEsperaPedido;
        }
    }

    private void procesarTrabajoCocinero() {
        if (pedidoEnPreparacion != null) {
            pedidoEnPreparacion.reducirTiempoRestante();

            if (pedidoEnPreparacion.obtenerTiempoRestante() == 0) {
                numeroPedidosAtendidos = numeroPedidosAtendidos + 1;
                pedidoEnPreparacion = null;
            }
        }
    }

    private void imprimirSeparador() {
        System.out.println("========================================");
    }

    private void imprimirMinutoActual() {
        System.out.println("[" + minutoActual + ".0]");
    }

    private void imprimirEstadoCocina() {
        int pedidosEnCola;
        pedidosEnCola = colaPedidos.obtenerNumeroPedidosEnCola();

        System.out.println("COLA: " + pedidosEnCola + " pedidos");

        if (pedidoEnPreparacion != null) {
            System.out.println("Cocinero: [" + pedidoEnPreparacion.obtenerNombrePlato() + " - "
                    + pedidoEnPreparacion.obtenerTiempoRestante() + " minutos restantes]");
        } else {
            System.out.println("Cocinero: [Sin pedidos en preparación]");
        }
    }

    private void imprimirResumenJornada() {
        int comparacionesTotales;
        double tiempoMedioEspera;
        double tiempoMedioRedondeado;

        imprimirSeparador();
        System.out.println("RESUMEN DE LA JORNADA");
        imprimirSeparador();

        numeroPedidosPendientes = colaPedidos.obtenerNumeroPedidosEnCola();
        if (pedidoEnPreparacion != null && pedidoEnPreparacion.obtenerTiempoRestante() > 0) {
            numeroPedidosPendientes = numeroPedidosPendientes + 1;
        }

        if (numeroPedidosAtendidos > 0) {
            tiempoMedioEspera = (double) tiempoEsperaTotal / numeroPedidosAtendidos;
            tiempoMedioRedondeado = Math.round(tiempoMedioEspera * 100.0) / 100.0;
        } else {
            tiempoMedioRedondeado = 0.0;
        }

        comparacionesTotales = colaPedidos.obtenerNumeroComparaciones();

        System.out.println("Pedidos atendidos        : " + numeroPedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + numeroPedidosPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        System.out.println("Tiempo medio de espera   : " + tiempoMedioRedondeado + " minutos");
        System.out.println("Comparaciones totales    : " + comparacionesTotales);

        imprimirSeparador();
    }
}