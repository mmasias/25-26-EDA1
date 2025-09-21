import java.util.Scanner;

public class TelefonoDescacharrado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Teléfono Descacharrado (120 min) ===");

        long semilla = pedirLong(scanner, "Semilla (ej. 42) [42]: ", 42);
        double probError = pedirDouble(scanner, "Probabilidad de error [0.35]: ", 0.35);
        double probDosLetras = pedirDouble(scanner, "Probabilidad de dos letras si hay error [0.30]: ", 0.30);
        boolean mostrarEventos = pedirBoolean(scanner, "¿Mostrar traza de eventos? (true/false) [false]: ", false);

        System.out.print("Mensaje fijo de 10 letras en MAYÚSCULAS o 'random' [random]: ");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) entrada = "random";

        String mensajeFijo = null;
        if (!entrada.equalsIgnoreCase("random")) {
            if (entrada.length() == 10 && UtilTexto.esDiezMayusculas(entrada)) {
                mensajeFijo = entrada.toUpperCase();
            } else {
                System.out.println("El mensaje no es válido, se usará uno aleatorio.");
            }
        }
    
    public Simulador(long semilla, double pError, double pDos, boolean traza, String mensajeFijo) {
 
    }

    public void ejecutar() {
  
    }

        Simulador simulador = new Simulador(semilla, probError, probDosLetras, mostrarEventos, mensajeFijo);
        simulador.ejecutar();

        scanner.close();
    }

    private static long pedirLong(Scanner sc, String msg, long defecto) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return defecto;
        try { return Long.parseLong(s); } catch (NumberFormatException e) { return defecto; }
    }

    private static double pedirDouble(Scanner sc, String msg, double defecto) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return defecto;
        try { return Double.parseDouble(s); } catch (NumberFormatException e) { return defecto; }
    }

    private static boolean pedirBoolean(Scanner sc, String msg, boolean defecto) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return defecto;
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t") 
            || s.equalsIgnoreCase("1") || s.equalsIgnoreCase("yes");
    }
}
