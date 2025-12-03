public class Pedido {
    private int identificador;
    private TipoPlato tipoPlato;
    private int duracionPreparacion;
    private int tiempoPendiente;
    private int instanteLlegada;
    private int instanteInicio;

    public Pedido(int identificador, TipoPlato tipoPlato, int duracionPreparacion, int instanteLlegada) {
        this.identificador = identificador;
        this.tipoPlato = tipoPlato;
        this.duracionPreparacion = duracionPreparacion;
        this.tiempoPendiente = duracionPreparacion;
        this.instanteLlegada = instanteLlegada;
        this.instanteInicio = -1;
    }

    public int identificador() {
        return identificador;
    }

    public TipoPlato tipoPlato() {
        return tipoPlato;
    }

    public int duracionPreparacion() {
        return duracionPreparacion;
    }

    public int tiempoPendiente() {
        return tiempoPendiente;
    }

    public int instanteLlegada() {
        return instanteLlegada;
    }

    public int instanteInicio() {
        return instanteInicio;
    }

    public void establecerInstanteInicio(int inicio) {
        this.instanteInicio = inicio;
    }

    public void reducirUnMinutoDePreparacion() {
        if (tiempoPendiente > 0) tiempoPendiente--;
    }

    public boolean haFinalizado() {
        return tiempoPendiente == 0;
    }
}
