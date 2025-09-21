import java.util.Random;

public class Juego {
    private Nino[] jugadores;
    private int cantidadJugadores;
    private Random random = new Random();

    public Juego(Nino[] jugadores, int cantidadJugadores) {
        this.jugadores = jugadores;
        this.cantidadJugadores = cantidadJugadores;
    }

    public void iniciar(int minutoInicio) {
        String mensajeOriginal = generarMensaje();
        String mensaje = mensajeOriginal;
        int tiempo = minutoInicio;

        for (int i = 0; i < cantidadJugadores; i++) {
            tiempo++;
            mensaje = jugadores[i].transmitir(mensaje);
        }
        tiempo++;

        System.out.println("Inicio: " + mensajeOriginal + " | Final: " + mensaje + " | Duración: " + (tiempo - minutoInicio) + " min");
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        return sb.toString();
    }
}
