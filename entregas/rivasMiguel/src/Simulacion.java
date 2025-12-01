public class Simulacion {

    private final int duracion;
    private Cocinero cocinero;
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;

    public Simulacion(int duracionMinutos) {
        this.duracion = duracionMinutos;
        this.cocinero = new Cocinero();
    }

    public void iniciar() {
        for (int minuto = 1; minuto <= duracion; minuto++) {

            if (Main.rnd.nextDouble() < 0.40) {
                Pedido pedido = new Pedido(Pedido.Tipo.values()[Main.rnd.nextInt(5)]);
                pedido.setTiempoLlegada(minuto);
                cocinero.agregarPedido(pedido);

                System.out.println("[Min " + minuto + "] Llega pedido: " +
                        pedido.getTipo() + " (" + pedido.getTiempoPreparacion() + " min)");
            }

            Pedido actual = cocinero.getPedidoActual();
            if (actual != null) {
                if (actual.getTiempoRestante() == 1) {
                    pedidosAtendidos++;
                    tiempoEsperaTotal += minuto - actual.getTiempoLlegada();
                }
            }

            cocinero.procesarMinuto();

            System.out.println("COLA: " + cocinero.getPedidosPendientes() + " pedidos");
            Pedido p = cocinero.getPedidoActual();
            System.out.println("Cocinero: " + (p == null ? "[libre]" : "[" + p + "]"));
            System.out.println("------------------------------------");
        }

        mostrarResumen();
    }

    private void mostrarResumen() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("=====================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cocinero.getPedidosPendientes());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal);
        System.out.println("Tiempo medio de espera   : " +
                (pedidosAtendidos == 0 ? 0 : (double) tiempoEsperaTotal / pedidosAtendidos));
        System.out.println("Comparaciones totales    : " + cocinero.getComparacionesTotales());
        System.out.println("=====================================");
    }

    public int getPedidosAtendidos() { return pedidosAtendidos; }
    public int getPedidosPendientes() { return cocinero.getPedidosPendientes(); }
    public int getTiempoTotalEspera() { return tiempoEsperaTotal; }
    public double getTiempoMedioEspera() { return (pedidosAtendidos == 0 ? 0 : (double) tiempoEsperaTotal / pedidosAtendidos); }
    public int getComparacionesTotales() { return cocinero.getComparacionesTotales(); }
}
