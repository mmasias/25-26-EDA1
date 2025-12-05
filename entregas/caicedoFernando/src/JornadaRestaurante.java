package entregas.caicedoFernando.src;
import java.util.Random;
import java.util.Scanner;

public class JornadaRestaurante {
    private final int APERTURA = 9 * 60;
    private final int CIERRE = 21 * 60;
    private final double PROBABILIDAD = 0.4;
    private final String[] TIPOS = {"Bebida", "Café", "Bocadillo", "Ensalada"};
    private final int[][] TIEMPOS = {{1, 2}, {2, 3}, {3, 5}, {5, 8}};

    private final ColaPedidos arbolPedidos;
    private final Cocinero cocinero;
    private final Random random;
    
    private int atendidos = 0;
    private int esperaTotal = 0;
    private int comparaciones = 0;
    private int generados = 0;

    public JornadaRestaurante() {
        this.arbolPedidos = new ColaPedidos();
        this.cocinero = new Cocinero();
        this.random = new Random();
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean enEjecucion = true;

         for (int minuto = 0; minuto < (CIERRE - APERTURA) && enEjecucion; minuto++) {
            imprimirEstado(minuto);
            
            if (random.nextDouble() < PROBABILIDAD) {
                Pedido pedido = fabricarPedido(minuto);
                arbolPedidos.agregar(pedido);
                generados++;
                System.out.println("--> Nuevo pedido: " + pedido.tipo + " (" + pedido.tiempoPreparacion + " min)");
            }

            if (!cocinero.estaLibre()) {
                if (cocinero.trabajar() != null) atendidos++;
            }

            if (cocinero.estaLibre() && !arbolPedidos.estaVacia()) {
                ResultadoExtraccion resultado = arbolPedidos.extraerMinimo();
                cocinero.tomarPedido(resultado.pedidoMinimo);
                comparaciones += resultado.comparaciones;
            }

            esperaTotal += arbolPedidos.obtenerTamano();

            if (!esperarEnter(scanner)) {
                enEjecucion = false;
            }
        }
        imprimirResumen();
    }

    private boolean esperarEnter(Scanner scanner) {
        System.out.print("Presiona ENTER para avanzar (o 'Q' para salir)...");
        String entrada = scanner.nextLine().trim();
        
        if (entrada.length() > 0) {
            char letra = entrada.charAt(0); 
            if (letra == 'q' || letra == 'Q') { 
                return false;
            }
        }
        return true;
    }

    private Pedido fabricarPedido(int minuto) {
        int i = random.nextInt(TIPOS.length);
        int t = random.nextInt(TIEMPOS[i][1] - TIEMPOS[i][0] + 1) + TIEMPOS[i][0];
        return new Pedido(TIPOS[i], t, minuto);
    }

    private void imprimirEstado(int m) {
        System.out.print("[" + m + "] Cola: " + arbolPedidos.obtenerTamano() + " | Cocina: ");
        System.out.println(cocinero.estaLibre() ? "Libre" : cocinero.obtenerPedidoActual().tipo);
    }

    private void imprimirResumen() {
        System.out.println("\n=== RESUMEN ===");
        System.out.println("Atendidos: " + atendidos);
        System.out.println("Promedio espera: " + (generados > 0 ? (esperaTotal / (double)generados) : 0));
        System.out.println("Comparaciones: " + comparaciones);
    }
}