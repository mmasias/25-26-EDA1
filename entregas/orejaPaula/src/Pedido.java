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

    public void decrementarTiempo() {
        tiempoRestante--;
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    public Plato getPlato() {
        return plato;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getInstanteLlegada() {
        return instanteLlegada;
    }

    public int getInstanteInicio() {
        return instanteInicio;
    }

    public void setInstanteInicio(int instanteInicio) {
        this.instanteInicio = instanteInicio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getId() {
        return id;
    }
}




