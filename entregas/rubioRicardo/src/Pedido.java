
public class Pedido {
    private String tipo;
    private int tiempoPreparacion; // El tiempo original
    private int minutoLlegada;

    public Pedido(String tipo, int tiempoPreparacion, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.minutoLlegada = minutoLlegada;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

 
    public String toString() {
        return tipo + " (" + tiempoPreparacion + " min)";
    }
}
