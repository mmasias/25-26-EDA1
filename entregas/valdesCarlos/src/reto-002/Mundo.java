import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner sc;

    public Mundo() {
        ludoteca = new Ludoteca();
        sc = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione opción: ");
            procesarOpcion(opcion);
            System.out.println("Presione ENTER para continuar...");
            sc.nextLine(); // pausa
        } while (opcion != 0);
        sc.close();
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println();
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
        System.out.println();
        System.out.println("0.  Salir");
        System.out.println();
    }

    private void procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1: ludoteca.simularLlegadaNino(); break;
            case 2: ludoteca.simularInicioJuego(); break;
            case 3: ludoteca.presentacionGeneral(); break;
            case 4: ludoteca.presentarMayoresDe5(); break;
            case 5: 
                char letra = leerChar("Introduce la letra: ");
                ludoteca.presentarPorLetra(letra); 
                break;
            case 6: ludoteca.presentarPrimeros5(); break;
            case 7: ludoteca.presentarUltimos5(); break;
            case 8: ludoteca.mostrarCola(); break;
            case 9: ludoteca.mostrarEdadPromedio(); break;
            case 10: ludoteca.simularJuegoRana(); break;
            case 11: ludoteca.pasarNinosMenoresADalsy(); break;
            case 12: ludoteca.protocoloEmergencia(); break;
            case 13: ludoteca.mostrarMonitorasYNinos(); break;
            case 0: System.out.println("Saliendo de la simulación..."); break;
            default: System.out.println("Opción no válida."); break;
        }
        System.out.println();
    }

    private int leerInt(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, introduce un número válido.");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return valor;
    }

    private char leerChar(String mensaje) {
        System.out.print(mensaje);
        String line = sc.nextLine();
        if (line.length() > 0) {
            return line.charAt(0);
        } else {
            return ' ';
        }
    }

    // --- MAIN ---
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}
