import java.util.Random;
import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private int tiempoTotal;
    private final double probabilidadCambio0 = 0.70;
    private final double probabilidadCambio1 = 0.20;
    private final double probabilidadCambio2 = 0.10;
    private final Random random = new Random();

    public Mundo(Ludoteca unaLudoteca, int tiempoApertura) {
        ludoteca = unaLudoteca;
        tiempoTotal = tiempoApertura;
    }

    public void iniciarSimulacion() {
        Scanner scanner = new Scanner(System.in);

        for (int minuto = 0; minuto < tiempoTotal; minuto++) {
            System.out.println("=".repeat(30));
            System.out.println("Minuto " + minuto);

            if (llegaNiño(minuto)) {
                Niño niño = generarNiño();
                ludoteca.recibirNiño(niño);
            }

            ludoteca.actualizar();
            ludoteca.verEstado();

            System.out.println("Pulse enter para continuar");
            scanner.nextLine();
        }
    }

    private boolean llegaNiño(int minuto) {
        return Math.random() > ((minuto < 10) ? 0.3 : (minuto < 30) ? 0.6 : 0.8);
    }

    private Niño generarNiño() {
        String nombre = inventarNombre();
        System.out.println("Llega " + nombre);
        return new Niño(nombre, probabilidadCambio0, probabilidadCambio1, probabilidadCambio2, random);
    }

    private String inventarNombre() {
        String[] nombres = {
            "Andrés","Pablo","Diego","Aníbal","Umut","Javier","Fernando",
            "Cayetano","Iker","Mario","Adrián","Paula","Veronika","Eduardo",
            "Hugo","César","Miguel","Santiago","Juan","Daniel","Álvaro",
            "Maura","Neco","Sergio","Aurelio","Jorge","Raúl","José Manuel",
            "José Luis","Óscar","Rubén","Gabriel","Iñaki","Alejandro","Andriuw"
        };
        return nombres[(int)(Math.random()*nombres.length)];
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("TELÉFONO DESCACHARRADO v5.0 (Lista enlazada propia)");
        System.out.println("=".repeat(50));

        Ludoteca ludoteca = new Ludoteca();
        Mundo mundo = new Mundo(ludoteca, 40);
        mundo.iniciarSimulacion();
    }
}
