
public class Restaurant {

    private final ArbolPedidos ARBOL;

    private final int DURACION = 120;
    private final double PROBABILIDAD = 0.40;
    private final String[] TIPOS = {"Café", "Bocadillo", "Ensalada", "Colacao", "Cocido"};
    private final int[][] TIEMPOS = {{2, 3}, {3, 5}, {5, 8}, {2, 4}, {20, 25}};

    private Pedido enCocina;
    private int tiempoRestanteCoccion;

    private int atendidos;
    private int esperaAcumulada;

    public Restaurant() {
        this.ARBOL = new ArbolPedidos();
        this.enCocina = null;
        this.tiempoRestanteCoccion = 0;
        this.atendidos = 0;
        this.esperaAcumulada = 0;
    }

    public void run() {
        for (int minuto = 1; minuto <= DURACION; minuto++) {
            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            if (Math.random() < PROBABILIDAD) {
                generarPedido(minuto);
            }

            if (this.enCocina == null && !this.ARBOL.estaVacio()) {
                this.enCocina = this.ARBOL.extraerMinimo();
                this.tiempoRestanteCoccion = this.enCocina.getTiempoPreparacion();

                this.esperaAcumulada += (minuto - this.enCocina.getMinutoLlegada());
            }

            System.out.println("COLA: " + this.ARBOL.getTamano() + " pedidos");
            imprimirEstadoCocinero();

            if (this.enCocina != null) {
                this.tiempoRestanteCoccion--;
                if (this.tiempoRestanteCoccion <= 0) {
                    this.atendidos++;
                    this.enCocina = null;
                }
            }
        }
        mostrarResumen();
    }

    private void generarPedido(int minuto) {
        int i = (int) (Math.random() * TIPOS.length);
        int t = TIEMPOS[i][0] + (int) (Math.random() * (TIEMPOS[i][1] - TIEMPOS[i][0] + 1));

        Pedido nuevo = new Pedido(TIPOS[i], t, minuto);
        this.ARBOL.insertar(nuevo);

        System.out.println("Llega pedido: " + nuevo.getTipo() + " (" + t + " min)");
    }

    private void imprimirEstadoCocinero() {
        if (this.enCocina != null) {
            System.out.println("Cocinero: [" + this.enCocina.getTipo() + " - " + this.tiempoRestanteCoccion + " min restantes]");
        } else {
            System.out.println("Cocinero: [Esperando...]");
        }
    }

    private void mostrarResumen() {
        int pendientes = this.ARBOL.getTamano() + (this.enCocina != null ? 1 : 0);
        double media = this.atendidos > 0 ? (double) this.esperaAcumulada / this.atendidos : 0;

        System.out.println("========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + this.atendidos);
        System.out.println("Pedidos pendientes       : " + pendientes);
        System.out.println("Tiempo total de espera   : " + this.esperaAcumulada + " minutos");
        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", media);
        System.out.println("Comparaciones totales    : " + this.ARBOL.getComparaciones());
        System.out.println("========================================");
    }
}
