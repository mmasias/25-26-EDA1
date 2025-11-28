public class Pedido {
    Plato plato;
    int tiempoTotal;
    int tiempoRestante;
    int llegada;

    public Pedido(Plato plato, int tiempoTotal, int llegada) {
        this.plato = plato;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.llegada = llegada;
    }
}
