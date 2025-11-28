public class Pedido {

    private Plato plato;
    private int tiempoPreparacionInicial;
    private int tiempoRestante;
    private int instanteLlegada;
    private int instanteComienzo;

    public Pedido(Plato plato, int instanteLlegada) {
        this.plato = plato;
        tiempoPreparacionInicial = plato.obtenerTiempoPreparacion();
        tiempoRestante = tiempoPreparacionInicial;
        this.instanteLlegada = instanteLlegada;
        instanteComienzo = -1;
    }

    public Plato obtenerPlato() {
        return plato;
    }

    public int obtenerTiempoRestante() {
        return tiempoRestante;
    }

    public void reducirTiempoRestante() {
        tiempoRestante = tiempoRestante - 1;
    }

    public void establecerInstanteComienzo(int instante) {
        instanteComienzo = instante;
    }

    public int calcularTiempoEspera() {
        return instanteComienzo - instanteLlegada;
    }

    public String obtenerNombrePlato() {
        return plato.obtenerNombre();
    }
}