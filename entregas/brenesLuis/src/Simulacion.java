
public class Simulacion {

    public static void main(String[] args) {
        ListaPedidos lista = new ListaPedidos();
        Cocinero cocinero = new Cocinero();

        System.out.println("=== INICIO SIMULACION RCCCF ===");

        for (int minuto = 1; minuto <= 120; minuto++) {

            if (Math.random() < 0.40) {
                String[] tipos = {"Café", "Bocadillo", "Ensalada", "Refresco"};
                int[] tiempos = {2, 4, 7, 1};
                int azar = (int) (Math.random() * tipos.length);

                Pedido nuevo = new Pedido(tipos[azar], tiempos[azar], minuto);
                lista.insert(nuevo);

                System.out.println("[" + minuto + "] Llega: " + nuevo);
            }

            if (cocinero.estaLibre() && !lista.estaVacia()) {
                Pedido siguiente = lista.extraerMasRapido();
                cocinero.recibirPedido(siguiente);
                System.out.println("   >>> Cocinando: " + siguiente.getTipo());
            }

            cocinero.trabajar();
        }

        System.out.println("\n========================================");
        System.out.println("RESUMEN DE JORNADA");
        System.out.println("========================================");
        System.out.println("Total Atendidos        : " + cocinero.getAtendidos());
        System.out.println("Pendientes en Lista    : " + lista.size());
        System.out.println("Comparaciones Totales  : " + lista.getComparaciones());
        System.out.println("========================================");
    }
}
