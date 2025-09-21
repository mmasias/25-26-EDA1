public class Pizarrin {

    private String mensaje;

    public Pizarrin() {
        this.mensaje = "";
    }

    public void limpiar() {
        this.mensaje = "";
    }

    public void escribir(String mensaje) {
        this.mensaje = mensaje;
    }

    public String leer() {
        return mensaje;
    }
}