import java.util.Scanner;

public class Restaurante {
    private ArbolPedidos colaPedidos;
    private Pedido pedidoEnCocina;
    private int tiempoCoccionRestante;
    private int totalPedidosAtendidos;
    private Scanner entradaUsuario;
    
    private final String[] NOMBRES_PLATOS = {"Café", "Bocadillo", "Pasta", "Pizza"};
    private final int[] TIEMPOS_PREPARACION = {2, 5, 10, 15};

    public Restaurante() {
        this.colaPedidos = new ArbolPedidos();
        this.entradaUsuario = new Scanner(System.in);
        this.totalPedidosAtendidos = 0;
        this.pedidoEnCocina = null;
        this.tiempoCoccionRestante = 0;
    }

    public void iniciarSimulacion() {
        int minutoActual = 1;
        
        while (minutoActual <= 720) {
            
            System.out.println("\n--- MINUTO " + minutoActual + " ---");

            if (Math.random() < 0.4) {
                Pedido nuevoPedido = generarPedidoAleatorio();
                System.out.println(">>> LLEGADA: " + nuevoPedido.getNombre() + " (" + nuevoPedido.getTiempoPreparacion() + " min)");
                
                if (pedidoEnCocina == null) {
                    pedidoEnCocina = nuevoPedido;
                    tiempoCoccionRestante = nuevoPedido.getTiempoPreparacion();
                } else {
                    colaPedidos.insertar(nuevoPedido);
                }
            }

            procesarCocina();

            colaPedidos.actualizarTiemposDeEspera();

            mostrarEstado();

            gestionarInputUsuario();
            
            minutoActual++;
        }
        System.out.println("Fin de la jornada. Total atendidos: " + totalPedidosAtendidos);
    }

    private void procesarCocina() {
        if (pedidoEnCocina != null) {
            tiempoCoccionRestante--;
            pedidoEnCocina.incrementarTiempoEspera(); 

            if (tiempoCoccionRestante == 0) {
                System.out.println(">>> TERMINADO: " + pedidoEnCocina.getNombre());
                totalPedidosAtendidos++;
                
                if (colaPedidos.estaVacio()) {
                    pedidoEnCocina = null;
                } else {
                    pedidoEnCocina = colaPedidos.extraerMinimo();
                    tiempoCoccionRestante = pedidoEnCocina.getTiempoPreparacion();
                    System.out.println(">>> ENTRA A COCINA: " + pedidoEnCocina.getNombre());
                }
            }
        }
    }

    private void mostrarEstado() {
        if (pedidoEnCocina != null) {
            System.out.println("COCINA: [" + pedidoEnCocina.getNombre() + "] - Restan " + tiempoCoccionRestante + " min.");
        } else {
            System.out.println("COCINA: [LIBRE]");
        }
        System.out.println("EN COLA: " + colaPedidos.getCantidad() + " pedidos.");
    }

    private void gestionarInputUsuario() {
        System.out.println("[ENTER] Avanzar un minuto | [A] Ver Árbol de Pedidos");
        String entrada = entradaUsuario.nextLine();

        if (entrada.equalsIgnoreCase("A")) {
            System.out.println("---------------------------------");
            System.out.println("ESTADO DEL ÁRBOL (Nivel por Nivel)");
            colaPedidos.imprimirArbolIterativo();
            System.out.println("---------------------------------");
            gestionarInputUsuario(); 
        }
    }

    private Pedido generarPedidoAleatorio() {
        int indice = (int)(Math.random() * NOMBRES_PLATOS.length);
        return new Pedido(NOMBRES_PLATOS[indice], TIEMPOS_PREPARACION[indice]);
    }
}