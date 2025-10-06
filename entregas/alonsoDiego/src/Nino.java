public class Nino {
    private String nombre;
    private int edad;
    private String pizarra;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.pizarra = "";
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPizarra() {
        return pizarra;
    }

    public void setPizarra(String msg) {
        this.pizarra = msg;
    }

    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}