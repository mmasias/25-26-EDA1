public class Pedido implements Comparable<Pedido> {
    private String tipo;
    private int tiempoTotal;    
    private int tiempoRestante; 
    private double horaLlegada; 

    public static int comparacionesTotales = 0;

    public Pedido(String tipo, int tiempo, double horaLlegada) {
        this.tipo = tipo;
        this.tiempoTotal = tiempo;
        this.tiempoRestante = tiempo;
        this.horaLlegada = horaLlegada;
    }

    public void cocinar() {
        this.tiempoRestante--;
    }

    public boolean estaTerminado() {
        return this.tiempoRestante <= 0;
    }

    public double getHoraLlegada() {
        return horaLlegada;
    }

    public int compareTo(Pedido otro) {
        comparacionesTotales++;
        
        return Integer.compare(this.tiempoTotal, otro.tiempoTotal);
    }

    public String toString() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
    
    public String getDetalle() {
        return tipo + " (Original: " + tiempoTotal + " min)";
    }
}