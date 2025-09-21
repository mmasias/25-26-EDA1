import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private ArrayList<Nino> fila;
    private Random random;
    private String mensajeOriginal;
    private String mensajeFinal;
    private int duracion;

    public Juego(ArrayList<Nino> fila, Random random) {
        this.fila = fila;
        this.random = random;
    }

    public void jugar() {
        mensajeOriginal = Tiempo.generarMensaje(random);

        mensajeFinal = mensajeOriginal;
        for (int i = 0; i < fila.size(); i++) {
            mensajeFinal = Tiempo.deformar(mensajeFinal, random);
        }

        duracion = fila.size() + 2;

        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println("Mensaje final:    " + mensajeFinal);
        System.out.println("Niños en la fila: " + fila.size());
        System.out.println("Duración: " + duracion + " minutos\n");
    }

    public int getDuracion() {
        return duracion;
    }
}
