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

        while (ejecutando) {
            System.out.println("\n--- GESTIÓN DE LISTA ---");
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

            switch (opcion) {
                case 1 ->                     {
                        lista.mostrarPosicionesValidas();
                        System.out.print("Ingrese valor a insertar: ");
                        int valor = scanner.nextInt();
                        System.out.print("Ingrese posición: ");
                        int pos = scanner.nextInt();
                        lista.insertar(valor, pos);
                        System.out.println("Insertado.");
                    }
                case 2 ->                     {
                        System.out.print("Ingrese posición a eliminar: ");
                        int pos = scanner.nextInt();
                        lista.eliminar(pos);
                        System.out.println("Eliminado.");
                    }
                case 3 ->                     {
                        System.out.print("Ingrese posición a obtener: ");
                        int pos = scanner.nextInt();
                        Object val = lista.obtener(pos);
                        System.out.println("Valor: " + val);
                    }
                case 4 -> lista.mostrar();
                case 0 -> {
                    System.out.println("Saliendo...");
                    ejecutando = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}