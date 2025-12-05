import java.util.Random;

public class GeneradorPedidos {
    private final Random random;

    public GeneradorPedidos() {
        this.random = new Random();
    }

    public boolean llegaPedido() {
        return random.nextInt(100) < 40;
    }

    public Pedido crearPedido(int minutoActual) {
        int tipoNumero = random.nextInt(5);

        String tipo = "";
        int tiempo = 0;

        if (tipoNumero == 0) {
            tipo = "Bebida";
            tiempo = numeroEntre(1, 2);
        } else if (tipoNumero == 1) {
            tipo = "Café";
            tiempo = numeroEntre(2, 3);
        } else if (tipoNumero == 2) {
            tipo = "Colacao";
            tiempo = numeroEntre(2, 4);
        } else if (tipoNumero == 3) {
            tipo = "Bocadillo";
            tiempo = numeroEntre(3, 5);
        } else {
            tipo = "Ensalada";
            tiempo = numeroEntre(5, 8);
        }

        return new Pedido(tipo, tiempo, minutoActual);
    }

    private int numeroEntre(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}