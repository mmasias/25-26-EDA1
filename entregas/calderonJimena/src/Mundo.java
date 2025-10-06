import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner entradaUsuario;

    private static final int OPCION_SALIR = 0;
    private static final int OPCION_LLEGADA_NINO = 1;
    private static final int OPCION_INICIO_JUEGO = 2;
    private static final int OPCION_PRESENTACION_GENERAL = 3;
    private static final int OPCION_PRESENTACION_EDAD = 4;
    private static final int OPCION_PRESENTACION_INICIAL = 5;
    private static final int OPCION_PRESENTACION_PRIMEROS = 6;
    private static final int OPCION_PRESENTACION_ULTIMOS = 7;
    private static final int OPCION_CONTEO = 8;
    private static final int OPCION_PROMEDIO = 9;
    private static final int OPCION_JUEGO_RANA = 10;
    private static final int OPCION_SEPARAR_JUEGO = 11;
    private static final int OPCION_EMERGENCIA = 12;
    private static final int OPCION_ESTADO = 13;

    public Mundo() {
        entradaUsuario = new Scanner(System.in);
        ludoteca = new Ludoteca();
    }

    public static void main(String[] args) {
        new Mundo().iniciarPrograma();
    }

    private void iniciarPrograma() {
        int opcionSeleccionada;
        do {
            mostrarMenuPrincipal();
            opcionSeleccionada = leerNumero("Seleccione una opcion: ");
            ejecutarOpcion(opcionSeleccionada);
            if (opcionSeleccionada != OPCION_SALIR) {
                pausarEjecucion();
            }
        } while (opcionSeleccionada != OPCION_SALIR);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACION");
        System.out.println("========================================");
        System.out.println("1.  Simular llegada de nino");
        System.out.println("2.  Simular intento de inicio de juego");
        System.out.println("3.  Aisha se presenta y pide a los ninos que se presenten");
        System.out.println("4.  Aisha pide que se presenten los ninos mayores de 5 anos");
        System.out.println("5.  Aisha pide que se presenten los ninos cuyo nombre empieza por letra");
        System.out.println("6.  Aisha pide que se presenten los cinco primeros ninos");
        System.out.println("7.  Aisha pide que se presenten los cinco ultimos ninos");
        System.out.println("8.  Aisha y Lydia dicen cuantos ninos hay en cola");
        System.out.println("9.  Aisha dice la edad promedio de los ninos en cola");
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de ninos menores de 5 anos a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y ninos");
        System.out.println("0.  Salir");
    }

    private void ejecutarOpcion(int opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case OPCION_LLEGADA_NINO:
                String nombreNino = leerTexto("Nombre del nino: ");
                int edadNino = leerNumero("Edad del nino: ");
                ludoteca.registrarLlegadaNino(nombreNino, edadNino);
                break;
            case OPCION_INICIO_JUEGO:
                ludoteca.intentarIniciarJuego();
                break;
            case OPCION_PRESENTACION_GENERAL:
                ludoteca.realizarPresentacionGeneral();
                break;
            case OPCION_PRESENTACION_EDAD:
                ludoteca.presentarMayoresDeEdad(5);
                break;
            case OPCION_PRESENTACION_INICIAL:
                String letraInicial = leerTexto("Letra inicial: ");
                ludoteca.presentarPorInicial(letraInicial);
                break;
            case OPCION_PRESENTACION_PRIMEROS:
                ludoteca.presentarPrimerosCincoNinos();
                break;
            case OPCION_PRESENTACION_ULTIMOS:
                ludoteca.presentarUltimosCincoNinos();
                break;
            case OPCION_CONTEO:
                ludoteca.mostrarConteoAsistencia();
                break;
            case OPCION_PROMEDIO:
                ludoteca.mostrarEdadPromedio();
                break;
            case OPCION_JUEGO_RANA:
                ludoteca.verificarCondicionesJuegoRana();
                break;
            case OPCION_SEPARAR_JUEGO:
                ludoteca.separarNinosParaJuego();
                break;
            case OPCION_EMERGENCIA:
                ludoteca.activarProtocoloEmergencia();
                break;
            case OPCION_ESTADO:
                ludoteca.mostrarEstadoGeneral();
                break;
            case OPCION_SALIR:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private void pausarEjecucion() {
        System.out.println();
        System.out.print("Presione ENTER para continuar...");
        entradaUsuario.nextLine();
    }

    private int leerNumero(String mensaje) {
        System.out.print(mensaje);
        int valor = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        return valor;
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entradaUsuario.nextLine();
    }
}
