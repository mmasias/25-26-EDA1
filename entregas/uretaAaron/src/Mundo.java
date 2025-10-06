import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca = new Ludoteca();
    private Scanner sc = new Scanner(System.in);

    public void ejecutarSimulacion() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del niño: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad del niño: ");
                    int edad = leerEntero();
                    ludoteca.llegadaNino(new Nino(nombre, edad));
                }
                case 2 -> ludoteca.intentoInicioJuegoDesdeLydia();
                case 3 -> ludoteca.presentacionesGenerales();
                case 4 -> {
                    System.out.print("Edad mínima: ");
                    int edad = leerEntero();
                    ludoteca.presentacionesPorEdadMinima(edad);
                }
                case 5 -> {
                    System.out.print("Letra inicial: ");
                    char letra = sc.nextLine().charAt(0);
                    ludoteca.presentacionesPorInicial(letra);
                }
                case 6 -> ludoteca.primerosCinco();
                case 7 -> ludoteca.ultimosCinco();
                case 8 -> ludoteca.conteoAsistencia();
                case 9 -> ludoteca.edadPromedioAisha();
                case 10 -> ludoteca.intentoJuegoRana();
                case 11 -> ludoteca.separarMenoresParaRana();
                case 12 -> ludoteca.alarmaContraIncendios();
                case 13 -> ludoteca.mostrarEstado();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
            if (opcion != 0) {
                System.out.println();
                System.out.print("Presione ENTER para continuar...");
                sc.nextLine();
            }
        }
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
        System.out.println("========================================");
    }

    private int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.nextLine();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    public static void main(String[] args) {
        new Mundo().ejecutarSimulacion();
    }
}
