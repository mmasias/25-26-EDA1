package entregas.caicedoFernando.src;

public class ResultadoExtraccion {
    public Pedido pedidoMinimo;
    public int comparaciones;

    public ResultadoExtraccion(Pedido pedidoMinimo, int comparaciones) {
        this.pedidoMinimo = pedidoMinimo;
        this.comparaciones = comparaciones;
    }
}