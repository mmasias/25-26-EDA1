public class Simulation {

    public static void main(String[] args) {

        int DURACION = 120; 
        Cocina cocina = new Cocina();
        Pedido pedidoActual = null;

        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;

        for (int minuto = 1; minuto <= DURACION; minuto++) {

            System.out.println("=================================");
            System.out.println("Minuto " + minuto);

            if (Math.random() < 0.4) {
                String[] tipos = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
                int[] minTiempo = {1, 2, 2, 3, 5};
                int[] maxTiempo = {2, 3, 4, 5, 8};

                int indice = (int)(Math.random() * tipos.length);
                int tiempo = minTiempo[indice] + (int)(Math.random() * (maxTiempo[indice] - minTiempo[indice] + 1));

                Pedido nuevo = new Pedido(tipos[indice], tiempo, minuto);
                cocina.agregarPedido(nuevo);
                System.out.println("Llega pedido: " + nuevo.tipo + " (" + tiempo + " min)");
            }

            if (pedidoActual == null && cocina.hayPedidos()) {
                pedidoActual = cocina.obtenerPedidoMasCorto();
            }

            if (pedidoActual != null) {
                pedidoActual.trabajarUnMinuto();
                System.out.println("Cocinero: " + pedidoActual.mostrar());
            } else {
                System.out.println("Cocinero: Libre");
            }

            System.out.println("Cola: " + cocina.pedidosEnCola() + " pedidos");

            if (pedidoActual != null && pedidoActual.terminado()) {
                pedidosAtendidos++;
                tiempoTotalEspera += (minuto - pedidoActual.minutoLlegada);
                pedidoActual = null;
            }
        }

        System.out.println("\n====== RESUMEN ======");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Pedidos pendientes: " + cocina.pedidosEnCola());
        System.out.println("Tiempo total de espera: " + tiempoTotalEspera);
        if (pedidosAtendidos > 0) {
            System.out.println("Tiempo medio de espera: " + ((double)tiempoTotalEspera / pedidosAtendidos));
        }
        System.out.println("Comparaciones realizadas: " + cocina.comparaciones);
    }
}
