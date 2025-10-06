package reto002;

public class Ninio {
    private String nombre;
    private int edad;

    public Ninio(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public String presentacionCompleta() {
        return "Hola, soy " + nombre + " y tengo " + edad + " años";
    }

    public String presentacionNombre() {
        return "Hola, soy " + nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
