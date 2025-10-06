import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner lector;

    public Mundo() {
        ludoteca = new Ludoteca();
        lector = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione opción: ");
            opcion = lector.nextInt();
            lector.nextLine(); // limpiar buffer
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.print("\nPresione ENTER para continuar...");
                lector.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println("1.  Simular llegada de niño");
        System.out.println("2.  Simular intento de inicio de juego");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println("0.  Salir\n");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> {
                System.out.print("Nombre del niño: ");
                String nombre = lector.nextLine();
                System.out.print("Edad: ");
                int edad = lector.nextInt();
                ludoteca.llegadaNino(nombre, edad);
            }
            case 2 -> ludoteca.intentoInicioJuego();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción inválida\n");
        }
    }
}

