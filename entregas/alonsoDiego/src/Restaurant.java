public class Restaurant {

    private final int DURACION_JORNADA = 720;
    private final double PROBABILIDAD_LLEGADA = 0.4;
    private final int INDICE_MIN = 0;
    private final int INDICE_MAX = 1;

    private ArbolPedidos cola;
    private Pedido pedidoActual;
    
    private int pedidosAtendidos;
    private int tiempoTotalEspera;

    private final String[] NOMBRES_PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final int[][] RANGOS_TIEMPOS = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

    public Restaurant() {
        this.cola = new ArbolPedidos();
        this.pedidoActual = null;
        this.pedidosAtendidos = 0;
        this.tiempoTotalEspera = 0;
    }

    public void run() {
        System.out.println("=== APERTURA DEL RESTAURANTE (Modelo Árbol + Timestamp) ===");

        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            gestionarLlegada(minuto);
            gestionarCocina(minuto);
        }

        mostrarResumenFinal();
    }

    private void gestionarLlegada(int minutoActual) {
        if (Math.random() < PROBABILIDAD_LLEGADA) {
            Pedido nuevo = generarPedidoAleatorio(minutoActual);
            cola.insertar(nuevo);
            System.out.println("[" + minutoActual + "] Entra: " + nuevo.getNombrePlato() + " (" + nuevo.getTiempoPreparacion() + " min)");
        }
    }

    private void gestionarCocina(int minutoActual) {
        if (pedidoActual == null && !cola.isEmpty()) {
            pedidoActual = cola.extraerMinimo();
            System.out.println("   -> Cocinando: " + pedidoActual.getNombrePlato());
        }

        if (pedidoActual != null) {
            pedidoActual.restarMinuto();

            if (pedidoActual.estaTerminado()) {
                pedidosAtendidos++;
                
                int tiempoEnSistema = minutoActual - pedidoActual.getMinutoLlegada();
                int esperaEnCola = tiempoEnSistema - pedidoActual.getTiempoPreparacion();
                
                tiempoTotalEspera += esperaEnCola;
                
                pedidoActual = null;
            }
        }
    }

    private Pedido generarPedidoAleatorio(int minutoActual) {
        int i = (int)(Math.random() * NOMBRES_PLATOS.length);

        String nombre = NOMBRES_PLATOS[i];
        int min = RANGOS_TIEMPOS[i][INDICE_MIN];
        int max = RANGOS_TIEMPOS[i][INDICE_MAX];

        int duracion = (int)(Math.random() * (max - min + 1)) + min;

        return new Pedido(nombre, duracion, minutoActual);
    }

    private void mostrarResumenFinal() {
        System.out.println("\n=== CIERRE DE JORNADA ===");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Eficiencia del Árbol (Comparaciones): " + cola.getComparaciones());
        
        if (pedidosAtendidos > 0) {
            double mediaEspera = (double) tiempoTotalEspera / pedidosAtendidos;
            System.out.printf("Tiempo medio de espera: %.2f minutos\n", mediaEspera);
        }
    }
}