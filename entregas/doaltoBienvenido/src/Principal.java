import java.util.Scanner;

import Lista.Lista;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista listaEnteros = new Lista();
        int opcion;

        do {
            System.out.println("\n1. Insertar\n2. Eliminar\n3. Obtener\n4. Mostrar\n0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    listaEnteros.mostrarPosicionesValidas();
                    System.out.print("Ingrese valor a insertar: ");
                    int valor = scanner.nextInt();
                    System.out.print("Ingrese posición: ");
                    int posicion = scanner.nextInt();
                    listaEnteros.insertar(valor, posicion);
                    break;
                case 2:
                    System.out.print("Ingrese posición a eliminar: ");
                    int posicionEliminar = scanner.nextInt();
                    listaEnteros.eliminar(posicionEliminar);
                    break;
                case 3:
                    System.out.print("Ingrese posición a obtener: ");
                    int posicionObtener = scanner.nextInt();
                    int valorObtenido = listaEnteros.obtener(posicionObtener);
                    if (valorObtenido != -1) {
                        System.out.println("Valor: " + valorObtenido);
                    }
                    break;
                case 4:
                    listaEnteros.mostrar();
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
