import java.util.Scanner;

public class Menu {
    private final Lista lista; 
    private final Scanner scanner;

    public Menu() {
        lista = new Lista();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ LISTA ENLAZADA DE OBJETOS ---");
            System.out.println("1. Agregar elemento (Índice)");
            System.out.println("2. Insertar en posición específica");
            System.out.println("3. Apilar ");
            System.out.println("4. Desapilar ");
            System.out.println("5. Encolar ");
            System.out.println("6. Desencolar");
            System.out.println("7. Mostrar lista");
            System.out.println("8. Obtener elemento por índice");
            System.out.println("9. Remover elemento por índice");
            System.out.println("10. Ver tamaño y capacidad");
            System.out.println("11. Salir");
            System.out.print("Elige una opción: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("¡Entrada no válida! Por favor, ingresa un número.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            if (opcion == 1) {
                System.out.print("Ingresa el elemento a agregar: ");
                String elemento = scanner.nextLine();
                lista.agregar(elemento);
                System.out.println("Elemento agregado: " + elemento);
            }

            else if (opcion == 2) {
                System.out.print("Ingresa el índice donde insertar (0 a " + lista.tamaño() + "): ");
                if (scanner.hasNextInt()) {
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingresa el elemento: ");
                    String elemento = scanner.nextLine();
                    lista.agregar(indice, elemento);
                } else {
                    System.out.println("¡Entrada de índice no válida! Vuelve a intentar.");
                    scanner.nextLine();
                }
            }
            
            else if (opcion == 3) {
                System.out.print("Ingresa el elemento a apilar: ");
                String elemento = scanner.nextLine();
                lista.apilar(elemento);
                System.out.println("Elemento apilado: " + elemento);
            }
            
            else if (opcion == 4) {
                Object elemento = lista.desapilar();
                if (elemento != null) {
                    System.out.println("Elemento desapilado: " + elemento);
                }
            }
            
            else if (opcion == 5) {
                System.out.print("Ingresa el elemento a encolar: ");
                String elemento = scanner.nextLine();
                lista.encolar(elemento);
                System.out.println("Elemento encolado: " + elemento);
            }
            
            else if (opcion == 6) {
                Object elemento = lista.desencolar();
                if (elemento != null) {
                    System.out.println("Elemento desencolado: " + elemento);
                }
            }

            else if (opcion == 7) {
                System.out.println("Lista actual: " + lista.toString());
            }

            else if (opcion == 8) {
                System.out.print("Ingresa el índice: ");
                if (scanner.hasNextInt()) {
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    Object elemento = lista.obtener(indice);
                    if (elemento != null) {
                        System.out.println("Elemento en índice " + indice + ": " + elemento);
                    } else {
                        System.out.println("No se pudo obtener el elemento (índice fuera de rango).");
                    }
                } else {
                    System.out.println("¡Entrada de índice no válida! Vuelve a intentar.");
                    scanner.nextLine();
                }
            }

            else if (opcion == 9) {
                System.out.print("Ingresa el índice del elemento a eliminar: ");
                if (scanner.hasNextInt()) {
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    Object eliminado = lista.remover(indice);
                    if (eliminado != null) {
                        System.out.println("Elemento eliminado: " + eliminado);
                    } else {
                        System.out.println("No se pudo eliminar el elemento (índice fuera de rango).");
                    }
                } else {
                    System.out.println("¡Entrada de índice no válida! Vuelve a intentar.");
                    scanner.nextLine();
                }
            }

            else if (opcion == 10) {
                System.out.println("Tamaño actual: " + lista.tamaño());
                int capacidad = lista.obtenerCapacidad();
                System.out.println("Capacidad actual (teórica): " + ((capacidad == -1) ? "Ilimitada" : capacidad));
            }
            
            else if (opcion != 11) {
                System.out.println("Opción no válida.");
            }

        } while (opcion != 11);

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}