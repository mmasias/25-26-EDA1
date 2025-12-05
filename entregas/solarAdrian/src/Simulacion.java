import java.util.Random;

public class Simulacion {

    private static final String[] NOMBRES_PLATOS = { "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada" };
    private static final int[][] RANGOS_TIEMPOS = {
            { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 5, 8 }
    };

    public static void main(String[] args) {
        Cocina cocina = new Cocina();
        Random random = new Random();
        int jornadaMinutos = 720;

        System.out.println("--- SIMULACIÓN RCCCF CON ÁRBOL ---");

        for (int minuto = 1; minuto <= jornadaMinutos; minuto++) {

            if (random.nextDouble() < 0.4) {
                Pedido nuevo = generarPedidoAleatorio(minuto);
                cocina.recibirPedido(nuevo);
            }

            cocina.trabajar(minuto);

            if (minuto % 60 == 0) {
                System.out.println("Minuto " + minuto);
                cocina.imprimirEstado();
            }
        }

        cocina.mostrarResumen();
    }

    private static Pedido generarPedidoAleatorio(int minutoActual) {
        Random rand = new Random();
        int indice = rand.nextInt(NOMBRES_PLATOS.length);

        String nombre = NOMBRES_PLATOS[indice];
        int min = RANGOS_TIEMPOS[indice][0];
        int max = RANGOS_TIEMPOS[indice][1];

        int duracion = rand.nextInt((max - min) + 1) + min;

        return new Pedido(nombre, duracion, minutoActual);
    }
}