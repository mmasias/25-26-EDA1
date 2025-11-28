public class Cafe extends Plato {

    private static final int TIEMPO_MINIMO = 2;
    private static final int TIEMPO_MAXIMO = 3;

    public Cafe() {
        super("Cafe", generarTiempoAleatorio());
    }

    private static int generarTiempoAleatorio() {
        return (int)(Math.random() * (TIEMPO_MAXIMO - TIEMPO_MINIMO + 1)) + TIEMPO_MINIMO;
    }
}