package entregas.caicedoFernando.src;

public class Pedido {
    public String tipo;
    public int tiempoPreparacion;
    public int tiempoRestante;

    public Pedido(String tipo, int tiempoPreparacion, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
    }
}