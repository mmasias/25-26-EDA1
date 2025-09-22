public class Nino {
    private String nombre;
    private String mensajeEnPizarra = "";

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public void limpiarPizarrin() {
        mensajeEnPizarra = "";
    }

    public void recibirMensaje(String mensaje, TelefonoDescacharrado dinamica) {
        mensajeEnPizarra = dinamica.deformarMensaje(mensaje);
    }

    public String entregarMensaje() {
        return mensajeEnPizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void escribirMensaje(String mensaje) {
        mensajeEnPizarra = mensaje;
    }
}