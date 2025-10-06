package vPRG2.v002;

class Pizarra {
    private String mensaje;

    public Pizarra() { mensaje = ""; }

    public void escribirMensaje(String mensaje) { this.mensaje = mensaje; }

    public String leerMensaje() { return mensaje; }

    public void limpiar() { mensaje = ""; }
}
