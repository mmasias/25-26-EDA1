public class Pedido {

    private int identificador;
    private TipoPlato tipoPlato;
    private int duracionTotal;
    private int instanteLlegada;
    private int tiempoEnEspera;

    public Pedido(int identificador, TipoPlato tipoPlato, int duracionTotal, int instanteLlegada) {
        this.identificador = identificador;
        this.tipoPlato = tipoPlato;
        this.duracionTotal = duracionTotal;
        this.instanteLlegada = instanteLlegada;
        this.tiempoEnEspera = 0;
    }

    public void aumentarUnMinutoDeEspera() {
        tiempoEnEspera++;
    }

    public int identificador() {
        return identificador;
    }

    public int duracionTotal() {
        return duracionTotal;
    }

    public int instanteLlegada() {
        return instanteLlegada;
    }

    public int tiempoEnEspera() {
        return tiempoEnEspera;
    }

    public TipoPlato tipoPlato() {
        return tipoPlato;
    }
}
