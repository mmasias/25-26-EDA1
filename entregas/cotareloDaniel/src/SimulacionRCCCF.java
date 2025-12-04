public class SimulacionRCCCF {

    public static void main(String[] args) {

        ArbolPedidos arbolPedidos = new ArbolPedidos();

        int minutoActual = 1;
        int minutosTotales = 120;
        double probabilidadLlegada = 0.4;

        Pedido pedidoEnPreparacion = null;

        int pedidosAtendidos = 0;
        int tiempoEsperaTotal = 0;

        while (minutoActual <= minutosTotales) {

            System.out.println("========================================");
            System.out.println("[" + minutoActual + " minutos]");

            double aleatorio = Math.random();

            if (aleatorio < probabilidadLlegada) {

                int tipoRandom = (int) (Math.random() * 5);
                String tipo = "";
                int tiempo = 0;

                switch (tipoRandom) {
                    case 0:
                        tipo = "Bebida";
                        tiempo = 1 + (int) (Math.random() * 2);
                        break;
                    case 1:
                        tipo = "Café";
                        tiempo = 2 + (int) (Math.random() * 2);
                        break;
                    case 2:
                        tipo = "Colacao";
                        tiempo = 2 + (int) (Math.random() * 3);
                        break;
                    case 3:
                        tipo = "Bocadillo";
                        tiempo = 3 + (int) (Math.random() * 3);
                        break;
                    default:
                        tipo = "Ensalada";
                        tiempo = 5 + (int) (Math.random() * 4);
                        break;
                }

                Pedido nuevo = new Pedido(tipo, tiempo, minutoActual);
                arbolPedidos.insertar(nuevo);

                System.out.println("Llega pedido: " + tipo + " (" + tiempo + " min)");
            }

            if (pedidoEnPreparacion == null) {
                if (!arbolPedidos.estaVacio()) {
                    pedidoEnPreparacion = arbolPedidos.extraerMinimo();
                    pedidoEnPreparacion.marcarInicio(minutoActual);
                    tiempoEsperaTotal = tiempoEsperaTotal + pedidoEnPreparacion.obtenerEspera();
                }
            }

            System.out.println("COLA (árbol): " + (arbolPedidos.estaVacio() ? 0 : "pendientes"));

            if (pedidoEnPreparacion != null) {
                System.out.println("Cocinero: [" + pedidoEnPreparacion.obtenerTipo()
                        + " - " + pedidoEnPreparacion.obtenerTiempoRestante() + " min restantes]");
            } else {
                System.out.println("Cocinero: [Sin pedido]");
            }

            if (pedidoEnPreparacion != null) {
                pedidoEnPreparacion.reducirTiempo();

                if (pedidoEnPreparacion.obtenerTiempoRestante() == 0) {
                    pedidosAtendidos = pedidosAtendidos + 1;
                    pedidoEnPreparacion = null;
                }
            }

            minutoActual = minutoActual + 1;
        }

        System.out.println("========================================");
        System.out.println("RESUMEN");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Pedidos pendientes: " + (arbolPedidos.estaVacio() ? 0 : 1));
        System.out.println("Tiempo de espera total: " + tiempoEsperaTotal + " minutos");

        double media;
        double bruto;
        int entero;

        if (pedidosAtendidos == 0) {
            media = 0;
        } else {
            bruto = ((double) tiempoEsperaTotal / pedidosAtendidos) * 100;
            entero = (int) bruto;
            media = entero / 100.0;
        }

        System.out.println("Tiempo medio de espera: " + media + " minutos");
        System.out.println("Comparaciones árbol: " + arbolPedidos.obtenerComparaciones());
        System.out.println("========================================");
    }
}