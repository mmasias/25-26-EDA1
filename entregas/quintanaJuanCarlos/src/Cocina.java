
public class Cocina {

    private int instanteActualMinuto;
    private int duracionSimulacionMinutos;
    private double probabilidadLlegadaPedido;

    private int siguienteIdentificadorPedido;

    private ArbolPedidos arbolPedidos;
    private Cocinero cocineroUnico;
    private EstadisticasJornada estadisticasJornada;

    public CocinaRCCCF(int duracionSimulacionMinutos, double probabilidadLlegadaPedido) {
        this.instanteActualMinuto = 0;
        this.duracionSimulacionMinutos = duracionSimulacionMinutos;
        this.probabilidadLlegadaPedido = probabilidadLlegadaPedido;
        this.siguienteIdentificadorPedido = 1;
        this.arbolPedidos = new ArbolPedidos();
        this.cocineroUnico = new Cocinero();
    }

    public void ejecutarSimulacion() {
        while (instanteActualMinuto < duracionSimulacionMinutos) {
            procesarMinutoDeSimulacion();
            instanteActualMinuto++;
        }
        construirEstadisticasFinales();
        estadisticasJornada.mostrarEnConsola();
    }

    public void procesarMinutoDeSimulacion() {
        generarLlegadaDePedidoSiCorresponde();
        arbolPedidos.incrementarTiempoEsperaEnPedidos();
        cocineroUnico.procesarMinuto(arbolPedidos, instanteActualMinuto);
        mostrarEstadoMinuto();
    }

    private void generarLlegadaDePedidoSiCorresponde() {
        double valorAleatorio = Math.random();
        if (valorAleatorio < probabilidadLlegadaPedido) {
            Pedido nuevoPedido = crearPedidoAleatorio();
            arbolPedidos.insertarPedido(nuevoPedido);
            System.out.println("Minuto " + instanteActualMinuto + ": llega pedido " + nuevoPedido.identificador());
        }
    }

    private Pedido crearPedidoAleatorio() {
        TipoPlato tipoPlatoAleatorio = TipoPlato.elegirTipoAleatorio();
        int duracionPreparacion = tipoPlatoAleatorio.duracionAleatoria();
        Pedido pedido = new Pedido(siguienteIdentificadorPedido, tipoPlatoAleatorio, duracionPreparacion, instanteActualMinuto);
        siguienteIdentificadorPedido++;
        return pedido;
    }

    private void mostrarEstadoMinuto() {
        String textoCocinero;
        if (cocineroUnico.estaAtendiendoPedido()) {
            Pedido pedidoEnCurso = cocineroUnico.pedidoEnPreparacion();
            textoCocinero = "Cocinero preparando pedido " + pedidoEnCurso.identificador()
                    + " (restante: " + pedidoEnCurso.duracionRestante() + " min)";
        } else {
            textoCocinero = "Cocinero libre";
        }
        System.out.println("Minuto " + instanteActualMinuto
                + ": pedidos en espera=" + arbolPedidos.cantidadPedidos()
                + " | " + textoCocinero);
    }

    private void construirEstadisticasFinales() {
        int pedidosAtendidos = cocineroUnico.pedidosFinalizados();
        int pedidosPendientes = arbolPedidos.cantidadPedidos();
        int tiempoEsperaTotal = cocineroUnico.tiempoTotalDeEsperaAcumulado();

        estadisticasJornada = new EstadisticasJornada(pedidosAtendidos, pedidosPendientes, tiempoEsperaTotal);
        estadisticasJornada.calcularTiempoMedioEspera();
    }
}
