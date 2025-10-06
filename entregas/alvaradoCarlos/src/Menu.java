import java.util.Scanner;

public class Menu {
    
    private Scanner scanner;

    public Menu(){
	this.scanner = new Scanner(System.in);
    }

    public void mostrar(){
    	
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
	   	case 1 -> simularLlegadaNino();
                case 2 -> simularInicioJuego();
                case 3 -> aishaPresentaYNinosSePresentan();
                case 4 -> aishaPideNinosMayoresDe5();
                case 5 -> aishaPideNinosPorLetra();
                case 6 -> aishaPideCincoPrimeros();
                case 7 -> aishaPideCincoUltimos();
                case 8 -> aishaYLydiaCuentanNinos();
                case 9 -> aishaPromedioEdad();
                case 10 -> simularInicioJuegoRana();
                case 11 -> pasarMenoresCincoADalsy();
                case 12 -> activarProtocoloEmergencia();
                case 13 -> mostrarMonitorasYNinos();
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

        private void simularLlegadaNino() {
        System.out.println("Simulando llegada de niño...");
    }

    private void simularInicioJuego() {
        System.out.println("Simulando intento de inicio de juego...");
    }

    private void aishaPresentaYNinosSePresentan() {
        System.out.println("Aisha se presenta y los niños se presentan...");
    }

    private void aishaPideNinosMayoresDe5() {
        System.out.println("Aisha pide que se presenten los niños mayores de 5 años...");
    }

    private void aishaPideNinosPorLetra() {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza por cierta letra...");
    }

    private void aishaPideCincoPrimeros() {
        System.out.println("Aisha pide que se presenten los cinco primeros niños...");
    }

    private void aishaPideCincoUltimos() {
        System.out.println("Aisha pide que se presenten los cinco últimos niños...");
    }

    private void aishaYLydiaCuentanNinos() {
        System.out.println("Aisha y Lydia dicen cuántos niños hay en cola...");
    }

    private void aishaPromedioEdad() {
        System.out.println("Aisha dice la edad promedio de los niños en cola...");
    }

    private void simularInicioJuegoRana() {
        System.out.println("Simulando intento de inicio del juego de la rana...");
    }

    private void pasarMenoresCincoADalsy() {
        System.out.println("Pasando niños menores de 5 años a la monitora Dalsy...");
    }

    private void activarProtocoloEmergencia() {
        System.out.println("¡Alarma contra incendios! Activando protocolo de emergencia...");
    }

    private void mostrarMonitorasYNinos() {
        System.out.println("Mostrando monitoras y niños...");
    }
}
