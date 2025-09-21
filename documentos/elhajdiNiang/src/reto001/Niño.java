package reto001;

import java.util.Random;

public class Niño {
    private int id;
    private String pizarrin;

    public Niño(int id) {
        this.id = id;
    }

    public String copiarMensaje(String mensaje, Random rand) {
        StringBuilder sb = new StringBuilder(mensaje);
        int errores = rand.nextInt(3);
        for (int i = 0; i < errores; i++) {
            int pos = rand.nextInt(sb.length());
            char letra;
            do {
                letra = (char) ('A' + rand.nextInt(26));
            } while (letra == sb.charAt(pos));
            sb.setCharAt(pos, letra);
        }
        pizarrin = sb.toString();
        return pizarrin;
    }

    public int getId() {
        return id;
    }
}
