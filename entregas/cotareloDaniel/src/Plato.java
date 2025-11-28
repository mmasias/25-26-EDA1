public abstract class Plato {

    private String nombre;
    private int tiempoPreparacion;

    public Plato(String nombre, int tiempoPreparacion) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerTiempoPreparacion() {
        return tiempoPreparacion;
    }
}