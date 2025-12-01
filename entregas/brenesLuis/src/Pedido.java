
public class Pedido {

    private final String TIPO;
    private final int TIEMPO_TOTAL;
    private int TIEMPO_RESTANTE;
    private final int MINUTO_LLEGADA;

    public Pedido(String tipo, int tiempoTotal, int minutoLlegada) {
        this.TIPO = tipo;
        this.TIEMPO_TOTAL = tiempoTotal;
        this.MINUTO_LLEGADA = minutoLlegada;
        this.TIEMPO_RESTANTE = tiempoTotal;
    }

    public void cocinar() {
        if (this.TIEMPO_RESTANTE > 0) {
            this.TIEMPO_RESTANTE--;
        }
    }

    public boolean estaTerminado() {
        return this.TIEMPO_RESTANTE <= 0;
    }

    public int getTiempoTotal() {
        return this.TIEMPO_TOTAL;
    }

    public String getTipo() {
        return this.TIPO;
    }

    public int getMinutoLlegada() {
        return this.MINUTO_LLEGADA;
    }

    @Override
    public String toString() {
        return String.format("%s (%d min)", this.TIPO, this.TIEMPO_RESTANTE);
    }
}
