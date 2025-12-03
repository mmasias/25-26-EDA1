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

                if (masiasChef.ocupado()) {
                    masiasChef.processarPedido();
                }

                if (0.4 > Math.random()){
                    Cliente clienteNuevo = new Cliente();
                    restaurante.tomarPedido(clienteNuevo);
                }

                if (restaurante.personasEnFila() > 0 && !masiasChef.ocupado()){
                    restaurante.asignarPedido();
                }

                System.out.println("| Pedido |".repeat(restaurante.personasEnFila()));
                System.out.println("Presiona Enter para continuar...");
                scanner.nextLine();
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
