
public class Restaurante {

    private final EstructuraPedidos colaPedidos;
    private final Cocinero cocinero;
    private final GeneradorAleatorio random;
    private final int tiempoSimulacion;

    private int pedidosGenerados = 0;
    private int pedidosAtendidos = 0;
    private long sumaTiemposEspera = 0;

    public Restaurante(int tiempoSimulacion, long semilla) {
        assert tiempoSimulacion > 0 : "El tiempo de simulación debe ser positivo";

        this.colaPedidos = new ArbolPedidos();
        this.cocinero = new Cocinero();
        this.random = new GeneradorAleatorio(semilla);
        this.tiempoSimulacion = tiempoSimulacion;
    }

    public void ejecutar() {
        System.out.println("Iniciando simulación (" + tiempoSimulacion + " minutos)...");

        for (int minuto = 1; minuto <= tiempoSimulacion; minuto++) {
            simularMinuto(minuto);
        }

        imprimirEstadisticas();
    }

    private void simularMinuto(int minuto) {
        if (random.probabilidad(0.5)) {
            int tiempoPrep = random.siguienteEntero(3, 15);
            Pedido nuevo = new Pedido(tiempoPrep, minuto);
            colaPedidos.insertar(nuevo);
            pedidosGenerados++;
            System.out.println("[Min " + minuto + "] Nuevo pedido: " + nuevo);
        }

        if (cocinero.estaLibre() && !colaPedidos.estaVacia()) {
            Pedido siguiente = colaPedidos.extraerMinimo();
            cocinero.asignarPedido(siguiente);
        }

        Pedido terminado = cocinero.cocinar();

        if (terminado != null) {
            pedidosAtendidos++;
            int espera = minuto - terminado.getLlegadaMinuto();
            sumaTiemposEspera += espera;
            System.out.println("   -> [Min " + minuto + "] Completado: " + terminado + " (Espera total: " + espera + " min)");
        }
    }

    private void imprimirEstadisticas() {
        System.out.println("\n=== RESULTADOS FINALES ===");
        System.out.println("Pedidos generados: " + pedidosGenerados);
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);

        int pendientes = colaPedidos.tamano() + (cocinero.estaLibre() ? 0 : 1);
        System.out.println("Pedidos pendientes: " + pendientes);

        System.out.println("Comparaciones en Árbol (Eficiencia): " + colaPedidos.getComparaciones());

        double promedio = (pedidosAtendidos > 0) ? (double) sumaTiemposEspera / pedidosAtendidos : 0.0;
        System.out.printf("Tiempo de espera promedio: %.2f minutos%n", promedio);
    }
}
