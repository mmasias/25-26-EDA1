import java.util.Random;

public class Restaurante {

    public static void main(String[] args) {

        final int JORNADA = 240;
        final double PROB_LLEGADA = 0.40;

        Random rnd = new Random();

        ColaPedidos cola = new ColaPedidos(500);

        Pedido cocinando = null;
        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;

        for (int minuto = 1; minuto <= JORNADA; minuto++) {

            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            boolean haLlegado = false;

            if (rnd.nextDouble() < PROB_LLEGADA) {
                Pedido p = generarPedido(minuto, rnd);
                cola.insertar(p);
                System.out.println("Llega pedido: " + p);
                haLlegado = true;
            }

            if (!haLlegado) {
                System.out.println("No llega ningún pedido");
            }

            if (cocinando == null && !cola.estaVacia()) {
                cocinando = cola.extraerMin();
                cocinando.setInicio(minuto);
                tiempoTotalEspera += cocinando.getEspera();
            }

            System.out.println("COLA: " + cola.size() + " pedidos");

            if (cocinando != null) {
                System.out.println("Cocinero: [" + cocinando.getTipoTexto()
                        + " - " + cocinando.getRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: [Libre]");
            }

            if (cocinando != null) {
                cocinando.reducirTiempo();

                if (cocinando.getRestante() <= 0) {
                    pedidosAtendidos++;
                    cocinando = null;
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("RESUMEN FINAL");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Pedidos pendientes: " + (cola.size() + (cocinando != null ? 1 : 0)));
        System.out.println("Tiempo total de espera: " + tiempoTotalEspera + " minutos");

        if (pedidosAtendidos > 0)
            System.out.println("Tiempo medio de espera: " +
                    (double) tiempoTotalEspera / pedidosAtendidos + " minutos");

        System.out.println("Comparaciones realizadas: " + cola.comparaciones);
    }

    private static Pedido generarPedido(int minuto, Random rnd) {

        Pedido.Tipo tipo = Pedido.Tipo.values()[rnd.nextInt(5)];
        int tiempo;

        switch (tipo) {
            case BEBIDA -> tiempo = 1 + rnd.nextInt(2);
            case CAFE -> tiempo = 2 + rnd.nextInt(2);
            case COLACAO -> tiempo = 2 + rnd.nextInt(3);
            case BOCADILLO -> tiempo = 3 + rnd.nextInt(3);
            default -> tiempo = 5 + rnd.nextInt(4);
        }

        return new Pedido(tipo, tiempo, minuto);
    }
}
