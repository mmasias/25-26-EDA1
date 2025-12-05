public class Pedido {
    private final String TIPO;
    private final int TIEMPO_PREP;
    private int tiempoRestante;
    private final int TIEMPO_LLEGADA;

    public Pedido(String tipo, int tiempoPrep, int tiempoLlegada) {
        this.TIPO = tipo;
        this.TIEMPO_PREP = tiempoPrep;
        this.tiempoRestante = tiempoPrep;
        this.TIEMPO_LLEGADA = tiempoLlegada;
    }

    public String getTIPO() {
        return TIPO;
    }

    public int getTIEMPO_PREP() {
        return TIEMPO_PREP;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTIEMPO_LLEGADA() {
        return TIEMPO_LLEGADA;
    }

    public void tick() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public boolean isTerminado() {
        return tiempoRestante <= 0;
    }

    @Override
    public String toString() {
        return TIPO + " (" + TIEMPO_PREP + " min)";
    }
}