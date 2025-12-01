public class Simulador {
    
    private static final int DURACION_MIN = 60;
    private static final int DURACION_MAX = 120;
    
    private Arbol cola;
    private Cocinero cocinero;
    private GeneradorPedidos generador;
    private Estadisticas estadisticas;
    private int duracionJornada;
    
    public Simulador() {
        this.cola = new Arbol();
        this.cocinero = new Cocinero();
        this.generador = new GeneradorPedidos();
        this.estadisticas = new Estadisticas();
        this.duracionJornada = DURACION_MIN + (int)(Math.random() * (DURACION_MAX - DURACION_MIN + 1));
    }
    
    public void ejecutar() {
        System.out.println("Duración de la jornada: " + duracionJornada + " minutos\n");
        
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            System.out.println("========================================");
            System.out.printf("[%d]%n", minuto);
            
            if (generador.hayLlegada()) {
                Pedido nuevoPedido = generador.generar(minuto);
                cola.insertar(nuevoPedido);
                estadisticas.registrarLlegada();
                System.out.printf("Llega pedido: %s (%d min)%n", 
                    nuevoPedido.getTipo(), nuevoPedido.getTiempoPreparacion());
            }
            
            if (cocinero.tienePedidoActual()) {
                cocinero.procesar();
                
                if (cocinero.haTerminado()) {
                    estadisticas.registrarCompletado();
                    cocinero.liberarPedido();
                }
            }
            
            if (!cocinero.tienePedidoActual() && !cola.estaVacio()) {
                Pedido siguientePedido = cola.extraerMinimo();
                cocinero.tomarPedido(siguientePedido);
            }
            
            int pedidosEnCola = cola.contarPendientes();
            System.out.printf("COLA: %d pedidos%n", pedidosEnCola);
            
            if (cocinero.tienePedidoActual()) {
                System.out.printf("Cocinero: [%s]%n", cocinero.getPedidoActual());
            } else {
                System.out.println("Cocinero: [Inactivo]");
            }
            
            estadisticas.acumularTiempoEspera(pedidosEnCola);
        }
        
        estadisticas.mostrarResumen(cola.getContadorComparaciones());
    }
    
    public static void main(String[] args) {
        System.out.println("=== SIMULACIÓN RCCCF - RESTAURANTE ===\n");
        Simulador simulador = new Simulador();
        simulador.ejecutar();
    }
}