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

    public void trabajarUnMinuto() {
        tiempoRestante--;
    }

    public boolean terminado() {
        return tiempoRestante <= 0;
    }

    public String mostrar() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
}
