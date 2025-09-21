import java.util.Random;

public class Juego {
    public static void jugar(Nino[] jugadores, int cantidad, int minutoInicio) {
        String mensaje = generarMensaje();
        String original = mensaje;
        int tiempo = minutoInicio;

        for (int i = 0; i < cantidad; i++) {
            tiempo++;
            mensaje = Nino.transmitir(mensaje);
        }
        tiempo++;

        System.out.println("Inicio: " + original + " | Final: " + mensaje + " | Duración: " + (tiempo - minutoInicio) + " min");
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

