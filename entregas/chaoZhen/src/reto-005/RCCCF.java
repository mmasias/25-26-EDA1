import java.util.Scanner;

public class RCCCF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int duracionJornada = 0;

        System.out.println("--- CONFIGURACIÓN SIMULACIÓN RCCCF ---");
     
        while (duracionJornada <= 0) {
            System.out.print("Ingrese la duración de la jornada (en minutos): ");
            if (scanner.hasNextInt()) {
                duracionJornada = scanner.nextInt();
                if (duracionJornada <= 0) {
                    System.out.println("Error: El tiempo debe ser mayor a 0.");
                }
            } else {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                scanner.next(); 
            }
        }

        System.out.println("Configurando simulación para " + duracionJornada + " minutos...");
        
        
        RCCCF restaurante = new RCCCF(duracionJornada);
        restaurante.iniciarSimulacion();
        
        scanner.close();
    }
}