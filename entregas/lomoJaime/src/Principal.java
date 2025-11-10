import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeListas arrayDeListas = new ArrayDeListas();
        int opcion;

        do {
            System.out.println("\n1. Crear lista y agregar al array");
            System.out.println("2. Eliminar lista");
            System.out.println("3. Mostrar array de listas");
            System.out.println("4. Insertar elemento en lista específica");
            System.out.println("5. Mostrar lista específica");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ListaEnteros nuevaLista = new ListaEnteros();
                    System.out.println("Se ha creado una nueva lista vacía.");
                    arrayDeListas.mostrarPosicionesValidas();
                    System.out.print("Ingrese posición para insertar la lista: ");
                    int pos = scanner.nextInt();
                    arrayDeListas.insertarLista(nuevaLista, pos);
                    break;

                case 2:
                    System.out.print("Ingrese posición de la lista a eliminar: ");
                    int posEliminar = scanner.nextInt();
                    arrayDeListas.eliminarLista(posEliminar);
                    break;

                case 3:
                    arrayDeListas.mostrar();
                    break;

                case 4:
                    System.out.print("Ingrese posición de la lista: ");
                    int posLista = scanner.nextInt();
                    ListaEnteros lista = arrayDeListas.obtenerLista(posLista);
                    if (lista != null) {
                        System.out.print("Ingrese valor a insertar: ");
                        int valor = scanner.nextInt();
                        System.out.print("Ingrese posición dentro de la lista: ");
                        int posElemento = scanner.nextInt();
                        lista.insertar(valor, posElemento);
                    }
                    break;

                case 5:
                    System.out.print("Ingrese posición de la lista a mostrar: ");
                    int posMostrar = scanner.nextInt();
                    ListaEnteros listaMostrar = arrayDeListas.obtenerLista(posMostrar);
                    if (listaMostrar != null) {
                        listaMostrar.mostrar();
                        System.out.println();
                    }
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
