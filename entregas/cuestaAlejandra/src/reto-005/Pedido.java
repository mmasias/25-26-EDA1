public class Pedido {
    String tipo;
    int tiempoPreparacion;
    int tiempoRestante;
    int minutoLlegada;

    public Pedido(String tipo, int tiempo, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempo;
        this.tiempoRestante = tiempo;
        this.minutoLlegada = minutoLlegada;
    }

    public String toString() {
        return tipo + " (" + tiempoPreparacion + " min)";
    }
}