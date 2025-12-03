public class Restaurante {

    // --- Configuración ---
    private static final int DURACION = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.40;

    // --- Componentes ---
    private ColaDePrioridad cola;
    private Cocinero cocinero;
    private Vista vista;

    // --- Datos del Menú ---
    private final String[] PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final int[] MINS    = { 1,        2,      2,         3,           5 };
    private final int[] MAXS    = { 2,        3,      4,         5,           8 };

    // --- Estadísticas ---
    private int pedidosTotales = 0;
    private int tiempoTotalEspera = 0;

    public static void main(String[] args) {
        new Restaurante().run();
    }

    public Restaurante() {
        this.cola = new ColaDePrioridad();
        this.cocinero = new Cocinero();
        this.vista = new Vista();
    }

    public void run() {
        for (int minuto = 1; minuto <= DURACION; minuto++) {
            vista.mostrarTiempo(minuto);

            gestionarLlegadaCliente(minuto);
            gestionarCocina(minuto);
            
            vista.mostrarEstado(cola.getNumeroPedidos(), cocinero);
        }
        mostrarEstadisticas();
    }

    // --- Métodos Auxiliares (Detalles ocultos) ---

    private void gestionarLlegadaCliente(int minutoActual) {
        if (Math.random() < PROBABILIDAD_LLEGADA) {
            Pedido nuevo = generarPedidoAleatorio(minutoActual);
            cola.agregarPedido(nuevo);
            vista.mostrarLlegadaPedido(nuevo);
            pedidosTotales++;
        }
    }

    private void gestionarCocina(int minutoActual) {
        cocinero.trabajar();

        if (cocinero.estaLibre() && !cola.estaVacia()) {
            Pedido siguiente = cola.sacarPedidoPrioritario();
            cocinero.asignarPedido(siguiente);
            
            // Registramos la espera: (Ahora - Llegada)
            tiempoTotalEspera += (minutoActual - siguiente.getMinutoLlegada());
        }
    }

    private Pedido generarPedidoAleatorio(int minutoActual) {
        int i = (int) (Math.random() * PLATOS.length);
        int tiempo = (int) (Math.random() * (MAXS[i] - MINS[i] + 1)) + MINS[i];
        return new Pedido(PLATOS[i], tiempo, minutoActual);
    }

    private void mostrarEstadisticas() {
        int pendientes = cola.getNumeroPedidos();
        int atendidos = pedidosTotales - pendientes;
        double media = (atendidos > 0) ? (double) tiempoTotalEspera / atendidos : 0;

        vista.mostrarResumen(atendidos, pendientes, tiempoTotalEspera, media, cola.getComparacionesTotales());
    }
}