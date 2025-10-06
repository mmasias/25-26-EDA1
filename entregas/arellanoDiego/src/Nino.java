public class Nino {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.pizarrin = null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void recibirPizarrin(Pizarra p) {
        this.pizarrin = p;
    }

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
