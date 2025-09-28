public class Pizarra {

    private String contenido;

    public Pizarra() {
        this.contenido = "";
    }

    public void limpiar() {
        contenido = "";
    }

    public void escribir(String mensaje) {
        contenido = mensaje;
    }

    public String contenido() {
        return contenido;
    }
}
