public class Pedido {
    private static final int ORDEN_ANTERIOR = -1;
    private static final int ORDEN_IGUAL = 0;
    private static final int ORDEN_POSTERIOR = 1;

    private int id;
    private String tipo;
    private int tiempoPreparacion;
    
    public Pedido(int id, String tipo, int tiempoPreparacion) {
        this.id = id;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
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
    
    public int compareTo(Pedido otroPedido) {
        if (this.tiempoPreparacion != otroPedido.tiempoPreparacion) {
            return this.tiempoPreparacion < otroPedido.tiempoPreparacion ? ORDEN_ANTERIOR : ORDEN_POSTERIOR;
        }

        if (this.id != otroPedido.id) {
            return this.id < otroPedido.id ? ORDEN_ANTERIOR : ORDEN_POSTERIOR;
        }

        return ORDEN_IGUAL;
    }

    public String toString() {
        return tipo + " (prep: " + tiempoPreparacion + " min)";
    }
}
