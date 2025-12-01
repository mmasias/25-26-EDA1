import java.util.ArrayList;
import java.util.List;

public class CCCF {

    static final int TIPO_BEBIDA = 0;
    static final int MIN_BEBIDA = 1;
    static final int MAX_BEBIDA = 2;

    static final int TIPO_CAFE = 1;
    static final int MIN_CAFE = 2;
    static final int MAX_CAFE = 3;

    static final int TIPO_COLACAO = 2;
    static final int MIN_COLACAO = 2;
    static final int MAX_COLACAO = 4;

    static final int TIPO_BOCADILLO = 3;
    static final int MIN_BOCADILLO = 3;
    static final int MAX_BOCADILLO = 5;

    static final int TIPO_ENSALADA = 4;
    static final int MIN_ENSALADA = 5;
    static final int MAX_ENSALADA = 8;

    static final int TOTAL_TIPOS_PLATOS = 5;

    static int comparacionesTotales = 0;

    public static void main(String[] args) {

        final int JORNADA_MINUTOS = 720;
        final double PROBABILIDAD_LLEGADA = 0.4;

        List<Pedido> cola = new ArrayList<>();
        Cocinero cocinero = new Cocinero();

        System.out.println("=== INICIO SIMULACIÓN RCCCF ===");

        for (int minuto = 1; minuto <= JORNADA_MINUTOS; minuto++) {

            if (Math.random() < PROBABILIDAD_LLEGADA) {
                Pedido nuevo = generarPedidoAleatorio(minuto);
                cola.add(nuevo);
                System.out.println("[" + minuto + "] Nuevo pedido: " + nuevo.getNombrePlato() + " (" + nuevo.getTiempoTotal() + " min)");
            }

            if (cocinero.estaLibre() && !cola.isEmpty()) {
                Pedido elMasRapido = buscarSiguientePedido(cola);
                cocinero.asignarPedido(elMasRapido);
                System.out.println("   -> Cocinero empieza: " + elMasRapido.getNombrePlato());
            }

            cocinero.trabajar();
        }

        System.out.println("\n=== FIN DE JORNADA ===");
        System.out.println("Pedidos atendidos: " + cocinero.getPlatosAtendidos());
        System.out.println("Pedidos pendientes en cola: " + cola.size());
        System.out.println("Eficiencia (Comparaciones realizadas): " + comparacionesTotales);
    }

    private static Pedido buscarSiguientePedido(List<Pedido> cola) {
        if (cola.isEmpty()) return null;

        int indiceDelMejor = 0;

        for (int i = 1; i < cola.size(); i++) {
            comparacionesTotales++;
            if (cola.get(i).getTiempoRestante() < cola.get(indiceDelMejor).getTiempoRestante()) {
                indiceDelMejor = i;
            }
        }

        return cola.remove(indiceDelMejor);
    }

    private static Pedido generarPedidoAleatorio(int minutoActual) {
        int tipo = (int)(Math.random() * TOTAL_TIPOS_PLATOS);
        
        String nombre = "";
        int tiempoMinimo = 0;
        int tiempoMaximo = 0;

        switch (tipo) {
            case TIPO_BEBIDA:
                nombre = "Bebida";
                tiempoMinimo = MIN_BEBIDA;
                tiempoMaximo = MAX_BEBIDA;
                break;
            case TIPO_CAFE:
                nombre = "Café";
                tiempoMinimo = MIN_CAFE;
                tiempoMaximo = MAX_CAFE;
                break;
            case TIPO_COLACAO:
                nombre = "Colacao";
                tiempoMinimo = MIN_COLACAO;
                tiempoMaximo = MAX_COLACAO;
                break;
            case TIPO_BOCADILLO:
                nombre = "Bocadillo";
                tiempoMinimo = MIN_BOCADILLO;
                tiempoMaximo = MAX_BOCADILLO;
                break;
            case TIPO_ENSALADA:
                nombre = "Ensalada";
                tiempoMinimo = MIN_ENSALADA;
                tiempoMaximo = MAX_ENSALADA;
                break;
        }

        int duracion = (int)(Math.random() * (tiempoMaximo - tiempoMinimo + 1)) + tiempoMinimo;
        return new Pedido(nombre, duracion, minutoActual);
    }
}