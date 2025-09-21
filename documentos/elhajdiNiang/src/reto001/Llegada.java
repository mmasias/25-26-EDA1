package reto001;

import java.util.*;

public class Llegada {
    private Random random = new Random();
    private int contadorNinos = 0;

    public List<Niño> generarLlegadas(int minuto) {
        List<Niño> nuevos = new ArrayList<>();
        int cantidad = 0;

        if (minuto <= 10) {
            cantidad = random.nextInt(3);
        } else if (minuto <= 30) {
            if (minuto % 3 == 0 && random.nextDouble() < 0.5) {
                cantidad = 1;
            }
        }

        for (int i = 0; i < cantidad; i++) {
            nuevos.add(new Niño(++contadorNinos));
        }
        return nuevos;
    }

    public int getTotalNinosGenerados() {
        return contadorNinos;
    }
}
     
    




                

            
        




