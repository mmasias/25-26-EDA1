package Reto002;

import utils.Console;

public class Mundo{

    public static void main(String[] args) {
        Console console = new Console();
        Ludoteca ludoteca = new Ludoteca();
        int opcion;

        do {
            console.writeln("========================================");
            console.writeln("        LUDOTECA - SIMULACIÓN");
            console.writeln("========================================");
            console.writeln("1.  Simular llegada de niño");
            console.writeln("2.  Simular intento de inicio de juego");
            console.writeln("3.  Aisha se presenta y pide a los niños que se presenten");
            console.writeln("4.  Aisha pide que se presenten los niños mayores de 5 años");
            console.writeln("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
            console.writeln("6.  Aisha pide que se presenten los cinco primeros niños");
            console.writeln("7.  Aisha pide que se presenten los cinco últimos niños");
            console.writeln("8.  Aisha y Lydia dicen cuántos niños hay en cola");
            console.writeln("9.  Aisha dice la edad promedio de los niños en cola");
            console.writeln("10. Simular intento de inicio del juego de la rana");
            console.writeln("11. Paso de niños menores de 5 años a monitora Dalsy");
            console.writeln("12. Alarma contra incendios y protocolo de emergencia");
            console.writeln("13. Mostrar monitoras y niños");
            console.writeln("0.  Salir");
            console.writeln("========================================");
            opcion = console.readInt("Seleccione una opción: ");
            console.writeln("");

            switch (opcion) {
                case 1:
                    ludoteca.simularLlegadaNiño();
                    break;
                case 2:
                    ludoteca.simularInicioDeJuego();
                    break;
                case 3:
                    ludoteca.presentacionesGenerales();
                    break;
                case 4:
                    ludoteca.presentacionesPorEdad();
                    break;
                case 5:
                    ludoteca.presentacionesPorInicial();
                    break;
                case 6:
                    ludoteca.primerosCinco();
                    break;
                case 7:
                    ludoteca.ultimosCinco();
                    break;
                case 8:
                    ludoteca.conteoAsistencia();
                    break;
                case 9:
                    ludoteca.edadPromedio();
                    break;
                case 10:
                    ludoteca.juegoDeLaRana();
                    break;
                case 11:
                    ludoteca.separarMenoresDeCinco();
                    break;
                case 12:
                    ludoteca.alarmaIncendios();
                    break;
                case 13:
                    ludoteca.mostrarEstado();
                    break;
                case 0:
                    console.writeln("Saliendo de la simulación...");
                    break;
                default:
                    console.writeln("Opción no válida.");
                    break;
            }

            if (opcion != 0) {
                console.readString("\nPulse ENTER para continuar...");
                console.clearScreen();
            }

        } while (opcion != 0);
    }
}

