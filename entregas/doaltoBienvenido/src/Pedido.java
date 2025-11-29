public class Pedido {
    private static final int INSTANTE_NO_INICIADO = -1;
    private static final int INSTANTE_INICIADO = 1;
    private static final int UMBRAL_COMPLETADO = 0;
    private int id;
    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int instanteLlegada;
    private int instanteInicio;

    public Pedido(int id, String tipo, int tiempoPreparacion, int instanteLlegada) {
        this.id = id;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.instanteLlegada = instanteLlegada;
        this.instanteInicio = INSTANTE_NO_INICIADO;
    }

    public int getId() { 
        return id; 
    }
    public String getTipo() { 
        return tipo; 
    }
    public int getTiempoPreparacion() { 
        return tiempoPreparacion; 
    }
    public int getTiempoRestante() { 
        return tiempoRestante; 
    }
    public int getInstanteLlegada() { 
        return instanteLlegada; 
    }
    public void decrementarTiempoRestante() { 
        tiempoRestante--; 
    }
    public void marcarComoIniciado(int instante) { 
        instanteInicio = instante; 
    }
    public boolean estaCompleto() { 
        return tiempoRestante <= UMBRAL_COMPLETADO; 
    }

    public int compareTo(Pedido otroPedido) {
        if (this.tiempoPreparacion != otroPedido.tiempoPreparacion) {
            return this.tiempoPreparacion < otroPedido.tiempoPreparacion ? INSTANTE_NO_INICIADO : INSTANTE_INICIADO;
        } else if (this.instanteLlegada != otroPedido.instanteLlegada) {
            return this.instanteLlegada < otroPedido.instanteLlegada ? INSTANTE_NO_INICIADO : INSTANTE_INICIADO;
        } else {
            return 0;
        }
    }

    public String toString() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
}
