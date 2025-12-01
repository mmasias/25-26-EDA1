package rcccf2;

public class SimulacionRCCCF {

    public static void main(String[] args) {

        MonticuloMinimo monticulo = new MonticuloMinimo();
        GeneradorDePedidos generador = new GeneradorDePedidos();

        int pedidosAtendidos = 0;
        int tiempoTotalEspera = 0;

        Pedido actual = null;

        for (int minuto = 0; minuto < 120; minuto++) {

            if (generador.llegaPedido()) {
                monticulo.insertar(generador.crearPedido());
            }

            monticulo.aumentarEsperaGeneral();

            if (actual == null) {
                actual = monticulo.extraerMinimo();
            }

            if (actual != null) {
                actual.reducirTiempo();

                if (actual.getTiempoPreparacion() == 0) {
                    pedidosAtendidos++;
                    tiempoTotalEspera += actual.getTiempoEspera();
                    actual = null;
                }
            }
        }

        System.out.println("----- RESULTADOS RCCCF -----");
        System.out.println("Pedidos atendidos: " + pedidosAtendidos);
        System.out.println("Quedan en cola: " + (actual != null ? 1 : 0));
        System.out.println("Tiempo medio de espera: " +
            (pedidosAtendidos > 0 ? tiempoTotalEspera / pedidosAtendidos : 0));
        System.out.println("Comparaciones totales: " + monticulo.getComparaciones());
    }
}
