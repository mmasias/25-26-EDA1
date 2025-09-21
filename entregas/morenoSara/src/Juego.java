import java.util.Random;

public class Juego {
    public static void jugar(Nino[] jugadores, int cantidad, int minutoInicio) {
        String mensaje = generarMensaje();
        System.out.println("\n Nuevo juego comienza en minuto " + minutoInicio);
        System.out.println("Mensaje original: " + mensaje);

        int tiempo = minutoInicio;

        for (int i = 0; i < cantidad; i++) {
            tiempo++;
            mensaje = Nino.transmitirPaso(mensaje, jugadores[i].getNombre(), i + 1);
        }

        tiempo++;
        System.out.println("Mensaje final en la pizarra: " + mensaje);
        System.out.println("Duración total del juego: " + (tiempo - minutoInicio) + " min\n");
    }

    private static String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        for (int i = 0; i < 10; i++) {
            int idx = (int)(Math.random() * letras.length());
            mensaje += letras.charAt(idx);
        }
        return mensaje;
    }
}

