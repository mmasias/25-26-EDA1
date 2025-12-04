import java.util.Random;

public class generadorPedidos {

    private static final Random random = new Random();

    private static final String[] TIPOS = { "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada" };
    private static final int[][] TIEMPOS = {
        {1, 2},
        {2, 3},
        {2, 4},
        {3, 5},
        {5, 8}
    };

    public static boolean llegaPedido() {
        return random.nextDouble() < 0.40;
    }

    public static Pedido generarPedido(int minutoActual) {
        int indice = random.nextInt(TIPOS.length);
        String tipoPedido = TIPOS[indice];
        int minimo = TIEMPOS[indice][0];
        int maximo = TIEMPOS[indice][1];

        int tiempo = random.nextInt(maximo - minimo + 1) + minimo;

        return new Pedido(tipoPedido, tiempo, minutoActual);
    }
}


