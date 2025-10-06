
public class Niño {
    private String nombre;
    private int edad;
    private boolean jugando;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.jugando = false;
    }

    
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean isJugando() { return jugando; }
    public void setJugando(boolean jugando) { this.jugando = jugando; }

    
    public void presentarse() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}
