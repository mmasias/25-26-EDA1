import java.util.Scanner;
import Lista.Lista;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista listaEnteros = new Lista();
        int opcionSeleccionada = -1;

        while (opcionSeleccionada != 0) {
            System.out.println("\n--- MENÚ DE GESTIÓN ---");
            System.out.println("1. Insertar valor");
            System.out.println("2. Eliminar posición");
            System.out.println("3. Obtener valor");
            System.out.println("4. Mostrar lista");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcionSeleccionada = scanner.nextInt();

            if (opcionSeleccionada == 1) {
                System.out.print("Ingrese valor a insertar: ");
                int valorNuevo = scanner.nextInt();
                System.out.print("Ingrese posición: ");
                int posicionInsercion = scanner.nextInt();
                listaEnteros.insertar(valorNuevo, posicionInsercion);

            } else if (opcionSeleccionada == 2) {
                System.out.print("Ingrese posición a eliminar: ");
                int posicionEliminar = scanner.nextInt();
                listaEnteros.eliminar(posicionEliminar);

            } else if (opcionSeleccionada == 3) {
                System.out.print("Ingrese posición a obtener: ");
                int posicionConsulta = scanner.nextInt();
                int resultado = listaEnteros.obtener(posicionConsulta);
                System.out.println("Valor recuperado: " + resultado);

            } else if (opcionSeleccionada == 4) {
                listaEnteros.imprimir();

            } else if (opcionSeleccionada == 0) {
                System.out.println("Cerrando el sistema...");

            } else {
                System.out.println("Error: Opción no reconocida.");
            }
        }
        scanner.close();
    }
}