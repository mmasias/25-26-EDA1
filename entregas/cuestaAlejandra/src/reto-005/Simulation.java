public class Simulation {
    public static void main(String[] args) {
        System.out.println("========================================");
        Restaurante r = new Restaurante();
        final int DURACION = 120;

        for (int min = 1; min <= DURACION; min++) {
            System.out.println("[" + min + ".0]");
            r.avanzarMinuto(min);
            if (min < DURACION) {
                System.out.println("========================================");
            }
        }

        r.cerrar(DURACION);

        int total = r.pedidosAtendidos + r.tamanoCola();
        double media = total > 0 ? (double) r.tiempoTotalEspera / total : 0.0;

        System.out.println();
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + r.pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + r.tamanoCola());
        System.out.println("Tiempo total de espera   : " + r.tiempoTotalEspera + " minutos");
        System.out.printf("Tiempo medio de espera   : %.1f minutos%n", media);
        System.out.println("Comparaciones totales    : " + r.getComparaciones());
        System.out.println("========================================");
    }
}