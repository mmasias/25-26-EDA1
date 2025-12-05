public class Pedido {
    private String nombrePlato;
    private int tiempoTotal;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(String nombrePlato, int tiempoTotal, int minutoLlegada) {
        this.nombrePlato = nombrePlato;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.minutoLlegada = minutoLlegada;
    }

    public void procesarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    @Override
    public String toString() {
        return nombrePlato + " (" + tiempoTotal + " min)";
    }
}