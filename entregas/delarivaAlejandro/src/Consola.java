import java.util.Scanner;

public class Consola {
    private final Scanner scanner = new Scanner(System.in);

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
            }
        }
    }

    public String leerCadena(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (!linea.isEmpty()) return linea;
            System.out.println("Entrada no válida. Intente de nuevo.");
        }
    }

    public char leerLetra(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (!linea.isEmpty()) return linea.charAt(0);
            System.out.println("Entrada no válida. Intente de nuevo.");
        }
    }

    public void pausar() {
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }
}
