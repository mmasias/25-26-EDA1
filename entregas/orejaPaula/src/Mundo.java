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
private void mostrarMenu() {
    System.out.println("========================================");
    System.out.println("        LUDOTECA - SIMULACIÓN");
    System.out.println("========================================");
    System.out.println("1.  Simular llegada de niño");
    System.out.println("2.  Simular intento de inicio de juego");
    System.out.println("3.  Aisha se presenta y pide a los niños que se presenten");
    System.out.println("4.  Aisha pide que se presenten los niños mayores de X años");
    System.out.println("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
    System.out.println("6.  Aisha pide que se presenten los cinco primeros niños");
    System.out.println("7.  Aisha pide que se presenten los cinco últimos niños");
    System.out.println("8.  Aisha y Lidia dicen cuántos niños hay en cola");
    System.out.println("9.  Aisha dice la edad promedio de los niños en cola");
    System.out.println("10. Simular intento de inicio del juego de la rana");
    System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
    System.out.println("12. Alarma contra incendios y protocolo de emergencia");
    System.out.println("13. Mostrar monitoras y niños");
    System.out.println("0.  Salir");
    System.out.println();
}

