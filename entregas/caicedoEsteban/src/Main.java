import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ludoteca ludoteca = new Ludoteca();
        Random random = new Random();
        boolean salir = false;

        String[] nombres = {
                "Lucas", "Mateo", "Sofía", "Valentina", "Diego", "Martina",
                "Samuel", "Lucía", "Emma", "Juan", "Sara", "Camila", "Leo", "Noah",
                "Julia", "David", "Liam", "Clara", "Pablo", "Isabella", "Esteban", "David", "Leonel", "Emilio",
                "Ernesto", "Valentino", "Emmanuel", "Fernando", "Felipe", "Luis", "Alexandra", "Carmen", "Sofia",
                "Milena", "Maria", "Jose", "Romina", "Gabriela", "Luciana", "Guilianna", "Victoria"
        };

        while (!salir) {
            System.out.println("""

                    ========================================
                            LUDOTECA - SIMULACIÓN
                    ========================================
                    1.  Simular llegada de niño
                    2.  Simular intento de inicio de juego
                    3.  Aisha se presenta y pide a los niños que se presenten
                    4.  Aisha pide que se presenten los niños mayores de 5 años
                    5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra
                    6.  Aisha pide que se presenten los cinco primeros niños
                    7.  Aisha pide que se presenten los cinco últimos niños
                    8.  Aisha y Lydia dicen cuántos niños hay en cola
                    9.  Aisha dice la edad promedio de los niños en cola
                    10. Simular intento de inicio del juego de la rana
                    11. Paso de niños menores de 5 años a monitora Dalsy
                    12. Alarma contra incendios y protocolo de emergencia
                    13. Mostrar monitoras y niños
                    0.  Salir
                    ========================================""");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1 -> {
                    String nombre = nombres[random.nextInt(nombres.length)];
                    int edad = 3 + random.nextInt(7);
                    Niño n = new Niño(nombre, edad);
                    ludoteca.recibirNiño(n);
                    System.out.println("Niño generado: " + nombre + " (" + edad + " años)");
                }
                case 2 -> ludoteca.intentarInicioDeJuego();
                case 3 -> ludoteca.presentacionGeneral();
                case 4 -> ludoteca.presentacionMayoresDe5();
                case 5 -> {
                    System.out.print("Letra inicial: ");
                    char letra = scanner.nextLine().toUpperCase().charAt(0);
                    ludoteca.presentacionPorLetra(letra);
                }
                case 6 -> ludoteca.presentacionPrimerosCinco();
                case 7 -> ludoteca.presentacionUltimosCinco();
                case 8 -> ludoteca.mostrarCantidadColas();
                case 9 -> ludoteca.mostrarEdadPromedio();
                case 10 -> ludoteca.simularJuegoDeLaRana();
                case 11 -> ludoteca.pasarMenoresADalsy();
                case 12 -> ludoteca.alarmaIncendios();
                case 13 -> ludoteca.mostrarEstado();
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}
