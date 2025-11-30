public class Pedido {

    public enum Tipo {
        BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA
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

    public Tipo getTipo() { return tipo; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getTiempoLlegada() { return tiempoLlegada; }
    public void setTiempoLlegada(int minuto) { this.tiempoLlegada = minuto; }
    public void reducirTiempo() { tiempoRestante--; }

    private int generarTiempo(Tipo tipo) {
        switch (tipo) {
            case BEBIDA:    return Main.rnd.nextInt(2) + 1;
            case CAFE:      return Main.rnd.nextInt(2) + 2;
            case COLACAO:   return Main.rnd.nextInt(3) + 2;
            case BOCADILLO: return Main.rnd.nextInt(3) + 3;
            default:        return Main.rnd.nextInt(4) + 5;
        }
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoRestante + " min)";
    }
}
