import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeListas arrayDeListas = new ArrayDeListas();
        int opcion;

        do {
            System.out.println("\n1. Crear nueva lista");
            System.out.println("2. Insertar elemento en lista existente");
            System.out.println("3. Eliminar lista");
            System.out.println("4. Mostrar todas las listas");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ListaEnteros nuevaLista = new ListaEnteros();
                    System.out.println("Nueva lista creada.");

                    System.out.print("¿Cuántos elementos desea agregar a la lista? ");
                    int n = scanner.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Ingrese valor " + (i + 1) + ": ");
                        int valor = scanner.nextInt();
                        nuevaLista.insertar(valor, i);
                    }

                    System.out.print("Ingrese posición para insertar la lista en el array: ");
                    int pos = scanner.nextInt();
                    arrayDeListas.insertarLista(nuevaLista, pos);
                    break;

                case 2:
                    System.out.print("Ingrese número de lista: ");
                    int numLista = scanner.nextInt();
                    ListaEnteros lista = arrayDeListas.obtenerLista(numLista);
                    if (lista != null) {
                        System.out.print("Ingrese valor: ");
                        int valor = scanner.nextInt();
                        System.out.print("Ingrese posición: ");
                        int posElemento = scanner.nextInt();
                        lista.insertar(valor, posElemento);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese posición de la lista a eliminar: ");
                    int posEliminar = scanner.nextInt();
                    arrayDeListas.eliminarLista(posEliminar);
                    break;

                case 4:
                    arrayDeListas.mostrar();
                    break;

                case 0:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
