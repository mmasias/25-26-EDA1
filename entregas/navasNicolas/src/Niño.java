import utils.Console;

class Niño {
    private String nombre;
    private int edad;
    private Niño siguienteNiño;
    private Niño anteriorNiño;

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

    public Niño getSiguienteNiño() {
        return siguienteNiño;
    }

    public void setSiguienteNiño(Niño niño) {
        this.siguienteNiño = niño;
    }

    public Niño getAnteriorNiño() {
        return anteriorNiño;
    }

    public void setAnteriorNiño(Niño niño) {
        this.anteriorNiño = niño;
    }

    public void presentarse(Console console) {
        console.writeln(nombre + ": Hola, soy " + nombre + " y tengo " + edad + " años");
    }
}
