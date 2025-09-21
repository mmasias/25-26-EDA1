import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Juego {
    private final Queue<Niño> colaSnapshot;
    private final Mensaje mensajeInicial;
    private final Random rnd;

    private final double probCambios1 = 0.30;
    private final double probCambios2 = 0.10;

    private final List<String> mensajesPorPaso = new ArrayList<>();
    private String mensajeEnPizarraFinal;
    private int tiempoTotalMinutos;

    public Juego(Queue<Niño> colaSnapshot, Random rnd) {
        this.colaSnapshot = new LinkedList<>(colaSnapshot);
        this.rnd = rnd;
        this.mensajeInicial = Mensaje.aleatorio(rnd);
    }

    public int ejecutar(boolean imprimirDetalles) {
        mensajesPorPaso.clear();
        String actual = mensajeInicial.getContenido();
        mensajesPorPaso.add("Aisha (inicio) -> " + actual);

        int tiempo = 1;

        for (Niño n : colaSnapshot) {
            double u = rnd.nextDouble();
            int cambios = (u < probCambios2) ? 2 : (u < probCambios2 + probCambios1 ? 1 : 0);

            String nuevo = new Mensaje(actual).deformar(rnd, cambios);
            mensajesPorPaso.add("Niño#" + n.getId() + " copia (cambios=" + cambios + ") -> " + nuevo);
            n.setPizarrin(nuevo);

            actual = nuevo;
            tiempo++;
        }

        tiempo++;
        mensajeEnPizarraFinal = actual;
        tiempoTotalMinutos = tiempo;

        if (imprimirDetalles) {
            System.out.println("  Mensaje inicial (Aisha): " + mensajeInicial);
            for (String paso : mensajesPorPaso) {
                System.out.println("   > " + paso);
            }
            System.out.println("  Mensaje final en pizarra: " + mensajeEnPizarraFinal +
                    " (distancia Hamming = " + mensajeInicial.hamming(mensajeEnPizarraFinal) + ")");
            System.out.println("  Duración juego (minutos): " + tiempoTotalMinutos);
        }
        return tiempoTotalMinutos;
    }

    public Mensaje getMensajeInicial() { return mensajeInicial; }
    public String getMensajeFinalEnPizarra() { return mensajeEnPizarraFinal; }
    public int getDistanciaHamming() { return mensajeInicial.hamming(mensajeEnPizarraFinal); }
    public int getNumeroParticipantes() { return colaSnapshot.size(); }
}