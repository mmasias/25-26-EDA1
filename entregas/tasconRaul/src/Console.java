import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public int readInt(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            if (esNumero(input)) {
                valor = convertirANumero(input);
                valido = true;
            } else {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        return valor;
    }

    
    private boolean esNumero(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && c == '-') continue;
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    
    private int convertirANumero(String s) {
        int num = 0;
        boolean negativo = false;
        int i = 0;
        if (s.charAt(0) == '-') {
            negativo = true;
            i = 1;
        }
        for (; i < s.length(); i++) {
            num = num * 10 + (s.charAt(i) - '0');
        }
        return negativo ? -num : num;
    }

    public String readString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
