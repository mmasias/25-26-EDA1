package entregas.puenteSergio.src;

public class Pedido implements Comparable<Pedido> {
    private static int contadorId = 1;
    private int id;
    private String tipo;
    private int tiempoTotalPreparacion;
    private int tiempoRestante;
    private double tiempoLlegada;

    public Pedido(String tipo, int tiempoTotalPreparacion, double tiempoLlegada) {
        this.id = contadorId++;
        this.tipo = tipo;
        this.tiempoTotalPreparacion = tiempoTotalPreparacion;
        this.tiempoRestante = tiempoTotalPreparacion;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoTotalPreparacion() {
        return tiempoTotalPreparacion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void disminuirTiempoRestante() {
        if (this.tiempoRestante > 0) {
            this.tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return this.tiempoRestante <= 0;
    }

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    @Override
    public int compareTo(Pedido otro) {
        int comparacionTiempo = Integer.compare(this.tiempoRestante, otro.tiempoRestante);
        if (comparacionTiempo != 0) {
            return comparacionTiempo;
        }
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return String.format("%s (%d min)", tipo, tiempoTotalPreparacion);
    }

    public String aCadenaEstado() {
        return String.format("[%s - %d min restantes]", tipo, tiempoRestante);
    }
}
