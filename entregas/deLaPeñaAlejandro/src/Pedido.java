public class Pedido {

    private String nombre;
    private int tiempoTotal;
    private int tiempoRestante;

    public Pedido(String nombre, int tiempoTotal) {
        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
    }

    public void procesarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return "[" + nombre + " - " + tiempoRestante + " min restantes]";
    }
}