import java.util.Random;

public class Simulation {

    private final int duracionJornada;
    private final double probLlegada;
    private int tiempoActual;
    private final Cocina cocina;
    private final Estadisticas estadisticas;
    private final Random random;
    private int siguienteId;

    public Simulation(int duracionJornada, double probLlegada, IEstructuraPedidos estructura) {
        this.duracionJornada = duracionJornada;
        this.probLlegada = probLlegada;
        this.tiempoActual = 0;
        this.cocina = new Cocina(estructura);
        this.estadisticas = new Estadisticas();
        this.random = new Random();
        this.siguienteId = 1;
    }

    public void iniciar() {
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            tiempoActual = minuto;
            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            boolean llegaPedido = random.nextDouble() < probLlegada;
            if (llegaPedido) {
                Plato plato = Plato.aleatorio();
                int tiempoPrep = plato.tiempoAleatorio();
                Pedido p = new Pedido(siguienteId++, plato, tiempoPrep, minuto);
                cocina.recibirPedido(p, estadisticas);
                System.out.println("Llega pedido: " + plato.getNombre() + " (" + tiempoPrep + " min)");
            }

            if (cocina.getPedidoActual() == null) {
                cocina.seleccionarSiguientePedido(minuto, estadisticas);
            }

            System.out.println("COLA: " + cocina.getNumeroPedidosEnCola() + " pedidos");

            Pedido actual = cocina.getPedidoActual();
            if (actual != null) {
                System.out.println("Cocinero: [" + actual.getPlato().getNombre()
                        + " - " + actual.getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: [Libre]");
            }

            cocina.procesarMinuto(minuto, estadisticas);
        }

        int pendientes = cocina.calcularPedidosPendientesAlFinal();
        estadisticas.setPedidosPendientes(pendientes);

        System.out.println();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + estadisticas.getPedidosAtendidos());
        System.out.println("Pedidos pendientes       : " + estadisticas.getPedidosPendientes());
        System.out.println("Tiempo total de espera   : " + estadisticas.getTiempoTotalEspera() + " minutos");
        System.out.println("Tiempo medio de espera   : " + String.format("%.1f", estadisticas.getTiempoMedioEspera()) + " minutos");
        System.out.println("Comparaciones totales    : " + estadisticas.getComparacionesTotales());
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        IEstructuraPedidos estructura = new ColaPrioridadPedidos();
        Simulation simulation = new Simulation(120, 0.4, estructura);
        simulation.iniciar();
    }
}


