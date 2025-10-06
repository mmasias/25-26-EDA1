package TelefonoDescacharrado2;

public class Niño {
    private String nombre;
    private int edad;
    private Niño siguiente; // enlace para la lista (cola)

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Niño getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Niño siguiente) {
        this.siguiente = siguiente;
    }

    public void presentarse() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años");
    }
}
