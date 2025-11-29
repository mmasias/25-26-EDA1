import java.util.Scanner;

public class Simulacion {
    private static final int DURACION_SIMULACION = 200;

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        for (int tiempo = 1; tiempo <= DURACION_SIMULACION; tiempo++) {
            System.out.println("--- Minuto " + tiempo + " ---");
            System.out.println("Pulsa Enter para avanzar al siguiente minuto...");
            scanner.nextLine();
            restaurante.avanzarUnMinuto(tiempo);
            
        }

        restaurante.finalizar();
        scanner.close();
    }
}