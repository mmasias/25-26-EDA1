import java.util.Random;

public class TelefonoDescacharrado {
    public static void main(String[] args) {
        Random rand = new Random();
        Ludoteca ludoteca = new Ludoteca();
        int tiempoTotal = 120; 

        for (int minuto = 0; minuto < tiempoTotal; minuto++) {
            System.out.println("Minuto: " + minuto);

         
            if (minuto < 10) {
                int nuevos = rand.nextInt(3); 
                for (int i = 0; i < nuevos; i++) {
                    ludoteca.recibirEnCola(new Nino("Niño#" + (minuto*10 + i + 1)));
                    System.out.println("Llega " + "Niño#" + (minuto*10 + i + 1));
                }
            } else if (minuto < 30 && minuto % 3 == 0) {
                if (rand.nextDouble() < 0.5) {
                    ludoteca.recibirEnCola(new Nino("Niño#" + (minuto + 1)));
                    System.out.println("Llega " + "Niño#" + (minuto + 1));
                }
            }

           
            if (ludoteca.haySuficientesParaJugar()) {
                Juego juego = ludoteca.iniciarJuego(rand);

                
                for (int i = 0; i < juego.getNumeroJugadores() + 2; i++) { 
                    
                    juego.ejecutarUnMinuto(rand, i);
                    minuto++;
                    if (minuto >= tiempoTotal) break;
                }

                ludoteca.moverEsperaACola();
            }
        }

        System.out.println("Simulación de la ludoteca finalizada.");
    }
}
