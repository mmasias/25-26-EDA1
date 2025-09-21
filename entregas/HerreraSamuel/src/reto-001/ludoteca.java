import java.util.*;

public class Ludoteca {
    public static void main(String[] args) {
        Queue<Nino> fila = new LinkedList<>();
        Random rand = new Random();

        int tiempo = 0;
        while (tiempo < 120) { // 2 horas
            // Llegada de niños
            if (tiempo < 10) { // primeros 10 minutos: 0-2 niños
                int llegadas = rand.nextInt(3);
                for (int i = 0; i < llegadas; i++) fila.add(new Nino());
            } else if (tiempo < 30) { // siguientes 20 minutos: 1 niño cada 3 min con 50% prob.
                if (tiempo % 3 == 0 && rand.nextBoolean()) fila.add(new Nino());
            }

            // Si hay suficientes niños, se juega
            if (fila.size() >= 5) {
                Juego juego = new Juego(fila);
                juego.jugar(tiempo);
                fila.clear(); // los niños del juego se van
            }

            tiempo++;
        }
    }
}
