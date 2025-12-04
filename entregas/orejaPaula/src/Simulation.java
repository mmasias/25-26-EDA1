import java.util.Random;

public class Simulation {

    private final ArbolPedidos arbol = new ArbolPedidos();
    private Pedido actual = null;
    private int atendidos = 0;
    private int tiempoEspera = 0;

    private final Random r = new Random();

    public void iniciar() {
    }

    public static void main(String[] args) {
        new Simulation().iniciar();
    }
}

