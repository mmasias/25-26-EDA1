import java.util.Random;

public class Juego {
    private Nino[] fila;
    private int cantidad;
    private Random random;
    private String mensajeOriginal;
    private String mensajeFinal;
    private int duracion;

    public Juego(Nino[] fila, int cantidad, Random random) {
        this.fila = fila;
        this.cantidad = cantidad;
        this.random = random;
    }

    public void jugar() {
        mensajeOriginal = Tiempo.generarMensaje(random);

        mensajeFinal = mensajeOriginal;
        for (int i = 0; i < cantidad; i++) {
            mensajeFinal = Tiempo.deformar(mensajeFinal, random);
        }

        duracion = cantidad + 2;

        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println("Mensaje final:    " + mensajeFinal);
        System.out.println("Niños en la fila: " + cantidad);
        System.out.println("Duración: " + duracion + " minutos\n");
    }

    public int getDuracion() {
        return duracion;
    }
}
