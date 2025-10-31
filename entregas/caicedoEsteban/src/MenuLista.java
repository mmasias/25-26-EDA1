import java.util.Scanner;

public class MenuLista {
    private SimuladorLista lista;
    private Scanner scanner;

    public MenuLista() {
        lista = new SimuladorLista();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar elemento");
            System.out.println("2. Insertar en posición específica");
            System.out.println("3. Mostrar lista");
            System.out.println("4. Obtener elemento por índice");
            System.out.println("5. Remover elemento");
            System.out.println("6. Ver tamaño y capacidad");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Ingresa el elemento a agregar: ");
                String elemento = scanner.nextLine();
                lista.agregar(elemento);
            }

            else if (opcion == 2) {
                System.out.print("Ingresa el índice donde insertar: ");
                int indice = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingresa el elemento: ");
                String elemento = scanner.nextLine();
                lista.agregar(indice, elemento);
            }

            else if (opcion == 3) {
                System.out.println("Lista actual: " + lista.toString());
            }

            else if (opcion == 4) {
                System.out.print("Ingresa el índice: ");
                int indice = scanner.nextInt();
                Object elemento = lista.obtener(indice);
                if (elemento != null) {
                    System.out.println("Elemento en índice " + indice + ": " + elemento);
                }
            }

            else if (opcion == 5) {
                System.out.print("Ingresa el índice del elemento a eliminar: ");
                int indice = scanner.nextInt();
                Object eliminado = lista.remover(indice);
                if (eliminado != null) {
                    System.out.println("Elemento eliminado: " + eliminado);
                }
            }

            else if (opcion == 6) {
                System.out.println("Tamaño actual: " + lista.getTamaño());
                System.out.println("Capacidad actual: " + lista.getCapacidad());
            }

        } while (opcion != 7);

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}
