import java.util.Scanner;
public class Restaurante {

    private ArbolPedidos colaPedidos;
    private Pedido pedidoEnCocina;
    private int tiempoCoccionRestante;
    private int totalPedidosAtendidos;
    private Scanner entradaUsuario;

    private final Plato[] PLATOS = {
        new Plato("Bebida", 1, 2),
        new Plato("Cafe", 2, 3),
        new Plato("Colacao", 2, 4),
        new Plato("Bocadillo", 3, 5),
        new Plato("Ensalada", 5, 8)
    };

    public Restaurante() {
        this.colaPedidos = new ArbolPedidos();
        this.entradaUsuario = new Scanner(System.in);
    }

    public void iniciarSimulacion() {
        for (int minuto = 1; minuto <= 720; minuto++) {

            System.out.println("\n--- MINUTO " + minuto + " ---");

            // 40% probabilidad de llegada
            if (Math.random() < 0.4) {
                Pedido nuevo = generarPedidoAleatorio();
                System.out.println(">>> LLEGA: " + nuevo.getNombre() +
                        " (" + nuevo.getTiempoPreparacion() + " min)");

                if (pedidoEnCocina == null) {
                    iniciarCocinaCon(nuevo);
                } else {
                    colaPedidos.insertar(nuevo);
                }
            }

            procesarCocina();
            colaPedidos.actualizarTiemposDeEspera();
            mostrarEstado();
            gestionarInput();
        }

        System.out.println("Jornada terminada. Atendidos: " + totalPedidosAtendidos);
    }

    private void iniciarCocinaCon(Pedido p) {
        pedidoEnCocina = p;
        tiempoCoccionRestante = p.getTiempoPreparacion();
    }

    private void procesarCocina() {
        if (pedidoEnCocina == null) return;

        tiempoCoccionRestante--;
        pedidoEnCocina.incrementarTiempoEspera();

        if (tiempoCoccionRestante == 0) {
            System.out.println(">>> TERMINADO: " + pedidoEnCocina.getNombre());
            totalPedidosAtendidos++;

            if (!colaPedidos.estaVacio()) {
                pedidoEnCocina = colaPedidos.extraerMinimo();
                tiempoCoccionRestante = pedidoEnCocina.getTiempoPreparacion();
                System.out.println(">>> ENTRA A COCINA: " + pedidoEnCocina.getNombre());
            } else {
                pedidoEnCocina = null;
            }
        }
    }

    private Pedido generarPedidoAleatorio() {
        int i = (int)(Math.random() * PLATOS.length);
        return new Pedido(PLATOS[i]);
    }

    private void mostrarEstado() {
        if (pedidoEnCocina == null)
            System.out.println("COCINA: [LIBRE]");
        else
            System.out.println("COCINA: [" + pedidoEnCocina.getNombre() + "] - Restan "
                    + tiempoCoccionRestante + " min");

        System.out.println("EN COLA: " + colaPedidos.getCantidad());
    }

    private void gestionarInput() {
        System.out.println("[ENTER] Avanzar | [A] Ver Árbol");
        String entrada = entradaUsuario.nextLine();

        if (entrada.equalsIgnoreCase("A")) {
            System.out.println("------ ÁRBOL DE PEDIDOS ------");
            colaPedidos.imprimirArbolIterativo();
            System.out.println("------------------------------");
            gestionarInput();
        }
    }
}
