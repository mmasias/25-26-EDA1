import java.util.Random;

public class Mundo {
    private static final int OPERACIONES_AUTOMATICAS = 12;
    private static final int OPCION_MINIMA = 1;
    private static final int OPCION_MAXIMA = 12;
    private static final int OPCION_ESTADO = 13;

    private static final String[] DESCRIPCIONES = {
        "", // posición 0 no usada
        "Simular llegada de niño",           // 1
        "Intentar inicio de juego",           // 2
        "Presentaciones generales",           // 3
        "Presentaciones por edad mínima",     // 4
        "Presentaciones por letra",           // 5
        "Primeros cinco niños",               // 6
        "Últimos cinco niños",                // 7
        "Conteo de asistencia",               // 8
        "Edad promedio con Aisha",            // 9
        "Verificar juego de la rana",         // 10
        "Separar menores para el juego",      // 11
        "Alarma contra incendios"             // 12
    };

    private Ludoteca ludoteca;
    private Random random;

    public Mundo() {
        ludoteca = new Ludoteca();
        random = new Random();
    }

    public void ejecutarSimulacion() {
        System.out.println("\n========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN AUTOMÁTICA");
        System.out.println("========================================\n");
        mostrarMenu();
        for (int paso = 1; paso <= OPERACIONES_AUTOMATICAS; paso++) {
            int opcion = generarOpcionAleatoria();
            System.out.println("\n--- Paso " + paso + " ---");
            System.out.println("Opción seleccionada automáticamente: " + opcion + " - " + DESCRIPCIONES[opcion]);
            procesarOpcion(opcion);
        }
        System.out.println("\n--- Resumen final ---");
        procesarOpcion(OPCION_ESTADO);
        System.out.println("Simulación finalizada.\n");
    }

    private void mostrarMenu() {
        System.out.println("Opciones disponibles durante la simulación automática:");
        for (int i = OPCION_MINIMA; i <= OPCION_MAXIMA; i++) {
            System.out.println(i + ". " + DESCRIPCIONES[i]);
        }
        System.out.println(OPCION_ESTADO + ". Mostrar estado actual");
    }

    private int generarOpcionAleatoria() {
        return OPCION_MINIMA + random.nextInt(OPCION_MAXIMA - OPCION_MINIMA + 1);
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
            case OPCION_ESTADO -> ludoteca.mostrarEstado();
            default -> System.out.println("Opción no válida");
        }
    }
}
