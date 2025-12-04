public class Pedido {
    public String nombre;
    public int tiempoTotal;
    public int tiempoRestante;
    public int minutoLlegada;

    public Pedido(String nombre, int tiempoTotal, int minutoLlegada) {
        
        assert nombre != null : "El nombre del pedido no puede ser nulo";
        assert tiempoTotal > 0 : "El tiempo de preparación debe ser positivo";
        assert minutoLlegada > 0 : "El minuto de llegada debe ser > 0";

        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.tiempoRestante = tiempoTotal;
        this.minutoLlegada = minutoLlegada;
    }

    @Override
    public String toString() {
        return nombre + " (" + tiempoRestante + " min rest)";
    }
    
    public String descripcionCompleta() {
        return nombre + " (" + tiempoTotal + " min)";
    }
}