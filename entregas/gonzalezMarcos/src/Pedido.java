public class Pedido {
    String tipo;
    int tiempoTotal;
    int tiempoRestante;
    int minutoLlegada;

    public Pedido(String tipo, int tiempoTotal, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.minutoLlegada = minutoLlegada;
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoTotal + " min)";
    }
}