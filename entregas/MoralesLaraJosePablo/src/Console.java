import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    public char readChar(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.length() > 0) {
            return input.charAt(0);
        }
        return ' ';
    }

    public void println(String mensaje) {
        System.out.println(mensaje);
    }

    public void print(String mensaje) {
        System.out.print(mensaje);
    }
}