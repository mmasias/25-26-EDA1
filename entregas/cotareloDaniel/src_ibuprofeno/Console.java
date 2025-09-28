package cotareloDaniel.src_ibuprofeno;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    public enum ForegroundColor {
        BLACK("\u001B[30m"), RED("\u001B[31m"), GREEN("\u001B[32m"), YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"), PURPLE("\u001B[35m"), CYAN("\u001B[36m"), WHITE("\u001B[37m"),
        RESET("\u001B[39m");

        private final String code;

        ForegroundColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum BackgroundColor {
        BLACK("\u001B[40m"), RED("\u001B[41m"), GREEN("\u001B[42m"), YELLOW("\u001B[43m"),
        BLUE("\u001B[44m"), PURPLE("\u001B[45m"), CYAN("\u001B[46m"), WHITE("\u001B[47m"),
        RESET("\u001B[49m");

        private final String code;

        BackgroundColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    private static final String RESET_ALL = "\u001B[0m";
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final boolean supportsAnsiColors;

    public Console() {
        this.supportsAnsiColors = System.console() != null
                && !System.getProperty("os.name").toLowerCase().contains("win");
    }

    private String applyForeground(String text, ForegroundColor color) {
        return supportsAnsiColors ? color.getCode() + text + RESET_ALL : text;
    }

    private String applyBackground(String text, BackgroundColor color) {
        return supportsAnsiColors ? color.getCode() + text + RESET_ALL : text;
    }

    private String applyColors(String text, ForegroundColor foreground, BackgroundColor background) {
        if (!supportsAnsiColors)
            return text;
        return foreground.getCode() + background.getCode() + text + RESET_ALL;
    }

    public String readString(String title) {
        String input = null;
        this.write(title);
        try {
            input = this.bufferedReader.readLine();
        } catch (Exception ex) {
        }
        return input;
    }

    public String readString() {
        return this.readString("");
    }

    public int readInt(String title) {
        int input = 0;
        boolean ok = false;
        do {
            try {
                input = Integer.parseInt(this.readString(title));
                ok = true;
            } catch (Exception ex) {
                this.writeError("integer");
            }
        } while (!ok);
        return input;
    }

    public double readDouble(String title) {
        double input = 0;
        boolean ok = false;
        do {
            try {
                input = Double.parseDouble(this.readString(title));
                ok = true;
            } catch (Exception ex) {
                this.writeError("double");
            }
        } while (!ok);
        return input;
    }

    public char readChar(String title) {
        char charValue = ' ';
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.length() != 1) {
                this.writeError("character");
            } else {
                charValue = input.charAt(0);
                ok = true;
            }
        } while (!ok);
        return charValue;
    }

    public void write(String string) {
        System.out.print(string);
    }

    public void write(int value) {
        write(String.valueOf(value));
    }

    public void write(double value) {
        write(String.valueOf(value));
    }

    public void write(char character) {
        write(String.valueOf(character));
    }

    public void write(boolean character) {
        write(String.valueOf(character));
    }

    public void writeln() {
        System.out.println();
    }

    public void writeln(String string) {
        this.write(string);
        this.writeln();
    }

    public void writeln(int value) {
        this.write(value);
        this.writeln();
    }

    public void writeln(double value) {
        this.write(value);
        this.writeln();
    }

    public void writeln(char value) {
        this.write(value);
        this.writeln();
    }

    public void writeln(boolean value) {
        this.write(value);
        this.writeln();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public char readChar(String title, boolean transformToUpperCase) {
        char unChar = this.readChar(title);
        return transformToUpperCase ? Character.toUpperCase(unChar) : unChar;
    }

    public void write(String string, ForegroundColor color) {
        write(applyForeground(string, color));
    }

    public void writeln(String string, ForegroundColor color) {
        write(string, color);
        writeln();
    }

    public void write(String string, BackgroundColor bgColor) {
        write(applyBackground(string, bgColor));
    }

    public void writeln(String string, BackgroundColor bgColor) {
        write(string, bgColor);
        writeln();
    }

    public void write(String string, ForegroundColor fgColor, BackgroundColor bgColor) {
        write(applyColors(string, fgColor, bgColor));
    }

    public void writeln(String string, ForegroundColor fgColor, BackgroundColor bgColor) {
        write(string, fgColor, bgColor);
        writeln();
    }

    public void writeError(String format) {
        writeln("FORMAT ERROR! Enter a " + format + " formatted value.", ForegroundColor.RED);
    }
}
