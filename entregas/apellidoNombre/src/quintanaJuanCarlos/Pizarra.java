public class Pizarra {
    private String mensaje = "";

    public void escribirMensaje(String m) { this.mensaje = (m == null ? "" : m); }
    public String leerMensaje() { return mensaje; }
    public void limpiar() { this.mensaje = ""; }
    public boolean estaLimpia() { return mensaje == null || mensaje.isEmpty(); }
}
