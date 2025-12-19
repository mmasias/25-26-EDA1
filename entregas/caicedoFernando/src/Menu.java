import java.util.Scanner;

public class Menu {
    private final Lista lista;
    private final Scanner scanner;

    public Menu() {
        lista = new Lista();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        boolean ejecutando = true;

        System.out.println("AVISO: Ejecute con -ea para activar asserts.");

        while (ejecutando) {
            System.out.println("\n--- GESTIÓN DE LISTA (SIN ABSTRACCIÓN) ---");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Obtener");
            System.out.println("4. Mostrar");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                scanner.next();
                opcion = -1;
            }

            if (opcion == 1) {
                lista.mostrarPosicionesValidas();
                System.out.print("Ingrese valor a insertar: ");
                int valor = scanner.nextInt();
                System.out.print("Ingrese posición: ");
                int pos = scanner.nextInt();
                lista.insertar(valor, pos); 
                System.out.println("Insertado.");
            } else if (opcion == 2) {
                System.out.print("Ingrese posición a eliminar: ");
                int pos = scanner.nextInt();
                lista.eliminar(pos);
                System.out.println("Eliminado.");
            } else if (opcion == 3) {
                System.out.print("Ingrese posición a obtener: ");
                int pos = scanner.nextInt();
                Object val = lista.obtener(pos);
                System.out.println("Valor: " + val);
            } else if (opcion == 4) {
                lista.mostrar();
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
                ejecutando = false;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}