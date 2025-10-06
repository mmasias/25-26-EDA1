import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Simulacion simulacion;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.simulacion = new Simulacion();
    }

    public void mostrar() {

        int opcion;

        do {
            mostrarMenu();
            System.out.print("\nSeleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingrese un número válido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> simulacion.simularLlegadaNino();
                case 2 -> simulacion.simularInicioJuego();
                case 3 -> simulacion.aishaPresentaYNinosSePresentan();
                case 4 -> simulacion.aishaPideNinosMayoresDe5();
                case 5 -> simulacion.aishaPideNinosPorLetra();
                case 6 -> simulacion.aishaPideCincoPrimeros();
                case 7 -> simulacion.aishaPideCincoUltimos();
                case 8 -> simulacion.aishaYLydiaCuentanNinos();
                case 9 -> simulacion.aishaPromedioEdad();
                case 10 -> simulacion.simularInicioJuegoRana();
                case 11 -> simulacion.pasarMenoresCincoADalsy();
                case 12 -> simulacion.activarProtocoloEmergencia();
                case 13 -> simulacion.mostrarMonitorasYNinos();
                case 0 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println("1.  Simular llegada de niño");
        System.out.println("2.  Simular intento de inicio de juego");
        System.out.println("3.  Aisha se presenta y pide a los niños que se presenten");
        System.out.println("4.  Aisha pide que se presenten los niños mayores de 5 años");
        System.out.println("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        System.out.println("6.  Aisha pide que se presenten los cinco primeros niños");
        System.out.println("7.  Aisha pide que se presenten los cinco últimos niños");
        System.out.println("8.  Aisha y Lydia dicen cuántos niños hay en cola");
        System.out.println("9.  Aisha dice la edad promedio de los niños en cola");
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println("0.  Salir");
    }
}
