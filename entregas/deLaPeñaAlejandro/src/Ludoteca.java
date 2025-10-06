import java.util.Scanner;

public class Ludoteca {
    private Monitora lydia = new Monitora("Lydia");
    private Monitora aisha = new Monitora("Aisha");
    private Monitora dalsy = new Monitora("Dalsy");
    private Scanner sc = new Scanner(System.in);

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            procesar(opcion);
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("""
        ========================================
                LUDOTECA - SIMULACIÓN
        ========================================
        1.  Simular llegada de niño
        13. Mostrar monitoras y niños
        0.  Salir
        ========================================
        """);
    }

    private void procesar(int opcion) {
        switch (opcion) {
            case 1 -> simularLlegada();
            case 13 -> mostrarEstado();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private void simularLlegada() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        Ninio n = new Ninio(nombre, edad);
        System.out.println("Llega " + n);
        System.out.println(nombre + " pasa a la cola de Lydia");
        lydia.recibirNinio(n);
    }

    private void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println("\nLYDIA:");
        lydia.mostrarCola();
        System.out.println("\nAISHA:");
        aisha.mostrarCola();
        System.out.println("\nDALSY:");
        dalsy.mostrarCola();
    }
}
