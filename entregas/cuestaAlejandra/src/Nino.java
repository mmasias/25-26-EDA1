import java.util.*;
import java.util.stream.Collectors;

class Nino {
    String nombre;
    int edad;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public void presentarse() {
        System.out.println("Hola, me llamo " + nombre + " y tengo " + edad + " años.");
    }

    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}