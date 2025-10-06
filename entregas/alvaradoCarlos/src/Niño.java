
public class Niño {

    private String nombre;
    private int edad;
    private Niño siguienteNiño;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.siguienteNiño = null;
    }

    public void presentarse(){
        System.out.println(nombre + ": Hola, soy " + nombre + " y tengo " + edad + " años");
        if (siguienteNiño != null) {
            siguienteNiño.presentarse();
        }
    }

    public void siguienteNiño(Niño siguiente) {
        this.siguienteNiño = siguiente;
    }

    public String nombre() {
        return nombre;
    }

    public int edad() {
        return edad;
    }

}
