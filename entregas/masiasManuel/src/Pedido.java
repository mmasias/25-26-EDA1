public class Pedido {
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(String tipo, int tiempoPreparacion) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoEspera = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void incrementarEspera() {
        tiempoEspera++;
    }
}
