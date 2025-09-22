public class Pizarra {
    private String nombre;
    private String contenido;

    public Pizarra(String nombre) {
        this.nombre = nombre;
        this.contenido = "";
    }

    public void limpiar() {
        contenido = "";
        System.out.println(nombre + " ha sido limpiada.");
    }

    public void escribir(String texto) {
        this.contenido = texto;
        System.out.println(nombre + " ahora contiene: " + texto);
    }

    public String getContenido() {
        return contenido;
    }

    public String getNombre() {
        return nombre;
    }
}
