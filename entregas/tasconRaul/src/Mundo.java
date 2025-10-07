public class Mundo {
    private Ludoteca ludoteca;
    private Console console;

    public Mundo() {
        ludoteca = new Ludoteca();
        console = new Console();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione opción: ");
            procesarOpcion(opcion);
            console.readString("Presione ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
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
        System.out.println("0.  Salir");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ludoteca.llegadaNino(console);
                break;
            case 2:
                ludoteca.inicioJuego();
                break;
            case 3:
                ludoteca.presentacionesGenerales();
                break;
            case 4:
                ludoteca.presentacionesPorEdad(console);
                break;
            case 5:
                ludoteca.presentacionesPorInicial(console);
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
                ludoteca.edadPromedio();
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
                ludoteca.mostrarEstado();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}
