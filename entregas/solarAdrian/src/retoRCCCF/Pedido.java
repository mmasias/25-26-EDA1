package retoRCCCF;

import java.util.Random;

public class Pedido implements Comparable<Pedido> {
    private String tipo;
    private int tiempoTotal;
    private int tiempoRestante;
    private int minutoLlegada;

    // Contador estático para las comparaciones
    public static int comparaciones = 0;

    public Pedido(String tipo, int tiempoTotal, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.minutoLlegada = minutoLlegada;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void decrementarTiempo() {
        if (this.tiempoRestante > 0) {
            this.tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return this.tiempoRestante <= 0;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    @Override
    public int compareTo(Pedido otro) {
        comparaciones++;
        // Ordenar por tiempo total de preparación (SJF - Shortest Job First)
        return Integer.compare(this.tiempoTotal, otro.tiempoTotal);
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoTotal + " min)";
    }

    public String estadoString() {
        return "[" + tipo + " - " + tiempoRestante + " min restantes]";
    }
}
