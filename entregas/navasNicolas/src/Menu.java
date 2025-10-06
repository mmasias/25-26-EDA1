import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    private Scanner scanner;
    private Aisha aisha;
    private Lydia lydia;
    private Pizarra pizarra;
    private List<Juego> juegos; // Lista de juegos disponibles

    public Menu() {
        scanner = new Scanner(System.in);
        aisha = new Aisha();
        lydia = new Lydia();
        pizarra = new Pizarra();
        juegos = new ArrayList<>();
        inicializarJuegos();
    }

    private void inicializarJuegos() {
        // Aquí puedes agregar todos los juegos disponibles
        juegos.add(new Juego("Rana")); // Juego de la rana como ejemplo
        // Agregar más juegos según necesites
    }

    public void agregarJuego(Juego juego) {
        juegos.add(juego);
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("========================================");
            System.out.println("        LUDOTECA - SIMULACIÓN");
            System.out.println("========================================");
            System.out.println("1.  Simular llegada de niño");
            System.out.println("2.  Simular intento de inicio de juego");
            System.out.println("3.  Aisha se presenta y pide a los niños que se presenten");
            System.out.println("4.  Aisha pide que se presenten los niños mayores de 5 años");
            System.out.println("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
            System.out.println("6.  Aisha pide que se presenten los cinco primeros niños");
            System.out.println("7.  Aisha pide que se presenten los cinco últimos niños");
            System.out.println("8.  Aisha y Lydia dicen cuántos niños hay en cola");
            System.out.println("9.  Aisha dice la edad promedio de los niños en cola");
            System.out.println("10. Simular intento de inicio del juego de la rana");
            System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
            System.out.println("12. Alarma contra incendios y protocolo de emergencia");
            System.out.println("13. Mostrar monitoras y niños");
            System.out.println();
            System.out.println("0.  Salir");
            System.out.print("\nSeleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            procesarOpcion(opcion);

        } while (opcion != 0);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                simularLlegadaNiño();
                break;
            case 2:
                simularInicioJuego();
                break;
            case 3:
                aishaPresentacionNiños();
                break;
            case 4:
                aishaPresentacionMayoresCinco();
                break;
            case 5:
                aishaPresentacionPorLetra();
                break;
            case 6:
                aishaPresentacionCincoPrimeros();
                break;
            case 7:
                aishaPresentacionCincoUltimos();
                break;
            case 8:
                contarNiñosEnCola();
                break;
            case 9:
                calcularEdadPromedio();
                break;
            case 10:
                iniciarJuegoRana();
                break;
            case 11:
                pasarNiñosADalsy();
                break;
            case 12:
                protocoloEmergencia();
                break;
            case 13:
                mostrarMonitorasYNiños();
                break;
            case 0:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    // Métodos privados para cada opción del menú
    private void simularLlegadaNiño() {
        // Implementar la lógica
    }

    private void simularInicioJuego() {
        // Implementar la lógica
    }

    private void aishaPresentacionNiños() {
        // Implementar la lógica
    }

    private void aishaPresentacionMayoresCinco() {
        // Implementar la lógica
    }

    private void aishaPresentacionPorLetra() {
        // Implementar la lógica
    }

    private void aishaPresentacionCincoPrimeros() {
        // Implementar la lógica
    }

    private void aishaPresentacionCincoUltimos() {
        // Implementar la lógica
    }

    private void contarNiñosEnCola() {
        // Implementar la lógica
    }

    private void calcularEdadPromedio() {
        // Implementar la lógica
    }

    private void iniciarJuegoRana() {
        // Buscar el juego de la rana en la lista de juegos
        Juego juegoRana = juegos.stream()
                               .filter(j -> j.getNombre().equalsIgnoreCase("Rana"))
                               .findFirst()
                               .orElse(null);
        
        if (juegoRana != null) {
            // Iniciar el juego de la rana
            juegoRana.iniciar();
        } else {
            System.out.println("El juego de la rana no está disponible");
        }
    }

    // Método genérico para iniciar cualquier juego
    private void iniciarJuego(String nombreJuego) {
        Juego juego = juegos.stream()
                           .filter(j -> j.getNombre().equalsIgnoreCase(nombreJuego))
                           .findFirst()
                           .orElse(null);
        
        if (juego != null) {
            juego.iniciar();
        } else {
            System.out.println("El juego " + nombreJuego + " no está disponible");
        }
    }

    private void pasarNiñosADalsy() {
        // Implementar la lógica
    }

    private void protocoloEmergencia() {
        // Implementar la lógica
    }

    private void mostrarMonitorasYNiños() {
        // Implementar la lógica
    }
}