public class Colacao extends Plato {

    private static final int TIEMPO_MINIMO = 2;
    private static final int TIEMPO_MAXIMO = 4;

    public Colacao() {
        super("Colacao", generarTiempoAleatorio());
    }

    private static int generarTiempoAleatorio() {
        return (int)(Math.random() * (TIEMPO_MAXIMO - TIEMPO_MINIMO + 1)) + TIEMPO_MINIMO;
    }
}