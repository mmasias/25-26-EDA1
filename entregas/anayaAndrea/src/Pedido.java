public class Pedido {

    private Plato plato;
    private int tiempoRestante;
    private int momentoLlegada;

    public Pedido(Plato plato, int minuto) {
        this.plato = plato;
        this.tiempoRestante = plato.getTiempoPrep();
        this.momentoLlegada = minuto;
    }

    public String getNombre() {
        return plato.getNombre();
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void reducirTiempo() {
        tiempoRestante--;
    }

    public int getMomentoLlegada() {
        return momentoLlegada;
    }

    @Override
    public String toString() {
        return plato.toString() + " - " + tiempoRestante + " min restantes";
    }
}
