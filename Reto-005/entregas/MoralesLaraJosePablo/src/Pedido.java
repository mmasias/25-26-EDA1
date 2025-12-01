public class Pedido {

    private String tipo;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int idOrden;
    private int minutoLlegada;
    
    public Pedido(String tipo, int tiempoPreparacion, int idOrden, int minutoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.idOrden = idOrden;
        this.minutoLlegada = minutoLlegada;
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
    
    public int getIdOrden() {
        return idOrden;
    }
    
    public int getMinutoLlegada() {
        return minutoLlegada;
    }
    
    public void decrementarTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }
    
    public boolean estaCompletado() {
        return tiempoRestante == 0;
    }
    
    public int compararCon(Pedido otro) {
        if (this.tiempoPreparacion != otro.tiempoPreparacion) {
            return Integer.compare(this.tiempoPreparacion, otro.tiempoPreparacion);
        }
        return Integer.compare(this.idOrden, otro.idOrden);
    }
    
    @Override
    public String toString() {
        return tipo + " (" + tiempoRestante + " min restantes)";
    }
}
