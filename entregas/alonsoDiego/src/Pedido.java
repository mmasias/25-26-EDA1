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

    public void restarMinuto() {
        if (tiempoRestante > 0)
            tiempoRestante = tiempoRestante - 1;
    }

    public boolean estaTerminado() {
        if (tiempoRestante <= 0)
            return true;
        else
            return false;
    }

    public String getNombrePlato() {
        return nombrePlato;
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
}
