public class Nino {
    private String nombre;
    private String mensajeEnPizarra = "";

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public void limpiarPizarra() {
        mensajeEnPizarra = "";
    }

    public void escribirMensaje(String mensaje) {
        mensajeEnPizarra = mensaje;
    }

    public String entregarMensaje() {
        return mensajeEnPizarra;
    }

    public String getNombre() {
        return nombre;
    }
}
