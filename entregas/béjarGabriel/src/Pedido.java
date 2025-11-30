
public class Pedido {

    private static int COUNTER = 0;

    private final int id;
    private final String tipo;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private final int llegadaMinuto;

    public Pedido(String tipo, int tiempoPreparacion, int llegadaMinuto) {
        this.id = ++COUNTER;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.llegadaMinuto = llegadaMinuto;
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

    public void decrementarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
    
        }}

    public int getLlegadaMinuto() {
        return llegadaMinuto;
    }

    @Override
    public String toString() {
        return String.format("Pedido[id=%d, %s, t=%d]", id, tipo, tiempoRestante);
    }
}
