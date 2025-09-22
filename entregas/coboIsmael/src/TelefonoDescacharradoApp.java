import java.util.Scanner;

public class TelefonoDescacharradoApp {
    public static void main(String[] args) {
        Scanner entradaUsuario = new Scanner(System.in);

        System.out.println("Simulacion: Telefono Descacharrado (2 horas)");
        System.out.println("Pulsa ENTER para comenzar...");
        String ignorar = entradaUsuario.nextLine();

        int semillaAleatoria = 1234; 
        Simulador simulador = new Simulador(semillaAleatoria);
        simulador.simular();

        entradaUsuario.close();
    }
}
