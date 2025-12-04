public class Pedido {
    String tipo;
    int tiempoPreparacion;
    int tiempoLlegada;
    int tiempoRestante;

    public Pedido(String tipo, int tiempoPreparacion, int tiempoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoRestante = tiempoPreparacion;
    }
}
