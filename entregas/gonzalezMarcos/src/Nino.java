public class Nino {
    private String nombre;
    private String pizarra;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarra = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void limpiarPizarra() {
        pizarra = "";
    }

    public String escribirEnPizarra(String mensaje) {
        if (mensaje.isEmpty()) return mensaje;

        int pos = (int) (Math.random() * mensaje.length());

        char letraNueva = (char) ('A' + (int) (Math.random() * 26));

        StringBuilder sb = new StringBuilder(mensaje);
        sb.setCharAt(pos, letraNueva);

        pizarra = sb.toString();

        return pizarra;
    }

    @Override
    public String toString() {
        return nombre + " escribió: " + pizarra;
    }
}