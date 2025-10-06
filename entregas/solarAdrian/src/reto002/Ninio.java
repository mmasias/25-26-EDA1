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

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
