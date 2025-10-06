package entregas.gonzalezMarcos.src;

class Pizarra {
    private String mensaje;

    public Pizarra() {
        this.mensaje = "";
    }

    public void escribirMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String leerMensaje() {
        return this.mensaje;
    }

    public void limpiar() {
        this.mensaje = "";
    }

    public boolean pizarraLimpia() {
        return mensaje.length() <= 0;
    }
}