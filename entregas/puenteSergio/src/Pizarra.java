class Pizarra {
    private String mensaje;

    public Pizarra() {
        this.mensaje = "";
    }

    public void escribirMensaje(String mensaje) {
        this.mensaje = mensaje != null ? mensaje : "";
    }

    public String leerMensaje() {
        return this.mensaje;
    }

    public void limpiar() {
        this.mensaje = "";
    }

    public boolean pizarraLimpia() {
        return mensaje.length() == 0;
    }

    public void mostrarTitulo(String titulo) {
        System.out.println("\n========================================");
        System.out.println("   " + titulo.toUpperCase());
        System.out.println("========================================\n");
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
