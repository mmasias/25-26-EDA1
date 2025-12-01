public class Order implements Comparable<Order> {

    private String tipo;
    private int tiempoTotal;
    private int tiempoRestante;
    private int llegada;

    public Order(String tipo, int tiempoTotal, int llegada) {
        this.tipo = tipo;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.llegada = llegada;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public int getLlegada() {
        return llegada;
    }

    public void procesarUnMinuto() {
        tiempoRestante--;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.tiempoRestante, o.tiempoRestante);
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
}
