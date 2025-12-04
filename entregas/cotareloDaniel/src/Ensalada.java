public class Ensalada extends Plato {

    private static final int TIEMPO_MINIMO = 5;
    private static final int TIEMPO_MAXIMO = 8;

    public Ensalada() {
        super("Ensalada", generarTiempoAleatorio());
    }

    private static int generarTiempoAleatorio() {
        return (int)(Math.random() * (TIEMPO_MAXIMO - TIEMPO_MINIMO + 1)) + TIEMPO_MINIMO;
    }
}