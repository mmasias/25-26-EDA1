public class Restaurante {
    private ArbolPedidos arbolPendientes;
    private int totalPedidosPendientes;
    private Pedido pedidoEnPreparacion;
    private int minutoActual;
    private int duracionJornadaMinutos;
    private int totalPedidosAtendidos;
    private int sumaTiemposEspera;
    private int numeroComparacionesInsercion;
    private int siguienteIdentificador;
    private static final double PROBABILIDAD_LLEGADA = 0.4;

    public Restaurante(int duracionJornadaMinutos) {
        this.arbolPendientes = new ArbolPedidos();
        this.totalPedidosPendientes = 0;
        this.pedidoEnPreparacion = null;
        this.minutoActual = 0;
        this.duracionJornadaMinutos = duracionJornadaMinutos;
        this.totalPedidosAtendidos = 0;
        this.sumaTiemposEspera = 0;
        this.numeroComparacionesInsercion = 0;
        this.siguienteIdentificador = 1;
    }

    public void ejecutarSimulacion() {
        for (minutoActual = 1; minutoActual <= duracionJornadaMinutos; minutoActual++) {
            System.out.println("========================================");
            System.out.println("[" + minutoActual + ".0]");

            if (hayLlegadaPedido()) {
                Pedido nuevoPedido = crearPedidoAleatorio();
                insertarPedidoEnArbol(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido.tipoPlato().nombrePlato()
                        + " (" + nuevoPedido.tiempoPendiente() + " min)");
            }

            System.out.println("PEDIDOS PENDIENTES: " + totalPedidosPendientes);

            if (pedidoEnPreparacion == null && totalPedidosPendientes > 0) {
                seleccionarPedidoParaCocinar();
            }

            if (pedidoEnPreparacion == null) {
                System.out.println("Cocinero: [Libre]");
            } else {
                System.out.println("Cocinero: [" + pedidoEnPreparacion.tipoPlato().nombrePlato()
                        + " - " + pedidoEnPreparacion.tiempoPendiente() + " min restantes]");
                avanzarUnMinutoDeCocinado();
            }
        }

        mostrarResumenFinal();
    }

    private boolean hayLlegadaPedido() {
        double valorAleatorio = Math.random();
        return valorAleatorio < PROBABILIDAD_LLEGADA;
    }

    private Pedido crearPedidoAleatorio() {
        TipoPlato tipoPlatoAleatorio = elegirTipoPlatoAleatorio();
        int duracion = tipoPlatoAleatorio.generarDuracionPreparacion();
        Pedido pedido = new Pedido(siguienteIdentificador, tipoPlatoAleatorio, duracion, minutoActual);
        siguienteIdentificador++;
        return pedido;
    }

    private TipoPlato elegirTipoPlatoAleatorio() {
        int valor = (int) (Math.random() * 5);
        if (valor == 0) {
            return TipoPlato.BEBIDA;
        } else if (valor == 1) {
            return TipoPlato.CAFE;
        } else if (valor == 2) {
            return TipoPlato.COLACAO;
        } else if (valor == 3) {
            return TipoPlato.BOCADILLO;
        } else {
            return TipoPlato.ENSALADA;
        }
    }

    private void insertarPedidoEnArbol(Pedido pedidoNuevo) {
        int comparaciones = arbolPendientes.insertarSegunPrioridad(pedidoNuevo);
        numeroComparacionesInsercion += comparaciones;
        totalPedidosPendientes++;
    }

    private void seleccionarPedidoParaCocinar() {
        assert totalPedidosPendientes > 0 : "No hay pedidos pendientes";
        assert arbolPendientes.hayPedidos() : "El árbol está vacío";
        Pedido siguiente = arbolPendientes.tomarPedidoConMayorPrioridad();
        pedidoEnPreparacion = siguiente;
        pedidoEnPreparacion.establecerInstanteInicio(minutoActual);
        int espera = pedidoEnPreparacion.instanteInicio() - pedidoEnPreparacion.instanteLlegada();
        sumaTiemposEspera += espera;
        totalPedidosPendientes--;
    }

    private void avanzarUnMinutoDeCocinado() {
        pedidoEnPreparacion.reducirUnMinutoDePreparacion();
        if (pedidoEnPreparacion.haFinalizado()) {
            totalPedidosAtendidos++;
            pedidoEnPreparacion = null;
        }
    }

    private void mostrarResumenFinal() {
        int pendientes = totalPedidosPendientes;
        if (pedidoEnPreparacion != null) {
            pendientes++;
        }

        System.out.println("========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + totalPedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pendientes);
        System.out.println("Tiempo total de espera   : " + sumaTiemposEspera + " minutos");
        double tiempoMedio = 0.0;
        if (totalPedidosAtendidos > 0) {
            tiempoMedio = (double) sumaTiemposEspera / totalPedidosAtendidos;
        }
        System.out.println("Tiempo medio de espera   : " + String.format("%.2f", tiempoMedio));
        System.out.println("Comparaciones inserción  : " + numeroComparacionesInsercion);
        System.out.println("========================================");
    }
}
