public class SimulacionRCCCF {

    private static final int MINUTOS_JORNADA = 12 * 60;
    private static final double PROBABILIDAD_LLEGADA = 0.4;

    private Cocina cocina;
    private int siguienteIdPedido;
    private int tiempoEsperaAcumulado;

    public SimulacionRCCCF() {
        this.cocina = new Cocina();
        this.siguienteIdPedido = 1;
        this.tiempoEsperaAcumulado = 0;
    }

    public static void main(String[] args) {
        SimulacionRCCCF simulacion = new SimulacionRCCCF();
        simulacion.ejecutar();
    }

    public void ejecutar() {
        for (int minutoActual = 1; minutoActual <= MINUTOS_JORNADA; minutoActual++) {
            System.out.println("========================================");
            System.out.println("[" + minutoActual + ".0]");

            boolean hayLlegada = llegaNuevoPedido();
            if (hayLlegada) {
                Pedido nuevoPedido = generarPedido(minutoActual);
                cocina.anadirPedido(nuevoPedido);
                System.out.println("Llega pedido: " + nuevoPedido.describir());
            }

            cocina.seleccionarSiguientePedido();

            int pedidosEnCola = cocina.getNumeroPedidosPendientes();
            System.out.println("COLA: " + pedidosEnCola + " pedidos");
            tiempoEsperaAcumulado += pedidosEnCola;

            Pedido pedidoActual = cocina.getPedidoEnCocina();
            if (pedidoActual == null) {
                System.out.println("Cocinero: [libre]");
            } else {
                System.out.println("Cocinero: [" +
                    pedidoActual.getTipo() + " - " +
                    pedidoActual.getTiempoRestante() +
                    " min restantes]");
            }

            cocina.procesarUnMinuto();
        }

        int pedidosPendientes = cocina.getNumeroPedidosPendientes();
        if (cocina.getPedidoEnCocina() != null) {
            pedidosPendientes++;
        }

        System.out.println("========================================");
        System.out.println();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + cocina.getPedidosAtendidos());
        System.out.println("Pedidos pendientes       : " + pedidosPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaAcumulado + " minutos");

        int pedidosTotales = cocina.getPedidosAtendidos() + pedidosPendientes;
        double tiempoMedioEspera = 0.0;
        if (pedidosTotales > 0) {
            tiempoMedioEspera = (double) tiempoEsperaAcumulado / pedidosTotales;
        }

        System.out.println("Tiempo medio de espera   : " +
            String.format("%.2f", tiempoMedioEspera) +
            " minutos");
        System.out.println("Comparaciones totales    : " +
            cocina.getComparacionesSeleccion());
        System.out.println("========================================");
    }

    private boolean llegaNuevoPedido() {
        double valor = generarNumeroAleatorio();
        return valor < PROBABILIDAD_LLEGADA;
    }

    private Pedido generarPedido(int minutoActual) {
        String tipo = elegirTipoAleatorio();
        int tiempoPreparacion = generarTiempoPreparacion(tipo);
        Pedido pedido = new Pedido(siguienteIdPedido, tipo, tiempoPreparacion, minutoActual);
        siguienteIdPedido++;
        return pedido;
    }

    private String elegirTipoAleatorio() {
        int numero = generarEnteroAleatorio(0, 4);
        if (numero == 0) {
            return "Bebida";
        } else if (numero == 1) {
            return "Café";
        } else if (numero == 2) {
            return "Colacao";
        } else if (numero == 3) {
            return "Bocadillo";
        } else {
            return "Ensalada";
        }
    }

    private int generarTiempoPreparacion(String tipo) {
        if (tipo.equals("Bebida")) {
            return generarEnteroAleatorio(1, 2);
        } else if (tipo.equals("Café")) {
            return generarEnteroAleatorio(2, 3);
        } else if (tipo.equals("Colacao")) {
            return generarEnteroAleatorio(2, 4);
        } else if (tipo.equals("Bocadillo")) {
            return generarEnteroAleatorio(3, 5);
        } else {
            return generarEnteroAleatorio(5, 8);
        }
    }

    private double generarNumeroAleatorio() {
        return Math.random();
    }

    private int generarEnteroAleatorio(int minimo, int maximo) {
        return minimo + (int)(Math.random() * ((maximo - minimo) + 1));
    }
} 