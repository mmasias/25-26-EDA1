package reto005;

import java.util.Random;

public class Simulacion {

    public static void main(String[] args) {
        Cocina cocina = new Cocina();
        Random random = new Random();
        int tiempoTotalSimulacion = 60;

        for (int minuto = 1; minuto <= tiempoTotalSimulacion; minuto++) {
            System.out.println("Minuto " + minuto);

            if (random.nextDouble() < 0.4) {
                Pedido nuevoPedido = generarPedidoAleatorio();
                System.out.println("Nuevo pedido: " + nuevoPedido);
                cocina.recibirPedido(nuevoPedido);
            }

            cocina.trabajar();
            cocina.imprimirEstado();
            System.out.println("--------------------------------------------------");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cocina.mostrarResumen();
    }

    private static Pedido generarPedidoAleatorio() {
        String[] tipos = { "Hamburguesa", "Pizza", "Ensalada", "Pasta", "Sopa", "Sandwich" };
        Random random = new Random();
        String tipo = tipos[random.nextInt(tipos.length)];
        int tiempo = random.nextInt(15) + 1;
        return new Pedido(tipo, tiempo);
    }
}
