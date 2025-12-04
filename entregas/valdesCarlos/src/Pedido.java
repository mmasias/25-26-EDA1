public class Pedido {

    public enum Tipo {
        CAFE, BOCADILLO, ENSALADA, BEBIDA, COLACAO
    }

    private static int NEXT_ID = 1;

    private int id;
    private Tipo tipo;
    private int tiempoPreparacion;
    private int llegada;
    private int restante;
    private int inicio = -1;

    public Pedido(Tipo tipo, int tiempoPreparacion, int llegada) {
        this.id = NEXT_ID++;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.llegada = llegada;
        this.restante = tiempoPreparacion;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getLlegada() {
        return llegada;
    }

    public int getRestante() {
        return restante;
    }

    public void reducirTiempo() {
        restante--;
    }

    public void setInicio(int minuto) {
        this.inicio = minuto;
    }

    public int getEspera() {
        return inicio - llegada;
    }

    @Override
    public String toString() {
        return getTipoTexto() + " (" + tiempoPreparacion + " min)";
    }

    public String getTipoTexto() {
        return switch (tipo) {
            case BEBIDA -> "Bebida";
            case CAFE -> "Café";
            case COLACAO -> "Colacao";
            case BOCADILLO -> "Bocadillo";
            case ENSALADA -> "Ensalada";
        };
    }

}
