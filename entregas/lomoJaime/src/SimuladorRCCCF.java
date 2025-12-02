public class SimuladorRCCCF {
    private Cocina cocina;
    private ContextoSimulacion contexto;

    public SimuladorRCCCF() {
        this.contexto = new ContextoSimulacion();
        this.cocina = new Cocina(1, 200);
    }

    public void ejecutar() {
        for (int minuto = 1; minuto <= contexto.getDuracionDeLaJornadaEnMinutos(); minuto++) {
            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            cocina.procesaMinuto(minuto, contexto);

            System.out.println("COLA: " + cocina.getNumeroDePedidosEnCola() + " pedidos");

            Cocinero c = cocina.getCocineros()[0];
            if (c.getPedidoActual() != null) {
                Pedido p = c.getPedidoActual();
                System.out.println("Cocinero: [" + p.getTipo() + " - " + p.getTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: [Libre]");
            }
        }

        mostrarResumenFinal();
    }

    private void mostrarResumenFinal() {
        System.out.println("\n\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + contexto.getNumeroDePedidosAtendidos());
        System.out.println("Pedidos pendientes       : " + cocina.getNumeroDePedidosEnCola());
        System.out.println("Tiempo total de espera   : " + contexto.getTiempoTotalDeEspera() + " minutos");
        if (contexto.getNumeroDePedidosAtendidos() > 0) {
            double media = (double) contexto.getTiempoTotalDeEspera() / contexto.getNumeroDePedidosAtendidos();
            System.out.printf("Tiempo medio de espera   : %.1f minutos%n", media);
        } else {
            System.out.println("Tiempo medio de espera   : 0.0 minutos");
        }
        System.out.println("Comparaciones totales    : " + contexto.getTotalDeComparaciones());
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        new SimuladorRCCCF().ejecutar();
    }
}