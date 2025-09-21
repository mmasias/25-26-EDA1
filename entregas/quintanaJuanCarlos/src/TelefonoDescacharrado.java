import java.util.Scanner;

public class TelefonoDescacharrado {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simulador 'Teléfono Descacharrado' (120 minutos) ===");
        System.out.print("Semilla (entero, p.ej. 42): ");
        long semilla = leerLong(sc, 42);

        System.out.print("Probabilidad de error por niño (pError) [0.35]: ");
        double pError = leerDouble(sc, 0.35);

        System.out.print("Probabilidad de 2 letras si hay error (pDos) [0.30]: ");
        double pDos = leerDouble(sc, 0.30);

        System.out.print("¿Mostrar traza por evento? (true/false) [false]: ");
        boolean traza = leerBoolean(sc, false);

        System.out.print("Mensaje fijo de 10 letras mayúsculas o 'random' [random]: ");
        String opcionMsg = sc.nextLine().trim();
        if (opcionMsg.isEmpty()) opcionMsg = "random";
        String mensajeFijo = null;
        if (!opcionMsg.equalsIgnoreCase("random")) {
            if (opcionMsg.length() != 10 || !UtilTexto.esDiezMayusculas(opcionMsg)) {
                System.out.println("Mensaje inválido. Se usará aleatorio.");
            } else {
                mensajeFijo = opcionMsg.toUpperCase();
            }
        }

        Simulador simulador = new Simulador(semilla, pError, pDos, traza, mensajeFijo);
        simulador.ejecutar();

        sc.close();
    }

    private static long leerLong(Scanner sc, long def) {
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return def;
        try { return Long.parseLong(s); } catch (Exception e) { return def; }
    }
    private static double leerDouble(Scanner sc, double def) {
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return def;
        try { return Double.parseDouble(s); } catch (Exception e) { return def; }
    }
    private static boolean leerBoolean(Scanner sc, boolean def) {
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return def;
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t") || s.equalsIgnoreCase("1") || s.equalsIgnoreCase("yes");
    }
}
