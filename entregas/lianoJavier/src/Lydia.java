import java.util.Random;

public class Lydia {

    private ColaNinho esperando = new ColaNinho();
    private Random random = new Random();

    public void recibeNiño(Tiempo tiempo) {
        int totalMin = tiempo.horas() * 60 + tiempo.minutos();
        if (totalMin < 10) {
            int num = random.nextInt(3); // 0-2 niños
            for (int i = 0; i < num; i++) {
                esperando.enqueue(new Ninho());
            }
        } else if (totalMin < 30) {
            if (random.nextDouble() < 0.5) { // 50% probabilidad
                esperando.enqueue(new Ninho());
            }
        }
        // Después de 30 min, no llegan más
    }

    public boolean tieneEsperando() {
        return !esperando.estaVacia();
    }

    public Ninho darNinho() {
        return esperando.dequeue();
    }
}
