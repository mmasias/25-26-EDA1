import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola(50);
        Cola conLidia = new Cola(50);
        Random random = new Random();

        int tiempo = 0;
        int totalTiempo = 120;

        while (tiempo < totalTiempo) {
            if (tiempo < 10) {
                int llegadas = random.nextInt(3);
                for (int i = 0; i < llegadas; i++) {
                    if (cola.size() <= 5) {
                        cola.encolar(new Ninno("N" + tiempo + "_" + i));
                    } else {
                        conLidia.encolar(new Ninno("N" + tiempo + "_" + i));
                    }
                }
            } else if (tiempo < 30 && tiempo % 3 == 0) {
                if (random.nextBoolean()) {
                    if (cola.size() <= 5) {
                        cola.encolar(new Ninno("N" + tiempo));
                    } else {
                        conLidia.encolar(new Ninno("N" + tiempo));
                    }
                }
            }

            if (cola.size() > 5) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    sb.append((char) ('A' + random.nextInt(26)));
                }
                Mensaje mensaje = new Mensaje(sb.toString());

                Juego juego = new Juego(cola, mensaje);
                juego.jugar();

                System.out.println("Juego iniciado en el minuto " + tiempo);
                System.out.println("Mensaje original: " + juego.getMensajeOriginal());
                System.out.println("Mensaje final:    " + juego.getMensajeFinal());
                System.out.println("Duración: " + juego.getDuracion() + " minutos");
                System.out.println("=====================================");

                Ninno[] esperando = conLidia.getElementos();
                cola.reiniciar();
                for (int i = 0; i < esperando.length; i++) {
                    cola.encolar(esperando[i]);
                }
                conLidia.reiniciar();
            }

            tiempo++;
        }
    }
}
