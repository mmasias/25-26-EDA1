
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Restaurante {

    private final EstructuraPedidos estructura;
    private final Cocinero cocinero;
    private final RandomGenerator random;
    private final int tiempoTotal;

    private int pedidosGenerados = 0;
    private int pedidosAtendidos = 0;
    private int pedidosPendientes = 0;
    private final List<Integer> tiemposEspera = new ArrayList<>();

    public Restaurante(EstructuraPedidos estructura, Cocinero cocinero, RandomGenerator random, int tiempoTotal) {
        this.estructura = estructura;
        this.cocinero = cocinero;
        this.random = random;
        this.tiempoTotal = tiempoTotal;
    }

    public void ejecutar() {
        for (int minuto = 1; minuto <= tiempoTotal; minuto++) {
            System.out.println("=== Minuto " + minuto + " ===");

            if (random.probabilidad(0.5)) {
                Pedido nuevo = generarPedido(minuto);
                estructura.insertar(nuevo);
                pedidosGenerados++;

                System.out.println("Nuevo pedido → " + nuevo);
            }

            Pedido completado = cocinero.procesarMinuto(estructura);

            if (completado != null) {
                pedidosAtendidos++;
                int tiempoEspera = minuto - completado.getMinutoLlegada();
                tiemposEspera.add(tiempoEspera);

                System.out.println("Pedido completado → " + completado);
                System.out.println("Esperó: " + tiempoEspera + " minutos");
            }

            System.out.println("Pedidos en espera: " + estructura.size());
            System.out.println();
        }

        pedidosPendientes = estructura.size() + (cocinero.estaOcupado() ? 1 : 0);

        imprimirResultados();
    }

    private Pedido generarPedido(int minuto) {
        int tiempoPreparacion = random.enRango(3, 15);
        return new Pedido(tiempoPreparacion, minuto);
    }

    private void imprimirResultados() {
        System.out.println("=======================================");
        System.out.println("          RESULTADOS FINALES           ");
        System.out.println("=======================================");

        System.out.println("Pedidos generados:  " + pedidosGenerados);
        System.out.println("Pedidos atendidos:  " + pedidosAtendidos);
        System.out.println("Pedidos pendientes: " + pedidosPendientes);

        System.out.println("Comparaciones realizadas en el árbol: " + estructura.getComparaciones());

        if (!tiemposEspera.isEmpty()) {
            double promedio = tiemposEspera.stream().mapToInt(a -> a).average().orElse(0);
            System.out.println("Tiempo promedio de espera: " + promedio + " minutos");
        } else {
            System.out.println("Tiempo promedio de espera: N/A");
        }
    }
}
