import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Cocinero cocinero = new Cocinero();
        Random r = new Random();

        int jornada = 20; 

        System.out.println("=== SIMULACION RCCCF ===");

        for (int minuto = 1; minuto <= jornada; minuto++) {

            if (r.nextDouble() < 0.40) {
                Plato p = generarPlatoAleatorio(r);
                Pedido ped = new Pedido(p, minuto);
                cocinero.nuevoPedido(ped);
                System.out.println("[MINUTO " + minuto + "] Llega pedido: " + p);
            }

            cocinero.procesarMinuto(minuto);
        }

        cocinero.resumenFinal();
    }

    static Plato generarPlatoAleatorio(Random r) {
        int tipo = r.nextInt(5);
        switch (tipo) {
            case 0: return new Plato("Bebida",   1 + r.nextInt(2));
            case 1: return new Plato("Café",     2 + r.nextInt(2));
            case 2: return new Plato("Colacao",  2 + r.nextInt(3));
            case 3: return new Plato("Bocadillo",3 + r.nextInt(3));
            default:return new Plato("Ensalada", 5 + r.nextInt(4));
        }
    }
}
