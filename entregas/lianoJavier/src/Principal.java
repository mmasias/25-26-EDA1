import java.util.Scanner;

import Lista.Lista;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista listaEnteros = new Lista();

        int opcion;

        do {
            System.out.println();
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Obtener");
            System.out.println("4. Mostrar");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println(listaEnteros.obtenerTextoPosicionesValidas());
                    System.out.print("Ingrese valor a insertar: ");
                    int valor = scanner.nextInt();
                    System.out.print("Ingrese posición: ");
                    int posicion = scanner.nextInt();
                    listaEnteros.insertar(valor, posicion);
                }
                case 2 -> {
                    System.out.print("Ingrese posición a eliminar: ");
                    int posicionEliminar = scanner.nextInt();
                    listaEnteros.eliminar(posicionEliminar);
                }
                case 3 -> {
                    System.out.print("Ingrese posición a obtener: ");
                    int posicionObtener = scanner.nextInt();
                    int valorObtenido = listaEnteros.obtener(posicionObtener);
                    System.out.println("Valor: " + valorObtenido);
                }
                case 4 -> System.out.println(listaEnteros.obtenerTexto());
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
