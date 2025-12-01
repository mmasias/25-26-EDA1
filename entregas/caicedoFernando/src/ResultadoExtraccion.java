package entregas.caicedoFernando.src;

public class ResultadoExtraccion {
    Pedido pedidoMinimo;
    int comparaciones;

    public ResultadoExtraccion(Pedido pedidoMinimo, int comparaciones) {
        this.pedidoMinimo = pedidoMinimo;
        this.comparaciones = comparaciones;
    }
}