import java.util.Random;


public class SimulacionRCCCF {

  
    public static final int JORNADA_MINUTOS = 120;
    public static final double PROB_LLEGADA_PEDIDO = 0.40; // 40%

   
    private ColaPrioridadHeap cola;
    private Cocinero cocinero;
    private Estadisticas stats;
    private GeneradorPedidos generador;
    private Random random;

    public SimulacionRCCCF() {
        this.cola = new ColaPrioridadHeap(); 
        this.cocinero = new Cocinero();
        this.stats = new Estadisticas();
        this.generador = new GeneradorPedidos();
        this.random = new Random();
    }

 
    public void runSimulacion() {
        System.out.println("--- INICIO DE LA JORNADA RCCCF (Versión Heap) ---");

        for (int minuto = 1; minuto <= JORNADA_MINUTOS; minuto++) {
            System.out.println("========================================");
            System.out.println("[" + minuto + ".0]");

            if (random.nextDouble() < PROB_LLEGADA_PEDIDO) {
                Pedido nuevoPedido = generador.generar(minuto);
               
                cola.agregar(nuevoPedido, stats); 
                System.out.println("Llega pedido: " + nuevoPedido);
            }

           
            if (cocinero.estaLibre()) {
               
                Pedido proximoPedido = cola.extraerMinimo(stats);
                
                if (proximoPedido != null) {
                   
                    cocinero.asignarPedido(proximoPedido);
                }
            } else {
                
                Pedido pedidoTerminado = cocinero.procesarMinuto();
                
                if (pedidoTerminado != null) {
                   
                    stats.registrarPedidoTerminado(minuto, pedidoTerminado);
                    
                    Pedido proximoPedido = cola.extraerMinimo(stats);
                    if (proximoPedido != null) {
                        cocinero.asignarPedido(proximoPedido);
                    }
                }
            }

            imprimirEstado();
        }

        imprimirResumen();
    }


    private void imprimirEstado() {
        System.out.println("COLA: " + cola.getTamaño() + " pedidos");
        if (cocinero.estaLibre()) {
            System.out.println("Cocinero: LIBRE");
        } else {
            System.out.println("Cocinero: [" + cocinero.getPedidoActual().getTipo() + 
                               " - " + cocinero.getTiempoRestante() + " min restantes]");
        }
    }

 
    private void imprimirResumen() {
        System.out.println("========================================");
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + stats.pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cola.getTamaño());
        System.out.println("Tiempo total de espera   : " + stats.tiempoTotalEspera + " minutos");
        System.out.println(String.format("Tiempo medio de espera   : %.2f minutos", stats.getTiempoMedioEspera()));
        System.out.println("Comparaciones totales    : " + stats.comparacionesTotales);
        System.out.println("========================================");
    }


    public static void main(String[] args) {
        SimulacionRCCCF simulacion = new SimulacionRCCCF();
        simulacion.runSimulacion();
    }
}
