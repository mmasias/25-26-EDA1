import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        final int DURACION = 120;

        arbolPedidos arbolPedidos = new arbolPedidos();
        Cocinero cocinero = new Cocinero();
        Scanner scanner = new Scanner(System.in);

        for (int minutoActual = 1; minutoActual <= DURACION; minutoActual++) {

            System.out.print("Minuto " + minutoActual + " - Pulsa ENTER para continuar, K para ver el árbol: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("K")) {
                System.out.println("\nESTADO ACTUAL DEL ÁRBOL:");
                arbolPedidos.imprimirArbol();
                minutoActual--;
                continue;
            }

            if (generadorPedidos.llegaPedido()) {
                Pedido pedidoNuevo = generadorPedidos.generarPedido(minutoActual);
                System.out.println("Llega pedido: " + pedidoNuevo);
                arbolPedidos.insertar(pedidoNuevo);
            }

            if (cocinero.getPedidoActual() == null) {
                if (!arbolPedidos.estaVacio()) {
                    Pedido siguientePedido = arbolPedidos.extraerMinimo();
                    cocinero.asignarPedido(siguientePedido, minutoActual);
                }
            }

            cocinero.procesar();
        }

        System.out.println("\nRESUMEN");
        System.out.println("Pedidos atendidos      : " + cocinero.getPedidosAtendidos());
        System.out.println("Tiempo total espera    : " + cocinero.getTiempoEsperaTotal());
        System.out.println("Comparaciones árbol    : " + arbolPedidos.getComparaciones());
        scanner.close();
    }
}

