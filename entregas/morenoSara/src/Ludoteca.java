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
            llegadas = random.nextInt(3);
        } else if (minuto < 30) {
            if (minuto % 3 == 0 && random.nextBoolean()) {
                llegadas = 1;
            }
        }

        for (int i = 0; i < llegadas; i++) {
            Nino n = new Nino("Niño" + (contadorNinos++));
            if (topeCola > 5) {
                salaEspera[topeEspera++] = n;
                System.out.println(n.getNombre() + " llegó y espera en sala.");
            } else {
                cola[topeCola++] = n;
                System.out.println(n.getNombre() + " llegó a la cola.");
            }
        }
    }
}


