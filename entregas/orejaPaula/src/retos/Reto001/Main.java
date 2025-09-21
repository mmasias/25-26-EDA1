import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Fila fila = new Fila();
        JuegoTelefono juego = new JuegoTelefono(fila);
        List<Nino> esperaLidia = new ArrayList<>();
        Random random = new Random();
        int tiempo = 0;
        int tiempoTotal = 120;
        int contadorNinos = 1;

        while (tiempo < tiempoTotal) {
            if (tiempo < 10) {
                int nuevos = random.nextInt(3);
                for (int i = 0; i < nuevos; i++) {
                    if (fila.tamano() > 5) {
                        esperaLidia.add(new Nino("Niño" + contadorNinos++));
                    } else {
                        fila.agregarNino(new Nino("Niño" + contadorNinos++));
                    }
                }
            } else if (tiempo < 30) {
                if (tiempo % 3 == 0 && random.nextBoolean()) {
                    if (fila.tamano() > 5) {
                        esperaLidia.add(new Nino("Niño" + contadorNinos++));
                    } else {
                        fila.agregarNino(new Nino("Niño" + contadorNinos++));
                    }
                }
            }

            if (fila.tamano() > 5 && tiempo % 15 == 0) {
                Mensaje mensaje = Utils.generarMensajeAleatorio(10);
                System.out.println("\n--- Nuevo Juego ---");
                System.out.println("Mensaje original: " + mensaje.obtenerTexto());
                juego.jugar(mensaje, esperaLidia);
            }

            tiempo++;
        }
    }
}

