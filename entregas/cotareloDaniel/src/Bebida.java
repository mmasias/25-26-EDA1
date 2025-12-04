public class Bebida extends Plato {

    private static final int TIEMPO_MINIMO = 1;
    private static final int TIEMPO_MAXIMO = 2;

    public Bebida() {
        super("Bebida", generarTiempoAleatorio());
    }

    private static int generarTiempoAleatorio() {
        return (int)(Math.random() * (TIEMPO_MAXIMO - TIEMPO_MINIMO + 1)) + TIEMPO_MINIMO;
    }
}