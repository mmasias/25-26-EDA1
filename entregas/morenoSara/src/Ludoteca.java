import java.util.Random;

public class Ludoteca {
    private static final int DURACION = 120;
    private static final int MAX_NINOS = 100;
    
    private Nino[] cola = new Nino[MAX_NINOS];
    private int topeCola = 0;

    private Nino[] salaEspera = new Nino[MAX_NINOS];
    private int topeEspera = 0;

    private int contadorNinos = 1;
    private Random random = new Random();

    public void iniciar() {
        for (int minuto = 0; minuto < DURACION; minuto++) {
            llegadaDeNinos(minuto);

            if (topeCola > 5) {
                Juego juego = new Juego(cola, topeCola);
                juego.iniciar(minuto);

                // Mover niños de la sala de espera a la cola
                for (int i = 0; i < topeEspera; i++) {
                    cola[topeCola++] = salaEspera[i];
                }
                topeEspera = 0;
            }
        }
    }

    private void llegadaDeNinos(int minuto) {
        int llegadas = 0;
        if (minuto < 10) {
            llegadas = random.nextInt(3);
        } else if (minuto < 30) {
            if (minuto % 3 == 0 && random.nextBoolean()) {
                llegadas = 1;
            }
        }

        for (int i = 0; i < llegadas; i++) {
            Nino nino = new Nino("Niño" + (contadorNinos++));
            if (topeCola > 5) {
                salaEspera[topeEspera++] = nino;
            } else {
                cola[topeCola++] = nino;
            }
        }
    }
}
