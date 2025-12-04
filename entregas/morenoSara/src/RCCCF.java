import java.util.Random;

public class RCCCF {

    private Recepcionista recepcionista = new Recepcionista();
    private Cocinero cocinero = new Cocinero();

    private int totalPedidosAtendidos = 0;
    private int tiempoTotalDeEspera = 0;
    private int totalComparaciones = 0;

    private Random generadorAleatorio = new Random();

    public void simularJornada() {

        for (int minutoActual = 1; minutoActual <= 120; minutoActual++) {

            System.out.println("========================================");
            System.out.println("[" + String.format("%.1f", (double) minutoActual) + "]");

            if (generadorAleatorio.nextDouble() < 0.40) {
                Pedido nuevoPedido = generarPedido(minutoActual);
                System.out.println("Llega pedido: " + nuevoPedido.getTipoPlato() +
                        " (" + nuevoPedido.getMinutosTotales() + " min)");
                recepcionista.recibirPedido(nuevoPedido, cocinero);
            }

            System.out.println("COLA: " + cocinero.getArbolPedidos().cantidadPendientes() + " pedidos");

            if (cocinero.estaLibre()) {
                cocinero.recibirSiguientePedido();
            }

            Pedido pedidoTerminado = cocinero.avanzarUnMinuto();

            if (pedidoTerminado != null) {
                totalPedidosAtendidos++;
                int tiempoEspera = minutoActual -
                        pedidoTerminado.getMinutoDeLlegada() -
                        pedidoTerminado.getMinutosTotales();
                tiempoTotalDeEspera += tiempoEspera;
            }

            System.out.println("Cocinero: " + cocinero.estadoActual());
        }

        totalComparaciones = cocinero.getArbolPedidos().getTotalComparaciones();
        resumenFinal();
    }

    private Pedido generarPedido(int minutoActual) {
        String[] tiposPlato = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
        int[][] rangosTiempo = {
                {1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}
        };

        int indice = generadorAleatorio.nextInt(tiposPlato.length);
        int minimo = rangosTiempo[indice][0];
        int maximo = rangosTiempo[indice][1];

        int duracion = generadorAleatorio.nextInt(maximo - minimo + 1) + minimo;

        return new Pedido(tiposPlato[indice], duracion, minutoActual);
    }

    public void resumenFinal() {
        System.out.println("========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + totalPedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cocinero.getArbolPedidos().cantidadPendientes());
        System.out.println("Tiempo total de espera   : " + tiempoTotalDeEspera + " minutos");
        if (totalPedidosAtendidos > 0) {
            System.out.println("Tiempo medio de espera   : " +
                (tiempoTotalDeEspera / (double) totalPedidosAtendidos) + " minutos");
        }
        System.out.println("Comparaciones totales    : " + totalComparaciones);
        System.out.println("========================================");
    }
}

