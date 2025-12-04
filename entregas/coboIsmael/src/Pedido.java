public class Pedido {

    private int id;
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(int id, String tipo, int tiempoPreparacion, int minutoLlegada) {
        this.id = id;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.minutoLlegada = minutoLlegada;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public String describir() {
        return tipo + " (" + tiempoPreparacion + " min)";
    }
}