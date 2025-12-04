import java.util.Random;
import java.util.Scanner;

class Restaurant {
    private static final double HORA_APERTURA = 9.0;
    private static final double HORA_CIERRE = 21.0;
    private static final double UN_MINUTO_EN_HORAS = 1.0 / 60.0;
    private static final double PROBABILIDAD_LLEGADA_PEDIDO = 0.4;
    private static final int TOTAL_MINUTOS_JORNADA = 720;

    private static final String[] TIPOS_PLATOS_DISPONIBLES = {
        "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"
    };
    private static final int[][] RANGOS_TIEMPO_PREPARACION = {
        {1, 2}, 
        {2, 3}, 
        {2, 4}, 
        {3, 5}, 
        {5, 8}  
    };

    private ArbolBinario arbolPedidosPendientes;
    private Pedido pedidoEnProcesamiento;
    private int cantidadPedidosAtendidos;
    private int acumuladorTiempoEspera;
    private int contadorMinutoSimulacion;
    private Random generadorNumerosAleatorios;
    private Scanner lectorEntradaUsuario;

    public Restaurant() {
        arbolPedidosPendientes = new ArbolBinario();
        pedidoEnProcesamiento = null;
        cantidadPedidosAtendidos = 0;
        acumuladorTiempoEspera = 0;
        contadorMinutoSimulacion = 0;
        generadorNumerosAleatorios = new Random();
        lectorEntradaUsuario = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Presiona ENTER para comenzar la simulación...");
        lectorEntradaUsuario.nextLine();
        System.out.println();

        for (double tiempoSimulacion = HORA_APERTURA;
             tiempoSimulacion < HORA_CIERRE;
             tiempoSimulacion += UN_MINUTO_EN_HORAS) {

            contadorMinutoSimulacion++;

            validarLlegadaNuevoPedido();
            procesarColaDeCocina();
            mostrarEstadoActualDelSistema();

            if (contadorMinutoSimulacion < TOTAL_MINUTOS_JORNADA) {
                System.out.print("Presiona ENTER para continuar...");
                lectorEntradaUsuario.nextLine();
                System.out.println();
            }
        }

        mostrarResumenFinalDeLaJornada();
        lectorEntradaUsuario.close();
    }

    private void validarLlegadaNuevoPedido() {
        if (generadorNumerosAleatorios.nextDouble() < PROBABILIDAD_LLEGADA_PEDIDO) {
            int indicePlato = generadorNumerosAleatorios.nextInt(TIPOS_PLATOS_DISPONIBLES.length);
            String tipo = TIPOS_PLATOS_DISPONIBLES[indicePlato];
            int minimoTiempo = RANGOS_TIEMPO_PREPARACION[indicePlato][0];
            int maximoTiempo = RANGOS_TIEMPO_PREPARACION[indicePlato][1];
            int tiempoPreparacion = minimoTiempo + generadorNumerosAleatorios.nextInt(maximoTiempo - minimoTiempo + 1);
            arbolPedidosPendientes.insertar(new Pedido(tipo, tiempoPreparacion, contadorMinutoSimulacion));
            System.out.println("[" + contadorMinutoSimulacion + ".0]");
            System.out.println("Llega pedido: " + tipo + " (" + tiempoPreparacion + " min)");
        } else {
            System.out.println("[" + contadorMinutoSimulacion + ".0]");
        }
    }

    private void procesarColaDeCocina() {
        if (pedidoEnProcesamiento == null && !arbolPedidosPendientes.estaVacio()) {
            pedidoEnProcesamiento = arbolPedidosPendientes.extraerMinimo();
            acumuladorTiempoEspera += pedidoEnProcesamiento.calcularTiempoEspera(contadorMinutoSimulacion);
        }

        if (pedidoEnProcesamiento != null) {
            pedidoEnProcesamiento.tiempoRestantePreparacion--;
            if (pedidoEnProcesamiento.tiempoRestantePreparacion == 0) {
                cantidadPedidosAtendidos++;
                pedidoEnProcesamiento = null;
                if (!arbolPedidosPendientes.estaVacio()) {
                    pedidoEnProcesamiento = arbolPedidosPendientes.extraerMinimo();
                    acumuladorTiempoEspera += pedidoEnProcesamiento.calcularTiempoEspera(contadorMinutoSimulacion);
                }
            }
        }
    }

    private void mostrarEstadoActualDelSistema() {
        System.out.println("COLA: " + arbolPedidosPendientes.obtenerCantidadPedidos() + " pedidos");
        if (pedidoEnProcesamiento != null) {
            System.out.println("Cocinero: [" + pedidoEnProcesamiento.tipoPlato + " - " +
                    pedidoEnProcesamiento.tiempoRestantePreparacion + " min restantes]");
        } else {
            System.out.println("Cocinero: [INACTIVO]");
        }
        System.out.println("========================================");
    }

    private void mostrarResumenFinalDeLaJornada() {
        int pedidosPendientes = arbolPedidosPendientes.obtenerCantidadPedidos();
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + cantidadPedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pedidosPendientes);
        System.out.println("Tiempo total de espera   : " + acumuladorTiempoEspera + " minutos");
        double tiempoMedioEspera = cantidadPedidosAtendidos > 0
                ? (double) acumuladorTiempoEspera / cantidadPedidosAtendidos
                : 0.0;
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", tiempoMedioEspera);
        System.out.println("Comparaciones totales    : " + arbolPedidosPendientes.obtenerTotalComparaciones());
        System.out.println("========================================");
    }
}