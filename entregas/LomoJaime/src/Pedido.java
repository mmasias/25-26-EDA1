public class Pedido {
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int tiempoLlegada;

    public Pedido(String tipo, int tiempoPreparacion, int tiempoLlegada) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El tipo no puede ser nulo o vacío");
        }
        if (tiempoPreparacion <= 0) {
            throw new IllegalArgumentException("El tiempo de preparación debe ser mayor que 0");
        }
        if (tiempoLlegada < 0) {
            throw new IllegalArgumentException("El tiempo de llegada no puede ser negativo");
        }
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getTipo() { return tipo; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getTiempoLlegada() { return tiempoLlegada; }

    public void setTiempoRestante(int tiempo) {
        this.tiempoRestante = tiempo;
    }
}