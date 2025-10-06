import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Console() { }

    public String readString(String title) {
        try {
            System.out.print(title != null ? title : "");
            return bufferedReader.readLine();
        } catch (Exception ex) {
            return "";
        }
    }

    public String readString() {
        return readString("");
    }

    public int readInt(String title) {
        while (true) {
            try {
                String s = readString(title);
                return Integer.parseInt(s.trim());
            } catch (Exception ex) {
                writeln("Entrada inválida. Introduce un número entero.");
            }
        }
    }

    public double readDouble(String title) {
        while (true) {
            try {
                String s = readString(title);
                return Double.parseDouble(s.trim());
            } catch (Exception ex) {
                writeln("Entrada inválida. Introduce un número decimal.");
            }
        }
    }

    public char readChar(String title) {
        while (true) {
            String s = readString(title);
            if (s != null && s.length() == 1) return s.charAt(0);
            writeln("Entrada inválida. Introduce un solo carácter.");
        }
    }

    public void write(String string) {
        System.out.print(string);
    }

    public void writeln(String string) {
        System.out.println(string);
    }

    public void writeln() {
        System.out.println();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
