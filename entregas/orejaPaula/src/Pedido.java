public class Pedido {
    public final String nombre;
    public final int tiempoPreparacion;
    public int tiempoRestante;
    public final int llegada;
    public int inicio;

    public Pedido(String nombre, int tiempoPreparacion, int llegada) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.llegada = llegada;
        this.inicio = -1;
    }

    public boolean terminado() {
        return tiempoRestante <= 0;
    }
}




