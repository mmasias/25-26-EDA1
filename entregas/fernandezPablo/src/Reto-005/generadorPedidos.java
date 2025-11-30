import java.util.Random;

public class generadorPedidos {

    private static final Random rand = new Random();

    private static final String[] TIPOS = { "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada" };
    private static final int[][] TIEMPOS = {
            {1, 2},
            {2, 3},
            {2, 4},
            {3, 5},
            {5, 8}
    };

    public static boolean llegaPedido() {
        return rand.nextDouble() < 0.40;
    }

    public static Pedido generarPedido(int minutoActual) {
        int index = rand.nextInt(TIPOS.length);
        String tipo = TIPOS[index];
        int min = TIEMPOS[index][0];
        int max = TIEMPOS[index][1];

        int tiempo = rand.nextInt(max - min + 1) + min;

        return new Pedido(tipo, tiempo, minutoActual);
    }
}

