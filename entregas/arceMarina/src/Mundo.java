import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner scanner;

    public Mundo() {
        ludoteca = new Ludoteca();
        scanner = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt(); scanner.nextLine();
            procesarOpcion(opcion);
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================\n");
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
        System.out.println("13. Mostrar monitoras y niños\n");
        System.out.println("0.  Salir\n");
    }

    private void procesarOpcion(int op) {
        switch(op) {
            case 1:
                System.out.print("Nombre del niño: "); String nombre = scanner.nextLine();
                System.out.print("Edad del niño: "); int edad = scanner.nextInt(); scanner.nextLine();
                Niño n = new Niño(nombre, edad);
                System.out.println("Llega " + nombre + " (" + edad + " años)");
                ludoteca.recibirNiño(n);
                System.out.println(nombre + " pasa a la cola de Lydia");
                break;
            case 2: ludoteca.intentarJuego(); break;
            case 3: ludoteca.presentacionesGenerales(); break;
            case 4:
                System.out.print("Edad mínima: "); int edadMin = scanner.nextInt(); scanner.nextLine();
                System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:");
                ludoteca.presentacionesEdadMin(edadMin);
                break;
            case 5:
                System.out.print("Letra inicial: "); char letra = scanner.nextLine().charAt(0);
                System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
                ludoteca.presentacionesInicial(letra);
                break;
            case 6:
                System.out.println("Aisha pide que se presenten los primeros 5 niños:");
                ludoteca.primerosCinco();
                break;
            case 7:
                System.out.println("Aisha pide que se presenten los últimos 5 niños:");
                ludoteca.ultimosCinco();
                break;
            case 8: ludoteca.conteoAsistencia(); break;
            case 9: ludoteca.edadPromedio(); break;
            case 10: ludoteca.juegoRana(); break;
            case 11: ludoteca.separarParaRana(); break;
            case 12: ludoteca.alarmaIncendios(); break;
            case 13: ludoteca.mostrarEstado(); break;
            case 0: System.out.println("Saliendo..."); break;
            default: System.out.println("Opción inválida"); break;
        }
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}