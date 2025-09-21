import java.util.Random;

public class Lydia {

    private ColaNinho esperando = new ColaNinho();
    private Random random = new Random();

    public void recibeNiño(Tiempo tiempo) {
        int totalMin = tiempo.horas() * 60 + tiempo.minutos();
        if (totalMin < 10) {
            int num = random.nextInt(3);
            for (int i = 0; i < num; i++) {
                esperando.enqueue(new Ninho());
                System.out.println("Llega niño a Lydia en tiempo " + tiempo.horas() + ":" + String.format("%02d", tiempo.minutos()));
            }
        } else if (totalMin < 30) {
            if (random.nextDouble() < 0.5) {
                esperando.enqueue(new Ninho());
                System.out.println("Llega niño a Lydia en tiempo " + tiempo.horas() + ":" + String.format("%02d", tiempo.minutos()));
            }
        }
    }

    public boolean tieneEsperando() {
        return !esperando.estaVacia();
    }

    public Ninho darNinho() {
        return esperando.dequeue();
    }

    public void imprimirLista() {
        System.out.print("Lydia: ");
        ColaNinho temp = new ColaNinho();
        while (!esperando.estaVacia()) {
            System.out.print("_o_ ");
            temp.enqueue(esperando.dequeue());
        }
        while (!temp.estaVacia()) {
            esperando.enqueue(temp.dequeue());
        }
        System.out.println();
    }
}
