public class Juego {
    private Fila fila;
    private TextoAleatorio generador;
    private TelefonoDescacharrado dinamica;

    public Juego(Fila fila, TextoAleatorio generador, TelefonoDescacharrado dinamica) {
        this.fila = fila;
        this.generador = generador;
        this.dinamica = dinamica;
    }

    public void jugar() {
        System.out.println("\n--- NUEVO JUEGO ---");

        for (Nino n : fila.getLista()) {
            n.limpiarPizarra();
        }

        String mensajeOriginal = generador.generar();
        System.out.println("Mensaje original: " + mensajeOriginal);

        String mensaje = mensajeOriginal;
        for (Nino n : fila.getLista()) {
            n.recibirMensaje(mensaje, dinamica);
            mensaje = n.entregarMensaje();
            System.out.println(n.getNombre() + " pasa: " + mensaje);
        }

        System.out.println("Mensaje final en la pizarra: " + mensaje);
    }
}
