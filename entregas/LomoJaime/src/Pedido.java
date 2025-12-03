public class Pedido {
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(String tipo, int tiempoPreparacion) {
        assert tipo != null && !tipo.isEmpty() : "El tipo no puede ser nulo o vacío";
        assert tiempoPreparacion > 0 : "El tiempo de preparación debe ser mayor que 0";
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoEspera = 0;
    }

    public String getTipo() { return tipo; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoEspera() { return tiempoEspera; }
    public void incrementarEspera() { this.tiempoEspera++; }
}