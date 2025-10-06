public abstract class Persona {
    protected String nombre;

    public Persona() {
        this.nombre = "";
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        String valor;
        valor = this.nombre;
        return valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String presentacion();
}
