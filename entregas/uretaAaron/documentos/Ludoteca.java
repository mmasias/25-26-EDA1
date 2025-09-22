import java.util.Scanner;

public class Ludoteca {
    public static void main(String[] args) {
        Scanner entrada;
        entrada = new Scanner(System.in);

        TelefonoDescacharrado juego;
        juego = new TelefonoDescacharrado();
        juego.jugar();

        entrada.close();
    }
}
