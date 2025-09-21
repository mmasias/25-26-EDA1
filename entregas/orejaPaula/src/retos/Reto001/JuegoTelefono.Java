import java.util.List;

public class JuegoTelefono {
    private Fila fila;
    private Mensaje pizarraSalon;

    public JuegoTelefono(Fila fila) {
        this.fila = fila;
        this.pizarraSalon = new Mensaje("");
    }
}
private void esperarUnMinuto() {
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
