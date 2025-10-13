import java.util.Scanner;

public class Mundo {    
    Monitor[] monitoras = new Monitor[NUM_MONITORAS];
    // Estructura para manejar las colas de las monitoras
    private static final int NUM_MONITORAS = 3;
    private static final int MAX_NINOS = 10;
    Nino[][] colas = new Nino[NUM_MONITORAS][MAX_NINOS];
    int[] frentes = new int[NUM_MONITORAS];
    int[] finales = new int[NUM_MONITORAS];
    private Scanner input;


    public Mundo() {
    input = new Scanner(System.in);
    monitoras[0] = new Monitor("Lydia");
    monitoras[1] = new Monitor("Aisha");
    monitoras[2] = new Monitor("Dalsy");
    }
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.iniciarSimulacion();


        
    }

    public void iniciarSimulacion(){
        int opcion;

        do {
            mostrarMenu();
            opcion = input.nextInt();
            input.nextLine();
            procesarOpcion(opcion);
            System.out.println("Presiona ENTER para continuar...");    
            input.nextLine();
        } while (opcion != 0);

        System.out.println("¡Hasta luego!");
    }


public void mostrarMenu() {
    System.out.println("========================================");
    System.out.println("        LUDOTECA - SIMULACIÓN");
    System.out.println("========================================");
    System.out.println("1. Simular llegada de niño");
    System.out.println("2. Simular intento de inicio de juego");
    System.out.println("3. Aisha se presenta y pide a los niños que se presenten");
    System.out.println("4. Aisha pide que se presenten los niños mayores de 5 años");
    System.out.println("5. Aisha pide que se presenten los niños cuyo nombre empieza por letra");
    System.out.println("6. Aisha pide que se presenten los cinco primeros niños");
    System.out.println("7. Aisha pide que se presenten los cinco últimos niños");
    System.out.println("8. Aisha y Lydia dicen cuántos niños hay en cola");
    System.out.println("9. Aisha dice la edad promedio de los niños en cola");
    System.out.println("10. Simular intento de inicio del juego de la rana");
    System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
    System.out.println("12. Alarma contra incendios y protocolo de emergencia");
    System.out.println("13. Mostrar monitoras y niños");
    System.out.println("0. Salir");
    System.out.print("Seleccione una opción: ");
}


public void procesarOpcion(int opcion){
    switch (opcion) {
        case 1:
                System.out.println("Has elegido: Simular llegada de niño");
                llegadaNino();
                break;
            case 2:
                System.out.println("Has elegido: Simular intento de inicio de juego");
                // Aquí iría la lógica de juego
                break;
            case 3:
                System.out.println("Has elegido: Presentaciones generales");
                // Aquí la lógica de presentaciones
                break;
                case 13:
                verEstado();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida, intenta de nuevo.");
    }
}

public void llegadaNino() {
Nino nino = new Nino();

if (finales[0] < MAX_NINOS) {
    colas[0][finales[0]] = nino;
    finales[0]++;
    System.out.println(nino.nombre + " ha sido agregado a la cola de Lydia.");
} else {
    System.out.println("La cola de Lydia está llena, no se puede agregar más niños.");
}
}


public void verEstado() {
    for (int m = 0; m < NUM_MONITORAS; m++) {
        System.out.println("Cola de " + monitoras[m].getNombre() + ":");
        if (frentes[m] == finales[m]) {
            System.out.println("  (Vacía)");
        } else {
            for (int i = frentes[m]; i < finales[m]; i++) {
                System.out.print("  Posición " + (i - frentes[m] + 1) + ": ");
                System.out.print(colas[m][i].nombre + ", edad: " + colas[m][i].edad);
                if (i == frentes[m]) System.out.print(" (Adelante)");
                if (i == finales[m] - 1) System.out.print(" (Atrás)");
                System.out.println();
            }
        }
        System.out.println();
    }
}
}