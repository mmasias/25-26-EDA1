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

    public boolean nombreEmpiezaCon(char letra) {
        char inicial = Character.toUpperCase(nombre.charAt(0));
        char objetivo = Character.toUpperCase(letra);
        return inicial == objetivo;
    }

    public void presentarse() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años");
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
