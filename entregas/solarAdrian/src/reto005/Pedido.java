package reto005;

public class Pedido implements Comparable<Pedido> {
    private String tipo;
    private int tiempoTotal;
    private int tiempoRestante;

    public static int comparacionesTotales = 0;

    public Pedido(String tipo, int tiempoTotal) {
        this.tipo = tipo;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void procesarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    @Override
    public int compareTo(Pedido otro) {
        comparacionesTotales++;

        return Integer.compare(this.tiempoTotal, otro.tiempoTotal);
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoTotal + " min)";
    }
}
