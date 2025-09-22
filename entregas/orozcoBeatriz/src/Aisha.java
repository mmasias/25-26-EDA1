public class Aisha {
    private Fila fila;

    public Aisha(Fila fila) {
        this.fila = fila;
    }

    public void agregarAFila(Nino n) {
        fila.agregar(n);
        System.out.println("Aisha pone en la fila a " + n.getNombre());
    }

    public void iniciarJuego() {
        TextoAleatorio generador = new TextoAleatorio();
        TelefonoDescacharrado dinamica = new TelefonoDescacharrado();
        Juego juego = new Juego(fila, generador, dinamica);
        juego.jugar();
    }
}
