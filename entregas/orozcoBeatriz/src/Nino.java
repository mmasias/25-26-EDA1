public class Nino {

    private String nombre;
    private int edad;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Nino(String nombre) {
        this(nombre, 0);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
