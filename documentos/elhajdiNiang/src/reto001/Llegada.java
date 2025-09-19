package reto001;
import java.util.Random;

public class Llegada {
    int tiempo = 0;
    int tiempoLlegada = 30;
    
    
    Random random; 
    
    public Llegada() {
        random = new Random(); 

        for (int i = 0; i < tiempoLlegada; i++) {
            tiempo++;
            int numeroNiños = llegaUnNiño(tiempo); 
            System.out.println("han llegado "+numeroNiños+"niños");
            
            
        }
    }

    public int llegaUnNiño(int minuto) {
        int numeroNiños = 0; 

        if (minuto <= 10) {
            numeroNiños = random.nextInt(3);
            System.out.println("EN LOS PRIMEROS 10 MINUTOS HAN LLEGADO: " + numeroNiños + " NIÑOS");
        } else if (minuto > 10 && minuto <= 30) {
            if (minuto % 3 == 0 && random.nextDouble() < 0.5) {
                numeroNiños = 1;
                System.out.println("EN LOS SIGUIENTES 20 MINUTOS HAN LLEGADO: " + numeroNiños + " NIÑOS");
            }
        }

        return numeroNiños;
    }
}


                

            
        




