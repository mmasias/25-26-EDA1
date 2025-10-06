import java.util.Random;
import java.util.Scanner;

public class SimuladorLudoteca {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Ludoteca ludoteca = new Ludoteca();

    private static final String[] NOMBRES = {
            "Lucas", "Mateo", "Sofía", "Valentina", "Diego", "Martina",
            "Samuel", "Lucía", "Emma", "Juan", "Sara", "Camila", "Leo", "Noah",
            "Julia", "David", "Liam", "Clara", "Pablo", "Isabella"
    };

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> simularLlegadaNiño();
                case 2 -> ludoteca.intentarInicioDeJuego();
                case 3 -> ludoteca.presentacionGeneral();
                case 4 -> ludoteca.presentacionMayoresDe5();
                case 5 -> {
                    char letra = leerLetra("Letra inicial: ");
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

    private void mostrarMenu() {
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
    }

    private void simularLlegadaNiño() {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        int edad = 3 + random.nextInt(7);
        ludoteca.recibirNiño(new Niño(nombre, edad));
        System.out.println("Niño generado: " + nombre + " (" + edad + " años)");
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private char leerLetra(String mensaje) {
        System.out.print(mensaje);
        String input = scanner.nextLine().trim().toUpperCase();
        return input.isEmpty() ? 'A' : input.charAt(0);
    }
}
