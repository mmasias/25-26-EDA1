public class Pedido {
    private String tipo;
    private int tiempoTotal;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(String tipo, int tiempo, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoTotal = tiempo;
        this.tiempoRestante = tiempo;
        this.minutoLlegada = minutoLlegada;
    }

    public String getTipo() { return tipo; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getTiempoTotal() { return tiempoTotal; }
    public int getMinutoLlegada() { return minutoLlegada; }

    public void procesarMinuto() {
        if (tiempoRestante > 0) tiempoRestante--;
    }

    public String toString() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
}
