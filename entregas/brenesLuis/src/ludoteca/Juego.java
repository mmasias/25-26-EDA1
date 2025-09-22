public class Juego {
    private Cola cola;
    private Mensaje mensajeOriginal;
    private Mensaje mensajeFinal;
    private int duracion;

    public Juego(Cola cola, Mensaje mensajeOriginal) {
        this.cola = cola;
        this.mensajeOriginal = mensajeOriginal;
    }

    public void jugar() {
        duracion = 0;
        Mensaje actual = mensajeOriginal;
        duracion++;

        int cantidad = cola.size();
        Ninno[] elementos = cola.getElementos();
        for (int i = 0; i < cantidad; i++) {
            actual = actual.copiarConErrores();
            duracion++;
        }

        duracion++;
        mensajeFinal = actual;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getMensajeOriginal() {
        return mensajeOriginal.getTexto();
    }

    public String getMensajeFinal() {
        return mensajeFinal.getTexto();
    }
}
