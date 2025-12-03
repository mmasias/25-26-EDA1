public class Pedido {
    private String nombre;
    private int tiempo; 

    public Pedido(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public void restarMinuto() {
        if (tiempo > 0) tiempo--;
    }

    public boolean terminado() {
        return tiempo <= 0;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String toString() {
        return "[" + nombre + " - " + tiempo + " min]";
    }
}