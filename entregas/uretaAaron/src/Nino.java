public class Nino {
    private String nombre;
    private int edad;

    public Nino(String nombre, int edad) {
        this.nombre = nombre.trim();
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String presentacionCompleta() {
        return String.format("Hola, soy %s y tengo %d años", nombre, edad);
    }

    public String presentacionNombre() {
        return String.format("Hola, soy %s", nombre);
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
