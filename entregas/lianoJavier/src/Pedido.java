public class Pedido {
    private int tipo;
    private int tiempoPreparacion;
    private int minutoLlegada;

    public Pedido(int tipo, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = TipoDePlato.generarTiempoAleatorio(tipo);
        this.minutoLlegada = minutoLlegada;
    }

    public int obtenerTipo() {
        return tipo;
    }

    public int obtenerTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int obtenerMinutoLlegada() {
        return minutoLlegada;
    }

    public int calcularTiempoEspera(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    public void procesarMinuto() {
        assert tiempoPreparacion <= 0 : "Tiempo Negativo o 0";

        tiempoPreparacion--;
    }

    public boolean estaCompletado() {
        return tiempoPreparacion == 0;
    }

    public static void main(String[] args) {
        Console.imprimirln("=== Pruebas de Pedido ===");
        Console.saltoLinea();

        Console.imprimirln("Creando pedidos de cada tipo:");
        for (int tipo = 0; tipo <= 4; tipo++) {
            Pedido pedido = new Pedido(tipo, 0);
            Console.imprimirln(TipoDePlato.obtenerNombre(tipo) +
                    " - Tiempo de preparación: " + pedido.obtenerTiempoPreparacion() + " min");
        }

        Console.saltoLinea();
        Console.imprimirln("Probando procesamiento de un pedido:");
        Pedido pedidoPrueba = new Pedido(TipoDePlato.CAFE, 1);
        int tiempoInicial = pedidoPrueba.obtenerTiempoPreparacion();
        Console.imprimirln("Tiempo inicial: " + tiempoInicial + " min");
        Console.imprimirln("Llegó en minuto: " + pedidoPrueba.obtenerMinutoLlegada());

        pedidoPrueba.procesarMinuto();
        Console.imprimirln("Después de 1 minuto: " + pedidoPrueba.obtenerTiempoPreparacion() + " min");
        Console.imprimirln("¿Completado? " + pedidoPrueba.estaCompletado());

        while (!pedidoPrueba.estaCompletado()) {
            pedidoPrueba.procesarMinuto();
        }
        Console.imprimirln("Después de completar: " + pedidoPrueba.obtenerTiempoPreparacion() + " min");
        Console.imprimirln("¿Completado? " + pedidoPrueba.estaCompletado());

        Console.saltoLinea();
        Console.imprimirln("Probando cálculo de tiempo de espera:");
        Pedido pedidoEspera = new Pedido(TipoDePlato.ENSALADA, 5);
        Console.imprimirln("Pedido llegó en minuto: " + pedidoEspera.obtenerMinutoLlegada());
        Console.imprimirln("Tiempo de espera en minuto 10: " + pedidoEspera.calcularTiempoEspera(10) + " min");
        Console.imprimirln("Tiempo de espera en minuto 15: " + pedidoEspera.calcularTiempoEspera(15) + " min");

        Console.saltoLinea();
        Console.imprimirln("=== Todas las pruebas completadas ===");
    }
}
