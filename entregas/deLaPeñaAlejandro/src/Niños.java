public class Ninio {
    private String nombre;
    private int edad;

    public Ninio(String nombre, int edad) {
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
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años");
    }

    public boolean nombreEmpiezaPor(char letra) {
        char inicial = Character.toUpperCase(nombre.charAt(0));
        return inicial == Character.toUpperCase(letra);
    }

    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}


