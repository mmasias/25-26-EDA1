import java.util.Random;

public class Ludoteca {
    private Fila fila = new Fila();
    private LidiaMonitora lidia = new LidiaMonitora("Lidia");
    private AishaMonitora aisha = new AishaMonitora("Aisha");
    private Random random = new Random();
    private int contadorNinos = 1;
    private boolean juegoEnCurso = false;

    public void simular() {
        int tiempo = 0;

        while (tiempo < 120) {
            if (!juegoEnCurso) {
                if (tiempo < 10) {
                    int nuevos = random.nextInt(3);
                    for (int i = 0; i < nuevos; i++) {
                        Nino n = new Nino("Niño" + (contadorNinos++));
                        lidia.recibirNino(n, false);
                        aisha.agregarAFila(n);
                    }
                } else if (tiempo < 30) {
                    if (tiempo % 3 == 0 && random.nextBoolean()) {
                        Nino n = new Nino("Niño" + (contadorNinos++));
                        lidia.recibirNino(n, false);
                        aisha.agregarAFila(n);
                    }
                }

                if (fila.size() >= 5) {
                    juegoEnCurso = true;
                    int nJugadores = fila.size();
                    int duracion = nJugadores + 2;
                    aisha.iniciarJuego();

                    for (int m = 0; m < duracion && tiempo < 120; m++) {
                        if (tiempo < 10) {
                            int nuevos = random.nextInt(3);
                            for (int i = 0; i < nuevos; i++) {
                                Nino n = new Nino("Niño" + (contadorNinos++));
                                lidia.recibirNino(n, true);
                            }
                        } else if (tiempo < 30) {
                            if (tiempo % 3 == 0 && random.nextBoolean()) {
                                Nino n = new Nino("Niño" + (contadorNinos++));
                                lidia.recibirNino(n, true);
                            }
                        }
                        tiempo++;
                    }

                    juegoEnCurso = false;
                    Nino[] liberados = lidia.liberarNinos();
                    for (int i = 0; i < liberados.length; i++) {
                        aisha.agregarAFila(liberados[i]);
                    }
                } else {
                    tiempo++;
                }
            } else {
                tiempo++;
            }
        }

        System.out.println("\n--- Cierre de la ludoteca ---");
    }
}
