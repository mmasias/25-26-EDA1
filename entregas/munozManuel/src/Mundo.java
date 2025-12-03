package entregas.munozManuel.src;

import java.util.Scanner;

class Mundo {
    private int horas;
    private int minutos;
    private final Restaurante restaurante;
    private final Chef masiasChef;
    private final Scanner scanner = new Scanner(System.in);

    public Mundo(){
        horas = 8;
        minutos = 50;
        masiasChef = new Chef();
        restaurante = new Restaurante("Buena Fumada de Codigo", masiasChef);
    }

    public void run(){
        while(horas < restaurante.cierre()){
            if(horas >= restaurante.apertura()){
                System.out.println("Hemos abierto el restaurante " + restaurante.nombreRestaurante());
            }
            while (horas >= restaurante.apertura() && horas < restaurante.cierre()){
                System.out.println("Dentro de restaurante abierto " + tiempo());

                if (0.25 > Math.random()){
                    Cliente clienteNuevo = new Cliente();
                    restaurante.tomarPedido(clienteNuevo);
                }

                if (restaurante.personasEnFila() > 0 && !masiasChef.ocupado()){
                    restaurante.asignarPedido();
                }

                if (masiasChef.ocupado()) {
                    masiasChef.processarPedido();
                    masiasChef.mostrarPedidoActual();
                }

                restaurante.mostrarPedidosEnFila();
                continuarPrograma();
                pasarTiempo(masiasChef);

            }

            System.out.println("Ey funciona " + tiempo());
            continuarPrograma();
            pasarTiempo(masiasChef);
        }
        System.out.println("Se hicieron " + restaurante.pedidosAtendidos());
        System.out.println("Han quedado pendientes " + restaurante.pedidosPendientes());
        scanner.close();
    }

    private void continuarPrograma(){
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }

    private String tiempo(){
        if(minutos < 10){
            return horas + ":0" + minutos;
        }
        return horas + ":" + minutos;
        
    }

    private void pasarTiempo(Chef chef){
        chef.trabajarEnPedido();
        minutos += 1;
        if (minutos == 60){
            horas += 1;
            minutos = 0;
        }
    }
}
