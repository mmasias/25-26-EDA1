public class Pedido {
    private final int id;
    private final Plato plato;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private final int instanteLlegada;
    private int instanteInicio;

    public Pedido(int id, Plato plato, int tiempoPreparacion, int instanteLlegada) {
        this.id = id;
        this.plato = plato;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.instanteLlegada = instanteLlegada;
        this.instanteInicio = -1;
    }
}


