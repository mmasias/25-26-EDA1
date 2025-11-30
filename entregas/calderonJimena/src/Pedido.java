class Pedido {
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(String tipo, int tiempo, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempo;
        this.tiempoRestante = tiempo;
        this.minutoLlegada = minutoLlegada;
    }

    public String getTipo() { return tipo; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoRestante() { return tiempoRestante; }
    public void decrementarTiempo() { tiempoRestante--; }
    public int getMinutoLlegada() { return minutoLlegada; }
    public int getTiempoEspera(int minutoActual) {
        return minutoActual - minutoLlegada;
    }
}