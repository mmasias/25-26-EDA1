public class Pedido {
    private String nombrePlato;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(String nombrePlato, int tiempoPreparacion, int minutoLlegada) {

        assert nombrePlato != null : "El nombre no puede ser nulo";
        assert !nombrePlato.isEmpty() : "El nombre no puede estar vacío";
        assert tiempoPreparacion > 0 : "El tiempo debe ser mayor que 0";
        assert minutoLlegada >= 0 : "El minuto de llegada debe ser válido";

        this.nombrePlato = nombrePlato;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.minutoLlegada = minutoLlegada;
    }

    public void restarMinuto() {
        if (tiempoRestante > 0)
            tiempoRestante = tiempoRestante - 1;
    }

    public boolean estaTerminado() {
        if (tiempoRestante <= 0)
            return true;
        else
            return false;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }
}
