public class Niño {
    private final String nombre;
    private final int edad;
    private final Console console;
    
    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.console = new Console();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void presentarse() {
        console.writeln("Hola, soy " + nombre + " y tengo " + edad + " años");
    }
    
    public void presentarseSolo() {
        console.writeln("Hola, soy " + nombre);
    }
    
    public String obtenerInformacion() {
        return nombre + " (" + edad + " años)";
    }
}