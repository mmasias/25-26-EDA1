public class Plato {

    private String nombre;
    private int tiempoPrep;

    public Plato(String nombre, int tiempoPrep) {
        this.nombre = nombre;
        this.tiempoPrep = tiempoPrep;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoPrep() {
        return tiempoPrep;
    }

    @Override
    public String toString() {
        return nombre + " (" + tiempoPrep + " min)";
    }
}
