public class Nino {
    private final String identificacion;
    private String contenidoTablero;

    public Nino(String identificacion) {
        this.identificacion = identificacion;
        this.contenidoTablero = "";
    }

    public String obtenerNombre() {
        return identificacion;
    }

    public void resetearTablero() {
        contenidoTablero = "";
    }

    public String modificarTexto(String textoOriginal) {
        if (textoOriginal.isEmpty())
            return textoOriginal;

        int indiceSeleccionado = (int) (Math.random() * textoOriginal.length());
        char caracterNuevo = (char) ('A' + (int) (Math.random() * 26));

        StringBuilder constructor = new StringBuilder(textoOriginal);
        constructor.setCharAt(indiceSeleccionado, caracterNuevo);
        contenidoTablero = constructor.toString();
        return contenidoTablero;
    }

    public String mostrarEstado() {
        return identificacion + " escribió: " + contenidoTablero;
    }
}
