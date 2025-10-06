

import java.util.Random;
import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner scanner;
    private Random random = new Random();

    public Mundo() {
        ludoteca = new Ludoteca();
        scanner = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = 1 + random.nextInt(13); // genera opción aleatoria entre 1 y 13
            System.out.println("Opción seleccionada automáticamente: " + opcion);
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.print("\n--- FIN DE EJECUCIÓN AUTOMÁTICA ---\n");
            }
            if (opcion == 13) opcion = 0; // terminar después de mostrar estado
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN AUTOMÁTICA");
        System.out.println("========================================");
        System.out.println("""
1.  Simular llegada de niño (aleatoria)
2.  Simular intento de inicio de juego
3.  Presentaciones generales
4.  Presentaciones por edad mínima aleatoria
5.  Presentaciones por letra aleatoria
6.  Primeros cinco niños
7.  Últimos cinco niños
8.  Conteo de asistencia
9.  Edad promedio
10. Juego de la rana
11. Separar menores de 5 años
12. Alarma contra incendios
13. Mostrar estado
0.  Salir
""");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> ludoteca.llegadaNiñoAleatorio();
            case 2 -> ludoteca.intentoInicioJuego();
            case 3 -> ludoteca.presentacionesGenerales();
            case 4 -> ludoteca.presentacionesMayoresDeAleatorio();
            case 5 -> ludoteca.presentacionesPorLetraAleatoria();
            case 6 -> ludoteca.primerosCinco();
            case 7 -> ludoteca.ultimosCinco();
            case 8 -> ludoteca.conteoAsistencia();
            case 9 -> ludoteca.edadPromedioAisha();
            case 10 -> ludoteca.intentoJuegoRana();
            case 11 -> ludoteca.separarParaJuego();
            case 12 -> ludoteca.alarmaIncendio();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción no válida");
        }
    }
}
