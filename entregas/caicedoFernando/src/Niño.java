public class Niño {
    private final String nombre;
    private final int edad;
    
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
    
    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + " y tengo " + edad + " años");
    }
    
    public void presentarseSolo() {
        System.out.println(nombre + ": Hola, soy " + nombre);
    }
    
    public String obtenerInformacion() {
        return nombre + " (" + edad + " años)";
    }
}