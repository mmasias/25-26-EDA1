public class Cocina {

    private int minutoActual;
    private int minutosTotalesSimulacion;
    private double probabilidadLlegadaPedido;

    private int siguienteIdPedido;

    private ArbolPedidos arbolPedidos;
    private Cocinero cocinero;

    public Cocina(int minutosTotalesSimulacion, double probabilidadLlegadaPedido) {
        this.minutoActual = 0;
        this.minutosTotalesSimulacion = minutosTotalesSimulacion;
        this.probabilidadLlegadaPedido = probabilidadLlegadaPedido;
        this.siguienteIdPedido = 1;
        this.arbolPedidos = new ArbolPedidos();
        this.cocinero = new Cocinero();
    }

    public void ejecutarSimulacion() {
        while (minutoActual < minutosTotalesSimulacion) {
            procesarMinuto();
            minutoActual++;
        }
        mostrarResumenFinal();
    }

    private void procesarMinuto() {
        generarPedidoSiLlega();
        arbolPedidos.incrementarEsperaEnTodosLosPedidos();
        cocinero.procesarMinuto(arbolPedidos);
        mostrarEstadoActual();
    }

    private void generarPedidoSiLlega() {
        double valorAleatorio = Math.random();
        if (valorAleatorio < probabilidadLlegadaPedido) {
            Pedido nuevoPedido = crearPedidoAleatorio();
            arbolPedidos.insertarPedido(nuevoPedido);
            System.out.println("Minuto " + minutoActual +
                    ": llega pedido " + nuevoPedido.identificador() +
                    " (duración " + nuevoPedido.duracionTotal() + ")");
        }
    }

    private Pedido crearPedidoAleatorio() {
        TipoPlato tipo = TipoPlato.elegirTipoAleatorio();
        int duracion = tipo.duracionAleatoria();
        Pedido pedido = new Pedido(siguienteIdPedido, tipo, duracion, minutoActual);
        siguienteIdPedido++;
        return pedido;
    }

    private void mostrarEstadoActual() {
        String textoCocinero;
        if (cocinero.estaOcupado()) {
            Pedido pedidoEnCurso = cocinero.pedidoActual();
            textoCocinero = "Cocinero preparando pedido " + pedidoEnCurso.identificador() +
                    " (restante " + cocinero.minutosRestantesPedidoActual() + ")";
        } else {
            textoCocinero = "Cocinero libre";
        }
        System.out.println("Minuto " + minutoActual +
                ": pedidos en árbol=" + arbolPedidos.cantidadPedidos() +
                " | " + textoCocinero);
    }

    private void mostrarResumenFinal() {
        int pedidosAtendidos = cocinero.pedidosTerminados();
        int pedidosPendientes = arbolPedidos.cantidadPedidos();
        int tiempoEsperaTotal = cocinero.tiempoTotalEsperaAcumulada();

        double tiempoEsperaMedio;
        if (pedidosAtendidos > 0) {
            tiempoEsperaMedio = (double) tiempoEsperaTotal / (double) pedidosAtendidos;
        } else {
            tiempoEsperaMedio = 0.0;
        }

        System.out.println("----- Resumen jornada -----");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Pedidos pendientes en árbol: " + pedidosPendientes);
        System.out.println("Suma total tiempos de espera: " + tiempoEsperaTotal);
        System.out.println("Tiempo medio de espera: " + tiempoEsperaMedio);
        System.out.println("---------------------------");
    }
}
