public class AishaMonitora extends Monitor {
    private Fila fila = new Fila();

    public AishaMonitora(String nombre) {
        super(nombre);
    }

    public void agregarAFila(Nino n) {
        fila.agregar(n);
        System.out.println(nombre + " pone en la fila a " + n.getNombre());
    }

    public int tamanoFila() {
        return fila.size();
    }

    public void iniciarJuego() {
        TextoAleatorio generador = new TextoAleatorio();
        TelefonoDescacharrado dinamica = new TelefonoDescacharrado();
        Juego juego = new Juego(fila, generador, dinamica);
        juego.jugar();
    }
}

