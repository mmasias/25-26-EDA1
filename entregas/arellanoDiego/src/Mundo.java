import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner sc;

    public Mundo() {
        this.ludoteca = new Ludoteca();
        this.sc = new Scanner(System.in);
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
        System.out.print("Seleccione una opción: ");
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt();
            System.out.println();
            procesarOpcion(opcion);
            System.out.println();
            System.out.println("Presione ENTER para continuar...");
            sc.nextLine();
        } while (opcion != 0);
        System.out.println("Saliendo. ¡Hasta la próxima!");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                opcionSimularLlegada();
                break;
            case 2:
                ludoteca.simularIntentoInicioJuegoDesdeLydia();
                break;
            case 3:
                ludoteca.aishaPresentacionGeneral();
                break;
            case 4:
                System.out.print("Edad mínima: ");
                int edadMin = leerInt();
                ludoteca.aishaPresentacionMayoresQue(edadMin);
                break;
            case 5:
                System.out.print("Ingrese la letra inicial: ");
                String s = sc.nextLine().trim();
                if (s.isEmpty()) {
                    System.out.println("Letra no válida.");
                } else {
                    char letra = s.charAt(0);
                    ludoteca.aishaPresentacionPorInicial(letra);
                }
                break;
            case 6:
                ludoteca.aishaPresentacionPrimerosCinco();
                break;
            case 7:
                ludoteca.aishaPresentacionUltimosCinco();
                break;
            case 8:
                ludoteca.conteoAsistencia();
                break;
            case 9:
                ludoteca.edadPromedioAisha();
                break;
            case 10:
                ludoteca.intentoJuegoRana();
                break;
            case 11:
                ludoteca.separarMenoresDe5ADalsy();
                break;
            case 12:
                ludoteca.alarmaIncendios();
                break;
            case 13:
                ludoteca.mostrarEstadoActual();
                break;
            case 0:
            
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void opcionSimularLlegada() {
        System.out.print("Nombre del niño: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre no valido.");
            return;
        }
        System.out.print("Edad del niño (años): ");
        int edad = leerInt();
        Nino n = new Nino(nombre, edad);
        ludoteca.recibirNinoEnLydia(n);
    }

    private int leerInt() {
        while (true) {
            try {
                String linea = sc.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida. Intente de nuevo: ");
            }
        }
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}
