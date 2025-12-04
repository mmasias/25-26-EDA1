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

        assert PLATOS.length > 0 : "La lista de platos no puede estar vacía";
    }

    public void iniciarSimulacion() {
        int JORNADA = 720; 
        int TIEMPO_VISUALIZACION = 1;
        double PROBABILIDAD_NUEVO_PEDIDO = 0.40;

        for (int minuto = TIEMPO_VISUALIZACION; minuto <= JORNADA; minuto++) {

            System.out.println("\n--- MINUTO " + minuto + " ---");

            if (Math.random() < PROBABILIDAD_NUEVO_PEDIDO ) {
                Pedido nuevo = generarPedidoAleatorio();
                assert nuevo != null : "Error generando nuevo pedido";

                System.out.println(">>> LLEGA: " + nuevo.getNombre() +
                        " (" + nuevo.getTiempoPreparacion() + " min)");

                if (pedidoEnCocina == null) {
                    iniciarCocinaCon(nuevo);
                } else {
                    colaPedidos.insertar(nuevo);
                }
            }

            procesarCocina();
            colaPedidos.actualizarTiemposDeEspera(pedidoEnCocina);
            mostrarEstado();
            gestionarInput();
        }

        System.out.println("Jornada terminada. Atendidos: " + totalPedidosAtendidos);
    }

    private void iniciarCocinaCon(Pedido pedido) {
        if (pedido == null)
            throw new IllegalArgumentException("No se puede cocinar un pedido null");

        pedidoEnCocina = pedido;
        tiempoCoccionRestante = pedido.getTiempoPreparacion();

        assert tiempoCoccionRestante > 0 : "Tiempo de cocción inválido";
    }

    private void procesarCocina() {
        if (pedidoEnCocina == null)
            return;

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
        int i = (int) (Math.random() * PLATOS.length);

        assert i >= 0 && i < PLATOS.length : "Índice fuera de rango";
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
        System.out.println("[ENTER] Avanzar | [A] Ver Arbol");
        String entrada = entradaUsuario.nextLine().trim();

        if (entrada.equals("A")) {
            System.out.println("------ ARBOL DE PEDIDOS ------");
            colaPedidos.imprimirArbol();
            System.out.println("------------------------------");
        }
    }
}
