public class Pedido {

    public enum Tipo {
        BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA
    }

    private static long seed = 1234567;

    private static int nextInt(int max) {
        seed = (seed * 48271) % 2147483647;
        return (int)(seed % max);
    }

    private static double nextDouble() {
        seed = (seed * 48271) % 2147483647;
        return (seed % 10000) / 10000.0;
    }

    private final Tipo tipo;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private int tiempoLlegada;

    public Pedido(Tipo tipo) {
        this.tipo = tipo;
        this.tiempoPreparacion = generarTiempo(tipo);
        this.tiempoRestante = tiempoPreparacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int minuto) {
        this.tiempoLlegada = minuto;
    }

    public void reducirTiempo() {
        tiempoRestante--;
    }

    private int generarTiempo(Tipo tipo) {
        switch (tipo) {
            case BEBIDA:   return nextInt(2) + 1;
            case CAFE:     return nextInt(2) + 2;
            case COLACAO:  return nextInt(3) + 2;
            case BOCADILLO:return nextInt(3) + 3;
            default:       return nextInt(4) + 5;
        }
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoRestante + " min)";
    }

    public static boolean llegaPedido() {
        return nextDouble() < 0.40;
    }

    public static Tipo tipoAleatorio() {
        return Tipo.values()[ nextInt(5) ];
    }
}
