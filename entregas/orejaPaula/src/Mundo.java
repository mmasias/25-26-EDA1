import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner sc;
}
public Mundo() {
    ludoteca = new Ludoteca();
    sc = new Scanner(System.in);
}
public void ejecutarSimulacion() {
    int opcion;
    do {
        mostrarMenu();
        opcion = leerInt("Seleccione una opción: ");
        procesarOpcion(opcion);
        if (opcion != 0) {
            System.out.println("\nPresione ENTER para continuar...");
            sc.nextLine();
        }
    } while (opcion != 0);
}

