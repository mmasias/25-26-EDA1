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
    
    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + " y tengo " + edad + " años");
    }
    
    public void presentarseSinEdad() {
        System.out.println(nombre + ": Hola, soy " + nombre);
    }
    
    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}