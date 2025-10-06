import utils.Console;

class Mundo {
    private Ludoteca ludoteca;
    private Console console;

    public Mundo() {
        console = new Console();
        ludoteca = new Ludoteca();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Elige tu opción: ");
            procesarOpcion(opcion);
            console.readString("\n ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        console.clearScreen();
        console.writeln("╔══════════════════════════════════════╗");
        console.writeln("║           LUDOTECA - MENU           ║");
        console.writeln("╠══════════════════════════════════════╣");
        console.writeln("║ 1  │ Llegada de un nuevo niño        ║");
        console.writeln("║ 2  │ Intentar iniciar un juego       ║");
        console.writeln("║ 3  │ Presentación general de niños   ║");
        console.writeln("║ 4  │ Presentar niños mayores de 5   ║");
        console.writeln("║ 5  │ Presentar niños por inicial    ║");
        console.writeln("║ 6  │ Presentar primeros 5 niños     ║");
        console.writeln("║ 7  │ Presentar últimos 5 niños      ║");
        console.writeln("║ 8  │ Conteo de asistencia           ║");
        console.writeln("║ 9  │ Edad promedio en Aisha         ║");
        console.writeln("║ 10 │ Intentar juego de la rana      ║");
        console.writeln("║ 11 │ Separar niños menores a Dalsy ║");
        console.writeln("║ 12 │ Alarma y protocolo emergencia  ║");
        console.writeln("║ 13 │ Mostrar monitoras y niños     ║");
        console.writeln("║ 0  │ Salir de la ludoteca           ║");
        console.writeln("╚══════════════════════════════════════╝\n");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> llegadaNiños();
            case 2 -> inicioJuego();
            case 3 -> presentacionGeneral();
            case 4 -> presentacionPorEdad();
            case 5 -> presentacionSegunInicial();
            case 6 -> primerosCinco();
            case 7 -> ultimosCinco();
            case 8 -> cantidadNiños();
            case 9 -> edadAisha();
            case 10 -> juegoDeLaRana();
            case 11 -> dividirNiñosMenores();
            case 12 -> alarmaEmergencia();
            case 13 -> ludoteca.mostrarEstado(console);
            case 0 -> console.writeln("¡Gracias por visitar la ludoteca!");
            default -> console.writeln("Opción inválida, intenta nuevamente.");
            
        }
    }

    private void llegadaNiños() {
        String nombre = console.readString("Nombre del niño: ");
        int edad = console.readInt("Edad del niño: ");
        Niño nuevoNiño = new Niño(nombre, edad);
        ludoteca.recibirNiñoEnLydia(nuevoNiño);
        console.writeln("Llega " + nombre + " (" + edad + " años)");
        console.writeln(nombre + " pasa a la cola de Lydia");
    }

    private void inicioJuego() {
        if (ludoteca.getAisha().getCantidad() >= 5) {
            console.writeln("Lydia transfiere sus niños a Aisha");
            ludoteca.getAisha().mostrarListaNiños(console);
        } else {
            console.writeln("No hay suficientes niños para iniciar el juego");
            console.writeln("Se necesitan al menos 5 niños");
        }
    }

    private void presentacionGeneral() {
        console.writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        Niño[] lista = ludoteca.getAisha().obtenerTodosNiños();
        for (Niño niñoActual : lista) {
            niñoActual.presentarse(console);
        }
    }

    private void presentacionPorEdad() {
        int edadMinima = 5;
        console.writeln("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        Niño[] lista = ludoteca.getAisha().obtenerTodosNiños();
        for (Niño niñoActual : lista) {
            if (niñoActual.getEdad() > edadMinima) {
                niñoActual.presentarse(console);
            }
        }
    }

    private void presentacionSegunInicial() {
        char letra = console.readChar("Letra inicial: ", true);
        console.writeln("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        Niño[] lista = ludoteca.getAisha().obtenerTodosNiños();
        for (Niño niñoActual : lista) {
            if (Character.toUpperCase(niñoActual.getNombre().charAt(0)) == letra) {
                console.writeln(niñoActual.getNombre() + ": Hola, soy " + niñoActual.getNombre());
            }
        }
    }

    private void primerosCinco() {
        console.writeln("Aisha pide que se presenten los primeros 5 niños:");
        Niño[] lista = ludoteca.getAisha().obtenerTodosNiños();
        for (int i = 0; i < lista.length && i < 5; i++) {
            console.writeln(lista[i].getNombre() + ": Hola, soy " + lista[i].getNombre());
        }
    }

    private void ultimosCinco() {
        console.writeln("Aisha pide que se presenten los últimos 5 niños:");
        Niño[] lista = ludoteca.getAisha().obtenerTodosNiños();
        int inicio = lista.length - 5;
        if (inicio < 0) inicio = 0;
        for (int i = inicio; i < lista.length; i++) {
            console.writeln(lista[i].getNombre() + ": Hola, soy " + lista[i].getNombre());
        }
    }

    private void cantidadNiños() {
        int total = ludoteca.getLydia().getCantidad() + ludoteca.getAisha().getCantidad() + ludoteca.getDalsy().getCantidad();
        console.writeln("CONTEO DE ASISTENCIA:");
        console.writeln("Lydia tiene " + ludoteca.getLydia().getCantidad() + " niños en fila");
        console.writeln("Aisha tiene " + ludoteca.getAisha().getCantidad() + " niños en fila");
        console.writeln("Dalsy tiene " + ludoteca.getDalsy().getCantidad() + " niños en fila");
        console.writeln("Total: " + total + " niños");
    }
    private void edadAisha() {
        if (!ludoteca.getAisha().tieneNiños()) {
            console.writeln("No hay niños en la fila de Aisha");
            return;
        }
        double promedio = ludoteca.getAisha().calcularEdadPromedio();
        console.writeln("Edad promedio de los niños en la fila de Aisha: " + promedio + " años");
    }

    private void juegoDeLaRana() {
        Niño[] listaAisha = ludoteca.getAisha().obtenerTodosNiños();
        int total = listaAisha.length;
        int mayoresDeCinco = 0;
        for (Niño niñoActual : listaAisha) {
            if (niñoActual.getEdad() >= 5) mayoresDeCinco++;
        }
        console.writeln("Confirmando condiciones para el juego de la rana...");
        console.writeln("Total de niños: " + total);
        console.writeln("Niños de 5 años o más: " + mayoresDeCinco);
        if (mayoresDeCinco >= (total + 1) / 2) {
            console.writeln("¡Más de la mitad cumplen la condición!");
            console.writeln("¡Pueden jugar al juego de la rana!");
        } else {
            console.writeln("No hay suficientes niños mayores de 5 años");
            console.writeln("No pueden jugar todavía");
        }
    }
    
   
    private void dividirNiñosMenores() {
        console.writeln("Separando niños menores de 5 años que se pasan a la fila de Dalsy...");
        Niño[] quedan = ludoteca.getAisha().separarMenoresDe(5, ludoteca.getDalsy());
        console.writeln("Niños que se quedan con Aisha en la fila:");
        for (Niño niñoActual : quedan) {
            console.writeln(" - " + niñoActual.getNombre() + " (" + niñoActual.getEdad() + " años)");
        }
    }
    
    
    private void alarmaEmergencia() {
        console.writeln("¡ALARMA DE INCENDIOS ACTIVADA!");
        console.writeln("PROTOCOLO DE EMERGENCIA EN OPERACIÓN");
        int totalTransferidos = 0;
        if (ludoteca.getDalsy().tieneNiños()) {
            Niño[] listaDalsy = ludoteca.getDalsy().obtenerTodosNiños();
            for (Niño niñoActual : listaDalsy) {
                ludoteca.getLydia().recibirNiño(niñoActual);
                totalTransferidos++;
            }
            ludoteca.getDalsy().vaciarCola();
        }
        if (ludoteca.getAisha().tieneNiños()) {
            Niño[] listaAisha = ludoteca.getAisha().obtenerTodosNiños();
            for (Niño niñoActual : listaAisha) {
                ludoteca.getLydia().recibirNiño(niñoActual);
                totalTransferidos++;
            }
            ludoteca.getAisha().vaciarCola();
        }
        console.writeln(totalTransferidos + " niños cambiados de fila");
        console.writeln("Lydia ahora tiene " + ludoteca.getLydia().getCantidad() + " niños listos para evacuar en orden");
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}
