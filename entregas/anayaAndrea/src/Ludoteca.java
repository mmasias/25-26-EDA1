import java.util.Random;

public class Ludoteca {
    private Lydia lydia;
    private Aisha aisha;
    private Random random;

    public Ludoteca() {
        lydia = new Lydia();
        aisha = new Aisha();
        random = new Random();
    }

    public void abrir() {
        int tiempoTotal = 120; 
        int tiempo = 0;
        int partida = 1;

        while (tiempo < tiempoTotal) {
            if (tiempo < 10) {
                int nuevos = random.nextInt(3);
                for (int i = 0; i < nuevos; i++) {
                    lydia.recibirNino(new Nino());
                }
            } else if (tiempo < 30) {
                if (tiempo % 3 == 0 && random.nextBoolean()) {
                    lydia.recibirNino(new Nino());
                }
            }

            if (!aisha.estaJugando() && lydia.tieneNinos()) {
                aisha.recibirNinos(lydia.pasarNinos());
            }

            
            if (aisha.puedeJugar()) {
                System.out.println("---- Partida " + partida + " ----");

                Juego juego = new Juego(aisha.getFila(), random);
                juego.jugar();

                tiempo += juego.getDuracion();

                aisha.vaciarFila();
                partida++;
                continue;
            }

            tiempo++;
        }
    }
}
