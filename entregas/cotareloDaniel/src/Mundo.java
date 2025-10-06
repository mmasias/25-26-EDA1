import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;

    public Mundo() {
        this.ludoteca = new Ludoteca();
    }

    public void ejecutarSimulacion(Scanner entrada) {
        int opcionSeleccionada;
        opcionSeleccionada = -1;
        do {
            mostrarMenu();
            opcionSeleccionada = leerEntero(entrada);
            procesarOpcion(opcionSeleccionada, entrada);
            if (opcionSeleccionada != 0) {
                System.out.println("Presione ENTER para continuar...");
                leerLinea(entrada);
            }
        } while (opcionSeleccionada != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println();
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
        System.out.println();
        System.out.println("Seleccione una opción:");
    }

    private void procesarOpcion(int opcionSeleccionada, Scanner entrada) {
        switch (opcionSeleccionada) {
            case 1:
                opcionLlegadaNino(entrada);
                break;
            case 2:
                ludoteca.intentoInicioJuego();
                break;
            case 3:
                ludoteca.presentacionesGenerales();
                break;
            case 4:
                ludoteca.presentacionesPorEdadMinima(6);
                break;
            case 5:
                opcionPresentacionesPorInicial(entrada);
                break;
            case 6:
                ludoteca.presentarPrimerosCinco();
                break;
            case 7:
                ludoteca.presentarUltimosCinco();
                break;
            case 8:
                ludoteca.conteoAsistencia();
                break;
            case 9:
                ludoteca.edadPromedioAisha();
                break;
            case 10:
                ludoteca.intentoJuegoRana();
                break;
            case 11:
                ludoteca.separarParaJuegoRana();
                break;
            case 12:
                ludoteca.alarmaIncendios();
                break;
            case 13:
                ludoteca.mostrarEstadoActual();
                break;
            case 0:
                System.out.println("Fin");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    private void opcionLlegadaNino(Scanner entrada) {
        String nombreIntroducido;
        int edadIntroducida;
        System.out.println("Introduzca el nombre del niño:");
        nombreIntroducido = leerLinea(entrada);
        System.out.println("Introduzca la edad del niño:");
        edadIntroducida = leerEntero(entrada);
        ludoteca.llegadaNino(nombreIntroducido, edadIntroducida);
    }

    private void opcionPresentacionesPorInicial(Scanner entrada) {
        String letraIntroducida;
        letraIntroducida = "";
        System.out.println("Introduzca la letra inicial:");
        letraIntroducida = leerLinea(entrada);
        ludoteca.presentacionesPorInicial(letraIntroducida);
    }

    private int leerEntero(Scanner entrada) {
        int numeroLeido;
        numeroLeido = 0;
        if (entrada.hasNextInt()) {
            numeroLeido = entrada.nextInt();
            entrada.nextLine();
        } else {
            entrada.nextLine();
        }
        return numeroLeido;
    }

    private String leerLinea(Scanner entrada) {
        String lineaLeida;
        lineaLeida = "";
        if (entrada.hasNextLine()) {
            lineaLeida = entrada.nextLine();
        }
        return lineaLeida;
    }
}
