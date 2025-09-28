import java.util.Random;

public class Juego {
    public static void jugar(Niño[] jugadores, int cantidad, int minutoInicio) {
        System.out.println("Se limpia la pizarra y los niños limpian sus pizarrines");
        String mensaje = generarMensaje();
        System.out.println("Mensaje escrito por Aisha: " + mensaje);

        int tiempo = minutoInicio + 1; // tiempo de Aisha mostrando el mensaje

        for (int i = 0; i < cantidad; i++) {
            tiempo++;
            mensaje = Niño.transmitirPaso(mensaje, jugadores[i].getNombre(), i + 1);
        }

        tiempo++; // tiempo para que el último niño escriba en la pizarra
        System.out.println(" Mensaje final en la pizarra: " + mensaje);
        System.out.println(" Duración del juego: " + (tiempo - minutoInicio) + " minutos\n");
    }

    private static String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int idx = random.nextInt(letras.length());
            mensaje += letras.charAt(idx);
        }
        return mensaje;
    }
}


