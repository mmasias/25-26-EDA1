import java.util.Random;

public class Simulacion {

    public static void main(String[] args) {

        Plato[] tipos = {
            new Plato("Bebida", 1, 2),
            new Plato("Café", 2, 3),
            new Plato("Colacao", 2, 4),
            new Plato("Bocadillo", 3, 5),
            new Plato("Ensalada", 5, 8)
        };

        int DURACION = 120;
        double PROB = 0.4;

        Random r = new Random();

        ArrayPedidos cola = new ArrayPedidos();
        Pila pilaHistorial = new Pila();

        Pedido actual = null;

        int comparaciones = 0;
        int pedidosAtendidos = 0;
        int tiempoEsperaTotal = 0;

        System.out.println("============== RCCCF – EDA ==============");

        for (int minuto = 1; minuto <= DURACION; minuto++) {

            System.out.println("\n[" + minuto + ".0]");

            // LLEGADA DE PEDIDO
            if (r.nextDouble() < PROB) {
                Plato p = tipos[r.nextInt(5)];
                int prep = r.nextInt(p.max - p.min + 1) + p.min;
                
                Pedido nuevo = new Pedido(p, prep, minuto);
                cola.add(nuevo);

                System.out.println("Llega pedido: " + p.nombre + " (" + prep + " min)");
            }

            // SELECCIÓN DEL MÍNIMO SI NO HAY PEDIDO ACTUAL
            if (actual == null && cola.length() > 0) {

                int posMin = 0;
                Pedido min = cola.get(0);

                for (int i = 1; i < cola.length(); i++) {
                    comparaciones++;
                    if (cola.get(i).tiempoRestante < min.tiempoRestante) {
                        min = cola.get(i);
                        posMin = i;
                    }
                }

                actual = min;
                cola.remove(posMin);

                tiempoEsperaTotal += minuto - actual.llegada;
            }

            // PROCESADO
            if (actual != null) {
                System.out.println("Cocinero: [" + actual.plato.nombre + " - " +
                                   actual.tiempoRestante + " min restantes]");
                actual.tiempoRestante--;

                if (actual.tiempoRestante == 0) {
                    pilaHistorial.push(actual);
                    pedidosAtendidos++;
                    actual = null;
                }
            } else {
                System.out.println("Cocinero: [Libre]");
            }

            System.out.println("COLA: " + cola.length() + " pedidos");
        }

        // RESUMEN
        System.out.println("\n=========== RESUMEN ==================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + cola.length());
        System.out.println("Tiempo total espera      : " + tiempoEsperaTotal);
        if (pedidosAtendidos > 0) {
            System.out.println("Tiempo medio espera      : " + (double) tiempoEsperaTotal / pedidosAtendidos);
        } else {
            System.out.println("Tiempo medio espera      : 0");
        }
        System.out.println("Comparaciones totales    : " + comparaciones);
        System.out.println("======================================");
    }
}
