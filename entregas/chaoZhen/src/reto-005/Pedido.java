public class Pedido {
    private Plato plato;
    private int tiempoTotal;
    private int tiempoRestante;
    private int horaLlegada;

    public Pedido(Plato plato, int horaLlegada) {
        this.plato = plato;
        this.horaLlegada = horaLlegada;
        this.tiempoTotal = (int) (Math.random() * (plato.obtenerMaxTiempo() - plato.obtenerMinTiempo() + 1) + plato.obtenerMinTiempo());
        this.tiempoRestante = this.tiempoTotal;
    }

    public void reducirTiempo(int minutos) {
        this.tiempoRestante -= minutos;
        if (this.tiempoRestante < 0) {
            this.tiempoRestante = 0;
        }
    }

    public boolean estaTerminado() {
        return this.tiempoRestante <= 0;
    }

    public int obtenerTiempoRestante() {
        return tiempoRestante;
    }
    
    public String obtenerNombrePlato() {
        return plato.obtenerNombre();
    }

    @Override
    public String toString() {
        return "Pedido: " + plato.obtenerNombre() + 
               " | Total: " + tiempoTotal + "m | Restante: " + tiempoRestante + "m";
    }
}