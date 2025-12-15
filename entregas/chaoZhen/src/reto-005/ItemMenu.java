public class ItemMenu {
    private String nombre;
    private int tiempoMinimo;
    private int tiempoMaximo;

    public ItemMenu(String nombre, int tiempoMinimo, int tiempoMaximo) {
        this.nombre = nombre;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerMinTiempo() {
        return tiempoMinimo;
    }

    public int obtenerMaxTiempo() {
        return tiempoMaximo;
    }
}
