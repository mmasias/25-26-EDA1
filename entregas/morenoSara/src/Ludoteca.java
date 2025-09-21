import java.util.Random;

public class Ludoteca {
    private static final int DURACION = 120;
    private static final int MAX_NINOS = 50;

    private static Nino[] cola = new Nino[MAX_NINOS];
    private static int topeCola = 0;

    private static Nino[] salaEspera = new Nino[MAX_NINOS];
    private static int topeEspera = 0;

    private static int contadorNinos = 1;
    private static Random random = new Random();

    public static void iniciar() {
        for (int minuto = 0; minuto < DURACION; minuto++) {
            llegadaDeNinos(minuto);

            if (topeCola > 5) {
                Juego.jugar(cola, topeCola, minuto);

                // Pasar niños de la sala de espera a la cola
                for (int i = 0; i < topeEspera; i++) {
                    cola[topeCola++] = salaEspera[i];
                }
                topeEspera = 0;
            }
        }
    }

    private static void llegadaDeNinos(int minuto) {
        int llegadas = 0;

        if (minuto < 10) {
            llegadas = random.nextInt(3); // 0-2 niños
        } else if (minuto < 30) {
            if (minuto % 3 == 0 && random.nextBoolean()) {
                llegadas = 1;
            }
        }

        for (int i = 0; i < llegadas; i++) {
            Nino nino= new Nino("Niño" + (contadorNinos++));
            if (topeCola > 5) {
                salaEspera[topeEspera++] = nino;
            } else {
                cola[topeCola++] = nino;
            }
        }
    }
}

