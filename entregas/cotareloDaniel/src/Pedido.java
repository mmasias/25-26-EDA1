public class Pedido {

    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int instanteLlegada;
    private int instanteInicio;

    public Pedido(String tipo, int tiempoPreparacion, int instanteLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.instanteLlegada = instanteLlegada;
        this.instanteInicio = -1;
    }

    public int obtenerTiempoRestante() {
        return tiempoRestante;
    }

    public void reducirTiempo() {
        tiempoRestante = tiempoRestante - 1;
    }

    public void marcarInicio(int minuto) {
        instanteInicio = minuto;
    }

    public int obtenerEspera() {
        return instanteInicio - instanteLlegada;
    }

    public String obtenerTipo() {
        return tipo;
    }
}