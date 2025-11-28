public class Bocadillo extends Plato {

    private static final int TIEMPO_MINIMO = 3;
    private static final int TIEMPO_MAXIMO = 5;

    public Bocadillo() {
        super("Bocadillo", generarTiempoAleatorio());
    }

    private static int generarTiempoAleatorio() {
        return (int)(Math.random() * (TIEMPO_MAXIMO - TIEMPO_MINIMO + 1)) + TIEMPO_MINIMO;
    }
}