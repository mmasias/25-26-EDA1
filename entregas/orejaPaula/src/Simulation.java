import java.util.Random;

public class Simulation {

    private final ArbolPedidos arbol = new ArbolPedidos();
    private Pedido actual = null;
    private int atendidos = 0;
    private int tiempoEspera = 0;

    private final Random r = new Random();

    public void iniciar() {

        for (int minuto = 1; minuto <= 120; minuto++) {
            System.out.println("====================================");
            System.out.println("[" + minuto + ".0]");

            if (r.nextDouble() < 0.4) {
                String[] nombres = {"Bebida","Café","Colacao","Bocadillo","Ensalada"};
                int[][] rangos = {{1,2},{2,3},{2,4},{3,5},{5,8}};
                int i = r.nextInt(nombres.length);

                int tprep = rangos[i][0] + r.nextInt(rangos[i][1] - rangos[i][0] + 1);
                Pedido p = new Pedido(nombres[i], tprep, minuto);
                arbol.insertar(p);

                System.out.println("Llega pedido: " + p.nombre + " (" + p.tiempoPreparacion + " min)");
            }

            if (actual == null && !arbol.vacio()) {
                actual = arbol.extraerMinimo();
                actual.inicio = minuto;
                tiempoEspera += (actual.inicio - actual.llegada);
            }

            System.out.println("COLA: " + (arbol.vacio() ? 0 : 1) + " pedidos");

            if (actual != null) {
                System.out.println("Cocinero: [" + actual.nombre + " - " + actual.tiempoRestante + " min]");
                actual.tick();
                if (actual.terminado()) {
                    atendidos++;
                    actual = null;
                }
            } else {
                System.out.println("Cocinero: [Libre]");
            }
        }
    }

    public static void main(String[] args) {
        new Simulation().iniciar();
    }
}


