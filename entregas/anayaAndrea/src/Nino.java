public class Nino {
    private String nombre;
    private int edad;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + " y tengo " + edad + " años");
    }

    public void presentarseSimple() {
        System.out.println(nombre + ": Hola, soy " + nombre);
    }
}
