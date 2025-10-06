public class Nino {
    private final String nombre;
    private final int edad;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad < 0 ? 0 : edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public String presentacionCompleta() {
        return "Hola, soy " + nombre + " y tengo " + edad + " años";
    }

    public String presentacionSimple() {
        return "Hola, soy " + nombre;
    }
}
