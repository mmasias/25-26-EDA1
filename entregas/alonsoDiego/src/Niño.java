public class Niño {
    private String nombre;
    private String pizarra;

    public Niño(String nombre) {
        this.nombre = nombre;
        this.pizarra = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getPizarra() {
        return pizarra;
    }

    public void setPizarra(String msg) {
        this.pizarra = msg;
    }

    public String toString() {
        return nombre + " [" + pizarra + "]";
    }
}
