package entregas.caicedoFernando.src;

import java.util.Random;

public class JornadaRestaurante {

    private final double HORA_APERTURA = 9.0;
    private final double HORA_CIERRE = 21.0;
    private final double PASO_MINUTO = 1.0 / 60.0;
    private final double PROBABILIDAD_LLEGADA = 0.4;
    private final String[] TIPOS_PLATO = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final int[][] RANGOS_TIEMPO = {
        {1, 2}, 
        {2, 3}, 
        {2, 4}, 
        {3, 5}, 
        {5, 8}  
    };

    private final ColaPedidos colaPedidos;
    private final Cocinero cocinero;
    private final Random generadorAleatorio;
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;
    private int comparacionesTotales;
    private int totalPedidosGenerados;

    public JornadaRestaurante() {
        this.colaPedidos = new ColaPedidos();
        this.cocinero = new Cocinero();
        this.generadorAleatorio = new Random();
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
        this.comparacionesTotales = 0;
        this.totalPedidosGenerados = 0;
    }

    public void ejecutar() {
        int minutoActual = 1;

        for (double tiempoActual = HORA_APERTURA; tiempoActual < HORA_CIERRE; tiempoActual = tiempoActual + PASO_MINUTO) {
            imprimirEstadoMinuto(minutoActual);
            gestionarLlegadas(minutoActual);
            procesarCocina();
            tiempoEsperaTotal += colaPedidos.obtenerTamano();
            minutoActual++;
        }
        imprimirResumenFinal();
    }

    private void gestionarLlegadas(int minuto) {
        if (generadorAleatorio.nextDouble() < PROBABILIDAD_LLEGADA) {
            Pedido nuevoPedido = generarPedido(minuto);
            colaPedidos.agregar(nuevoPedido);
            totalPedidosGenerados++;
            System.out.println("Llega pedido: " + nuevoPedido.tipo + " (" + nuevoPedido.tiempoPreparacion + " min)");
        }
    }

    private void procesarCocina() {
        Pedido pedidoTerminado = null;
        if (!cocinero.estaLibre()) {
            pedidoTerminado = cocinero.trabajar();
            if (pedidoTerminado != null) {
                pedidosAtendidos++;
            }
        }

        if (cocinero.estaLibre() && !colaPedidos.estaVacia()) {
            ResultadoExtraccion resultado = colaPedidos.extraerMinimo();
            cocinero.tomarPedido(resultado.pedidoMinimo);
            comparacionesTotales += resultado.comparaciones;
        }
    }

    private void imprimirEstadoMinuto(int minuto) {
        System.out.println("========================================");
        System.out.println("[" + minuto + ".0]");
        
        System.out.println("COLA: " + colaPedidos.obtenerTamano() + " pedidos");
        if (cocinero.estaLibre()) {
            System.out.println("Cocinero: No tiene platos por preparar!!!");
        } else {
            Pedido pedidoActual = cocinero.obtenerPedidoActual();
            System.out.println("Cocinero: [" + pedidoActual.tipo + " - " + pedidoActual.tiempoRestante + " minutos restantes]");
        }
    }

    private Pedido generarPedido(int minutoLlegada) {
        int i = generadorAleatorio.nextInt(TIPOS_PLATO.length);
        String tipo = TIPOS_PLATO[i];
        
        int tiempoMinimo = RANGOS_TIEMPO[i][0];
        int tiempoMaximo = RANGOS_TIEMPO[i][1];
        int tiempo = generadorAleatorio.nextInt(tiempoMaximo - tiempoMinimo + 1) + tiempoMinimo;

        return new Pedido(tipo, tiempo, minutoLlegada);
    }

    private void imprimirResumenFinal() {
        System.out.println("========================================");
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + colaPedidos.obtenerTamano());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        
        double tiempoMedio = 0;
        if (totalPedidosGenerados > 0) {
             tiempoMedio = (double) tiempoEsperaTotal / totalPedidosGenerados;
        }
        
        double tiempoMedioRedondeado = Math.round(tiempoMedio * 10.0) / 10.0;
        
        System.out.println("Tiempo medio de espera   : " + tiempoMedioRedondeado + " minutos");
        System.out.println("Comparaciones totales    : " + comparacionesTotales);
        System.out.println("========================================");
    }
}
