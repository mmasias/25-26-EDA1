import java.util.Random;
import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner scanner;
    private Random random;

    public Mundo() {
        ludoteca = new Ludoteca();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) simularLlegadaNiño();
            else if (opcion == 13) ludoteca.mostrarEstado();

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println("1.  Simular llegada de niño");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println("0.  Salir");
    }

    private void simularLlegadaNiño() {
        System.out.print("Nombre del niño: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad del niño: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        Niño niño = new Niño(nombre, edad, 0.7, 0.2, 0.1, random);
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        System.out.println(nombre + " pasa a la cola de Lydia");
        ludoteca.recibirNiñoEnLydia(niño);
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutar();
    }
}
