import java.util.Random;

public class TelefonoDescacharrado {
    private Lydia lydia;
    private Aisha aisha;
    private Juego juegoActual;
    private Niño[] niñosEnEspera;
    private int numNiñosEspera;
    private Random rand;

    public TelefonoDescacharrado() {
        lydia = new Lydia();
        aisha = new Aisha();
        niñosEnEspera = new Niño[100];
        numNiñosEspera = 0;
        rand = new Random();
    }

    public void simular() {
        int tiempo = 0;
        while (tiempo < 120) {
            if (tiempo < 10) {
                int n = rand.nextInt(3);
                for (int i = 0; i < n; i++) {
                    Niño nuevo = lydia.recibirNiño("Niño" + tiempo + "_" + i);
                    System.out.println("Minuto " + tiempo + ": llega " + nuevo.getNombre());
                    if (juegoActual != null) {
                        niñosEnEspera[numNiñosEspera++] = nuevo;
                    } else {
                        aisha.meterEnCola(nuevo);
                    }
                }
            } else if (tiempo < 30) {
                if (tiempo % 3 == 0 && rand.nextBoolean()) {
                    Niño nuevo = lydia.recibirNiño("Niño" + tiempo);
                    System.out.println("Minuto " + tiempo + ": llega " + nuevo.getNombre());
                    if (juegoActual != null) {
                        niñosEnEspera[numNiñosEspera++] = nuevo;
                    } else {
                        aisha.meterEnCola(nuevo);
                    }
                }
            }

            if (aisha.getCola().getTamaño() > 5 && juegoActual == null) {
                System.out.println("\n--- Se inicia un nuevo juego ---");
                System.out.print("Cola antes del juego: ");
                imprimirCola();

                String mensaje = generarMensaje();
                System.out.println("Mensaje original: " + mensaje);
                juegoActual = new Juego(aisha.getCola());

                for (int i = 0; i < aisha.getCola().getTamaño(); i++) {
                    if (i == 0) {
                        aisha.getCola().getNiño(i).setPizarra(mensaje);
                    } else {
                        String mensajePrevio = aisha.getCola().getNiño(i - 1).getPizarra();
                        aisha.getCola().getNiño(i).setPizarra(deformarMensaje(mensajePrevio));
                    }
                    System.out.println(aisha.getCola().getNiño(i).getNombre() + ": " + aisha.getCola().getNiño(i).getPizarra());
                }

                juegoActual.jugar(aisha.getCola().getNiño(aisha.getCola().getTamaño() - 1).getPizarra());
                System.out.println("Mensaje final en la pizarra del salón: " + juegoActual.getPizarraSalon());

                for (int i = 0; i < numNiñosEspera; i++) {
                    aisha.meterEnCola(niñosEnEspera[i]);
                    niñosEnEspera[i] = null;
                }
                numNiñosEspera = 0;

                System.out.print("Cola después del juego: ");
                imprimirCola();
                System.out.println("-----------------------------\n");

                juegoActual = null;
            }

            tiempo++;
        }
    }

    private void imprimirCola() {
        for (int i = 0; i < aisha.getCola().getTamaño(); i++) {
            System.out.print(aisha.getCola().getNiño(i).getNombre() + " ");
        }
        System.out.println();
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            msg.append(letras.charAt(rand.nextInt(letras.length())));
        }
        return msg.toString();
    }

    private String deformarMensaje(String msg) {
        char[] chars = msg.toCharArray();
        int cambios = rand.nextInt(2) + 1;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < cambios; i++) {
            int pos = rand.nextInt(chars.length);
            chars[pos] = letras.charAt(rand.nextInt(letras.length()));
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        TelefonoDescacharrado simulacion = new TelefonoDescacharrado();
        simulacion.simular();
    }
}


