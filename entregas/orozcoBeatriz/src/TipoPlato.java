import java.util.Random;

public class TipoPlato {

    private String nombre;
    private int tiempoMinimo;
    private int tiempoMaximo;

    public TipoPlato(String nombre, int tiempoMinimo, int tiempoMaximo) {
        this.nombre = nombre;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public int obtenerTiempoAleatorio(Random r) {
        int rango = tiempoMaximo - tiempoMinimo + 1;
        return tiempoMinimo + r.nextInt(rango);
    }

    public static TipoPlato obtenerAleatorio(Random r) {
        return TIPOS[r.nextInt(TIPOS.length)];
    }

    public static final TipoPlato[] TIPOS = {
            new TipoPlato("Bebida", 1, 2),
            new TipoPlato("Café", 2, 3),
            new TipoPlato("Colacao", 2, 4),
            new TipoPlato("Bocadillo", 3, 5),
            new TipoPlato("Ensalada", 5, 8)
    };
}
