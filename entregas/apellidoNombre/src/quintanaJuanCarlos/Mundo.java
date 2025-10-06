import java.util.Scanner;

public class Mundo {
    private final Ludoteca ludoteca;
    private final Scanner sc;

    public Mundo() {
        this.ludoteca = new Ludoteca();
        this.sc = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.println();
                System.out.print("Presione ENTER para continuar...");
                sc.nextLine();
                System.out.println();
            }
        } while (opcion != 0);
        sc.close();
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
        System.out.println();
        System.out.println("0.  Salir");
        System.out.println();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> opcionLlegada();
            case 2 -> ludoteca.intentoInicioDeJuego();
            case 3 -> ludoteca.presentacionesGeneralesAisha();
            case 4 -> {
                int edadMin = leerEntero("Edad mínima: ");
                ludoteca.presentacionesPorEdadMinima(edadMin);
            }
            case 5 -> {
                char letra = leerLetra("Letra inicial: ");
                ludoteca.presentacionesPorInicial(letra);
            }
            case 6 -> ludoteca.primerosCinco();
            case 7 -> ludoteca.ultimosCinco();
            case 8 -> ludoteca.conteoAsistencia();
            case 9 -> ludoteca.edadPromedioAisha();
            case 10 -> ludoteca.intentoJuegoDeLaRana();
            case 11 -> ludoteca.separarMenoresAJuegoRana();
            case 12 -> ludoteca.alarmaIncendios();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida");
        }
    }

    private void opcionLlegada() {
        System.out.print("Nombre del niño: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre vacío. Operación cancelada.");
            return;
        }
        int edad = leerEntero("Edad del niño: ");
        ludoteca.llegadaDeNino(nombre, edad);
    }

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Valor no válido. Intente de nuevo.");
            }
        }
    }

    private char leerLetra(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.length() == 1 && Character.isLetter(s.charAt(0))) {
                return s.charAt(0);
            }
            System.out.println("Debe introducir una única letra (A-Z).");
        }
    }

    public static void main(String[] args) {
        Mundo m = new Mundo();
        m.ejecutarSimulacion();
    }
}
