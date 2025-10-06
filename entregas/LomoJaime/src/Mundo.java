import utils.Console;

class Mundo {
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
            console.readString("\nPulse ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        console.clearScreen();
        String linea = new String(new char[50]).replace('\0', '=');
        console.writeln(linea);
        console.writeln("TELÉFONO DESCACHARRADO v2.0 - MENÚ INTERACTIVO");
        console.writeln(linea);
        console.writeln("1. Simular llegada de niño");
        console.writeln("2. Simular intento de inicio de juego");
        console.writeln("3. Aisha se presenta y pide a los niños que se presenten");
        console.writeln("4. Aisha pide que se presenten los niños mayores de X años");
        console.writeln("5. Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        console.writeln("6. Aisha pide que se presenten los cinco primeros niños");
        console.writeln("7. Aisha pide que se presenten los cinco últimos niños");
        console.writeln("8. Aisha y Lydia dicen cuántos niños hay en cola");
        console.writeln("9. Aisha dice la edad promedio de los niños en cola");
        console.writeln("10. Simular intento de inicio del juego de la rana");
        console.writeln("11. Paso de niños menores de 5 años a monitora Dalsy");
        console.writeln("12. Alarma contra incendios y protocolo de emergencia");
        console.writeln("13. Mostrar monitoras y niños");
        console.writeln("0. Salir");
        console.writeln(linea);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                opcion1();
                break;
            case 2:
                opcion2();
                break;
            case 3:
                opcion3();
                break;
            case 4:
                opcion4();
                break;
            case 5:
                opcion5();
                break;
            case 6:
                opcion6();
                break;
            case 7:
                opcion7();
                break;
            case 8:
                opcion8();
                break;
            case 9:
                opcion9();
                break;
            case 10:
                opcion10();
                break;
            case 11:
                opcion11();
                break;
            case 12:
                opcion12();
                break;
            case 13:
                opcion13();
                break;
            case 0:
                console.writeln("Saliendo...");
                break;
            default:
                console.writeln("Opción no válida");
        }
    }

    private void opcion1() {
        String nombre = console.readString("Nombre del niño: ");
        int edad = console.readInt("Edad del niño: ");
        console.writeln("Llega " + nombre + " (" + edad + " años)");
        ludoteca.recibirNiño(new Niño(nombre, edad));
    }

    private void opcion2() {
        ludoteca.intentarInicioJuego();
    }

    private void opcion3() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños para presentar");
            return;
        }
        ludoteca.presentarATodos();
    }

    private void opcion4() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        int edad = console.readInt("Edad mínima: ");
        ludoteca.presentarMayoresDe(edad);
    }

    private void opcion5() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        char letra = console.readString("Letra inicial: ").toUpperCase().charAt(0);
        ludoteca.presentarNombresConLetra(letra);
    }

    private void opcion6() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        ludoteca.presentarPrimeros();
    }

    private void opcion7() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        ludoteca.presentarUltimos();
    }

    private void opcion8() {
        ludoteca.mostrarConteo();
    }

    private void opcion9() {
        ludoteca.mostrarEdadPromedio();
    }

    private void opcion10() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        ludoteca.intentarJuegoRana();
    }

    private void opcion11() {
        if (ludoteca.getAisha().getCantidadNiños() == 0) {
            console.writeln("Aisha no tiene niños");
            return;
        }
        ludoteca.separarParaJuegoRana();
    }

    private void opcion12() {
        ludoteca.activarAlarmaIncendios();
    }

    private void opcion13() {
        ludoteca.mostrarEstado();
    }

    public static void main(String[] args) {
        new Mundo().ejecutarSimulacion();
    }
}