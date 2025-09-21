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
public void jugar(Mensaje mensajeInicial, List<Nino> esperaLidia) {
    fila.limpiarPizarras();
    String mensaje = mensajeInicial.obtenerTexto();
}
for (Nino nino : fila.obtenerCola()) {
    esperarUnMinuto();
    nino.recibirMensaje(new Mensaje(mensaje));
    mensaje = nino.entregarMensaje().obtenerTexto();
    System.out.println(nino.getNombre() + " recibió y pasó: " + mensaje);
}
esperarUnMinuto();
pizarraSalon.setTexto(mensaje);
System.out.println("Mensaje final en la pizarra: " + pizarraSalon.obtenerTexto());
for (Nino n : esperaLidia) {
    fila.agregarNino(n);
}
esperaLidia.clear();
