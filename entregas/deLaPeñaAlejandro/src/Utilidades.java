import java.util.Scanner;

public class Utilidades {

    public static void mensaje(String string) {
        System.out.print(string);
    }

    public static void saltarLinea() {
        System.out.println();
    }

    public static String leerCadena(String string) {
        mensaje(string);
        return new java.util.Scanner(System.in).nextLine();
    }
}