public class Niño {
    private String nombre;
    private int edad;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String presentarse() {
        return "Hola, soy " + nombre + " y tengo " + edad + " años";
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
