import java.util.Random;

public class Ninho {

    private char[] pizarrin = new char[10];
    private Random random = new Random();

    public void recibeMensaje(char[] msg) {
        pizarrin = msg.clone();
        System.out.println("Niño recibe mensaje: " + new String(msg));
    }

    public String getMensaje() {
        return new String(pizarrin);
    }

    public void muestraPizarrin(Ninho siguiente) {
        System.out.println("Niño muestra mensaje: " + getMensaje());
        char[] dist = distorsionar();
        System.out.println("Mensaje distorsionado: " + new String(dist));
        siguiente.recibeMensaje(dist);
    }

    private char[] distorsionar() {
        char[] chars = pizarrin.clone();
        int cambios = random.nextInt(3);
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(chars.length);
            chars[pos] = (char) ('a' + random.nextInt(26));
        }
        return chars;
    }

    public void escribe(Pizarra pizarra) {
        pizarra.setMensaje(pizarrin);
    }

    public void limpiarPizarrin() {
        pizarrin = new char[10];
    }

}
