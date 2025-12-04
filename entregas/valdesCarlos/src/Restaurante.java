import java.util.Random;

public class Restaurante {
    private ArbolPedidos colaCocina; 
    private Pedido pedidoEnPreparacion; 

    private final String[] TIPOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final int[][] TIEMPOS = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};
    private Random rand = new Random();

    public int pedidosAtendidos = 0;
    public int tiempoEsperaTotal = 0;

    public Restaurante() {
        this.colaCocina = new ArbolPedidos();
        this.pedidoEnPreparacion = null;
    }

    public void recibirPosiblePedido(int minutoActual) {
        if (rand.nextDouble() < 0.4) {
            int i = rand.nextInt(TIPOS.length);
            
            
            assert TIEMPOS[i][1] >= TIEMPOS[i][0] : "Error en matriz de tiempos";

            int tiempo = rand.nextInt(TIEMPOS[i][1] - TIEMPOS[i][0] + 1) + TIEMPOS[i][0];
            
            Pedido nuevo = new Pedido(TIPOS[i], tiempo, minutoActual);
            colaCocina.insertar(nuevo);
            
            System.out.println("[" + minutoActual + ".0] Llega pedido: " + nuevo.descripcionCompleta());
        }
    }

    public void gestionarCocina(int minutoActual) {
        
        if (pedidoEnPreparacion == null && !colaCocina.estaVacio()) {
            pedidoEnPreparacion = colaCocina.extraerPrioritario();
            
            
            
            assert pedidoEnPreparacion != null : "Cola no vacía devolvió null";

            tiempoEsperaTotal += (minutoActual - pedidoEnPreparacion.minutoLlegada);
        }

        
        if (pedidoEnPreparacion != null) {
            
            assert pedidoEnPreparacion.tiempoRestante > 0 : "Error: Procesando pedido ya terminado";
            
            pedidoEnPreparacion.tiempoRestante--;
            
            if (pedidoEnPreparacion.tiempoRestante == 0) {
                pedidosAtendidos++;
                pedidoEnPreparacion = null;
            }
        }
    }

    public void imprimirEstado(int minuto) {
        System.out.println("----------------------------------------");
        System.out.println("Hora: " + minuto);
        System.out.println("COLA: " + colaCocina.cantidadPedidos + " pedidos");
        
        
        assert colaCocina.cantidadPedidos >= 0 : "Error: Cantidad negativa de pedidos en cola";

        if (pedidoEnPreparacion != null) {
            System.out.println("Cocinero: [" + pedidoEnPreparacion + "]");
        } else {
            System.out.println("Cocinero: [LIBRE]");
        }
    }

    public void imprimirResumenFinal() {
        System.out.println("\n========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + colaCocina.cantidadPedidos);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " min");
        double media = (pedidosAtendidos == 0) ? 0 : (double) tiempoEsperaTotal / pedidosAtendidos;
        System.out.printf("Tiempo medio de espera   : %.2f min\n", media);
        System.out.println("Comparaciones (Arbol)    : " + colaCocina.comparaciones);
        System.out.println("========================================");
    }
}