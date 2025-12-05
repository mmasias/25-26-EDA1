
public class Pedido {

    private static int COUNTER = 0;

    private final int id;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private final int llegadaMinuto;

    public Pedido(int tiempoPreparacion, int llegadaMinuto) {
        assert tiempoPreparacion > 0 : "El tiempo de preparación debe ser mayor a 0";
        assert llegadaMinuto >= 0 : "El minuto de llegada no puede ser negativo";

        this.id = ++COUNTER;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.llegadaMinuto = llegadaMinuto;
    }

    public void decrementarMinuto() {
        assert tiempoRestante > 0 : "No se puede decrementar un pedido ya terminado";
        tiempoRestante--;
    }

    public boolean estaTerminado() {
        return tiempoRestante == 0;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getLlegadaMinuto() {
        return llegadaMinuto;
    }

    @Override
    public String toString() {
        return String.format("Pedido[id=%d, t_total=%d, t_rest=%d]", id, tiempoPreparacion, tiempoRestante);
    }
}
