package entregas.munozManuel.src;

import java.util.Scanner;

class Mundo {
    private double tiempo;
    private final Restaurante restaurante;
    private final Chef masiasChef;
    private final Scanner scanner = new Scanner(System.in);

    public Mundo(){
        tiempo = 6.0;
        masiasChef = new Chef();
        restaurante = new Restaurante("Buena Fumada de Codigo", masiasChef);
    }

    public void run(){
        while(tiempo < restaurante.cierre()){
            if(tiempo >= restaurante.apertura()){
                System.out.println("Hemos abierto el restaurante " + restaurante.nombreRestaurante());
            }
            while (tiempo >= restaurante.apertura() && tiempo <= restaurante.cierre()){
                System.out.println("Dentro de restaurante abierto " + tiempo);
                if (0.4 > Math.random()){
                    restaurante.tomarPedido(new Cliente());
                }
                masiasChef.actualizarEstadoDelPedido();
                System.out.println("| Pedido |".repeat(restaurante.cantidadOrdenes()));
                // System.out.println("Presiona Enter para continuar...");
                // scanner.nextLine();
                pasarTiempo();

            }

            System.out.println("Ey funciona " + tiempo);
            pasarTiempo();
        }
        scanner.close();
    }

    private void pasarTiempo(){
        tiempo += 0.1;
    }
}
