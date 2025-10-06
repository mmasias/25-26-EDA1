public class Mundo {
    public static void main(String[] args) {
        Console console = new Console();
        Ludoteca ludoteca = new Ludoteca();
        Pizarra pizarra = new Pizarra();
        int opcion;

        do {
            pizarra.mostrarTitulo("LUDOTECA - SIMULACIÓN");
            System.out.println("""
                    1.  Simular llegada de niño
                    2.  Simular intento de inicio de juego
                    3.  Aisha se presenta y pide a los niños que se presenten
                    4.  Aisha pide que se presenten los niños mayores de cierta edad
                    5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra
                    6.  Aisha pide que se presenten los cinco primeros niños
                    7.  Aisha pide que se presenten los cinco últimos niños
                    8.  Aisha y Lydia dicen cuántos niños hay en cola
                    9.  Aisha dice la edad promedio de los niños en cola
                    10. Simular intento de inicio del juego de la rana
                    11. Paso de niños menores de 5 años a monitora Dalsy
                    12. Alarma contra incendios y protocolo de emergencia
                    13. Mostrar monitoras y niños

                    0.  Salir
                    """);

            opcion = console.readInt("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> ludoteca.simularLlegadaNiño();
                case 2 -> ludoteca.simularInicioJuego();
                case 3 -> ludoteca.presentacionGeneral();
                case 4 -> {
                    int edad = console.readInt("Edad mínima: ");
                    ludoteca.presentarMayoresDe(edad);
                }
                case 5 -> {
                    char letra = console.readChar("Letra inicial: ");
                    ludoteca.presentarPorInicial(letra);
                }
                case 6 -> ludoteca.presentarPrimerosCinco();
                case 7 -> ludoteca.presentarUltimosCinco();
                case 8 -> ludoteca.mostrarCantidadNiños();
                case 9 -> ludoteca.mostrarEdadPromedio();
                case 10 -> ludoteca.simularJuegoRana();
                case 11 -> ludoteca.pasarMenoresDe5();
                case 12 -> ludoteca.protocoloEmergencia();
                case 13 -> ludoteca.mostrarMonitoresYNiños();
                case 0 -> console.writeln("Saliendo del simulador...");
                default -> console.writeln("Opción no válida, intente de nuevo.");
            }

            if (opcion != 0) {
                console.writeln("\nPulse ENTER para continuar...");
                console.readString("");
            }

        } while (opcion != 0);
    }
}