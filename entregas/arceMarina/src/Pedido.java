class Pedido {
    private int id;
    private int minutoLlegada;
    private TipoPlato tipo;
    private int tiempoTotal;
    private int tiempoRestante;

    Pedido(int id, int minutoLlegada) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;
        this.tipo = TipoPlato.obtenerAleatorio();
        this.tiempoTotal = this.tipo.generarTiempoAleatorio();
        this.tiempoRestante = this.tiempoTotal;
    }

    int getId() { return id; }
    int getMinutoLlegada() { return minutoLlegada; }
    String getNombrePlato() { return tipo.getNombre(); }
    int getTiempoTotal() { return tiempoTotal; }
    int getTiempoRestante() { return tiempoRestante; }

    boolean procesarMinuto() {
        if (this.tiempoRestante > 0) {
            this.tiempoRestante--;
        }
        return this.tiempoRestante <= 0;
    }
}