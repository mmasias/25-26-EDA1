import java.util.Random;

public class Lydia {

    private ColaNinho esperando = new ColaNinho();
    private Random random = new Random();

    public void recibeNiño(Tiempo tiempo) {
        int totalMin = tiempo.horas() * 60 + tiempo.minutos();
        if (totalMin < 10) {
            int[] limite = {0,2};
            int num = random.nextInt((limite[1]-limite[2])+1);
            for (int i = 0; i < num; i++) {
                esperando.enqueue(new Ninho());
            }
        } else if (totalMin < 30) {
            double probabilidad = 0.5;
            if (random.nextDouble() < probabilidad) {
                esperando.enqueue(new Ninho());
            }
        }
    }

    public boolean tieneEsperando() {
        return !esperando.estaVacia();
    }

    public Ninho darNinho() {
        return esperando.dequeue();
    }
}
