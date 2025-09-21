public class Pizarra {
    private String mensaje = "";

    public void setMensaje(String mensaje) {
        this.mensaje = (mensaje == null) ? "" : mensaje;
    }

    public void limpiar() { this.mensaje = ""; }

    public void imprimir() { System.out.println("Pizarra: \"" + mensaje + "\""); }

    public String getMensaje() { return mensaje; }
}
