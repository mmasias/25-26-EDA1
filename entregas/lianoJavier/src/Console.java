import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void separador(String divisor) {
        final int MULTIPLICADOR_BASE = 50;
        separador(divisor, MULTIPLICADOR_BASE);
    }

    public static void separador(String divisor, int multiplicador) {
        imprimir(divisor.repeat(multiplicador));
    }

    public static void imprimir(String string) {
        System.out.print(string);
    }

    public static void imprimirLinea() {
        System.out.println();
    }

    public static String espacio() {
        return " ";
    }

    public static void waitUser() {
        imprimirLinea();
        Console.imprimir("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

