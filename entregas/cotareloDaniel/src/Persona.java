public abstract class Persona {
    protected String nombre;

    public Persona(String nombreRecibido) {
        this.nombre = nombreRecibido;
    }

    public String obtenerNombre() {
        String nombreResultado;
        nombreResultado = this.nombre;
        return nombreResultado;
    }
}
