package entregas.caicedoFernando.src;


public class Pedido {
    String tipo;
    int tiempoPreparacion;
    int tiempoRestante;
    int minutoLlegada;

    public Pedido(String tipo, int tiempoPreparacion, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.minutoLlegada = minutoLlegada;
    }
}   