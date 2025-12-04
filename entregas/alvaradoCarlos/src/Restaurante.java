import java.util.Random;

public class Restaurante {
    private Cocina cocina;
    private TipoPlato[] cartaMenu;
    private Random rnd;
    private final int DURACION_JORNADA = 24;

    public Restaurante() {
        this.cocina = new Cocina();
        this.rnd = new Random();
        this.inicializarMenu();
    }

    private void inicializarMenu() {
        this.cartaMenu = new TipoPlato[] {
            new TipoPlato("Bebida", 1, 2),
            new TipoPlato("Café", 2, 3),
            new TipoPlato("Colacao", 2, 4),
            new TipoPlato("Bocadillo", 3, 5),
            new TipoPlato("Ensalada", 5, 8)
        };
    }

    public void run() throws InterruptedException {
        System.out.println("=== BIENVENIDO AL RESTAURANTE RCCCF ===");
        System.out.println("Sistema de cocina inteligente iniciado...");

        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            System.out.println("----------------------------------------");
            System.out.printf("[%d.0]\n", minuto);

            gestionarLlegadaClientes();
            cocina.asignarTrabajo();
            mostrarEstado();
            
            cocina.trabajar();

            Thread.sleep(200);
        }

        mostrarResumenFinal();
    }

    private void gestionarLlegadaClientes() {
        if (rnd.nextDouble() < 0.40) {
            TipoPlato platoElegido = cartaMenu[rnd.nextInt(cartaMenu.length)];
            Pedido nuevo = new Pedido(platoElegido);
            
            cocina.recibirPedido(nuevo);
            System.out.println(">>> Llega pedido: " + nuevo.nombrePlato() + " (" + nuevo.tiempoTotal() + " min)");
        }
    }

    private void mostrarEstado() {
        System.out.println("COLA: " + cocina.pendientes() + " pendientes");
        System.out.println("Cocinero: " + cocina.estado());
    }

    private void mostrarResumenFinal() {
        System.out.println("\n========================================");
        System.out.println("RESUMEN DE CIERRE DE CAJA");
        System.out.println("========================================");
        System.out.println("Pedidos servidos       : " + cocina.completados());
        System.out.println("Pedidos pendientes     : " + cocina.pendientes());
        System.out.println("Comparaciones (Eficiencia): " + cocina.eficiencia());
        System.out.println("========================================");
    }
}