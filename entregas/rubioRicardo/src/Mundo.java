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
            opcion = console.readInt("Seleccione una opción: ");
            procesarOpcion(opcion);
            console.readString("Presione ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
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
    }
    

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                simularLlegada();
                break;
            case 2:
                simularInicioJuego();
                break;
            case 3:
                presentacionGeneral();
                break;
            case 4:
                presentacionPorEdad();
                break;
            case 5:
                presentacionPorInicial();
                break;
            case 6:
                primerosCinco();
                break;
            case 7:
                ultimosCinco();
                break;
            case 8:
                conteoAsistencia();
                break;
            case 9:
                edadPromedioAisha();
                break;
            case 10:
                intentoJuegoRana();
                break;
            case 11:
                separarNiñosMenores();
                break;
            case 12:
                alarmaEmergencia();
                break;
            case 13:
                ludoteca.mostrarEstado(console);
                break;
            case 0:
                console.writeln("Saliendo de la simulación...");
                break;
            default:
                console.writeln("Opción inválida.");
        }
    }
    
    private void simularLlegada() {
        String nombre = console.readString("Nombre del niño: ");
        int edad = console.readInt("Edad del niño: ");
        Niño nuevoNiño = new Niño(nombre, edad);
        ludoteca.recibirNiñoEnLydia(nuevoNiño);
        console.writeln("Llega " + nombre + " (" + edad + " años)");
        console.writeln(nombre + " pasa a la cola de Lydia");
    }

    private void simularInicioJuego() {
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

    private void presentacionPorInicial() {
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

    private void conteoAsistencia() {
        int total = ludoteca.getLydia().getCantidad() + ludoteca.getAisha().getCantidad() + ludoteca.getDalsy().getCantidad();
        console.writeln("CONTEO DE ASISTENCIA:");
        console.writeln("Lydia tiene " + ludoteca.getLydia().getCantidad() + " niños en cola");
        console.writeln("Aisha tiene " + ludoteca.getAisha().getCantidad() + " niños en cola");
        console.writeln("Dalsy tiene " + ludoteca.getDalsy().getCantidad() + " niños en cola");
        console.writeln("Total: " + total + " niños");
    }
    private void edadPromedioAisha() {
        if (!ludoteca.getAisha().tieneNiños()) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        double promedio = ludoteca.getAisha().calcularEdadPromedio();
        console.writeln("Edad promedio de los niños en la cola de Aisha: " + promedio + " años");
    }
    
    private void intentoJuegoRana() {
        Niño[] listaAisha = ludoteca.getAisha().obtenerTodosNiños();
        int total = listaAisha.length;
        int mayoresDeCinco = 0;
        for (Niño niñoActual : listaAisha) {
            if (niñoActual.getEdad() >= 5) mayoresDeCinco++;
        }
        console.writeln("Verificando condiciones para el juego de la rana...");
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
    
   
    private void separarNiñosMenores() {
        console.writeln("Separando niños menores de 5 años para Dalsy...");
        Niño[] quedan = ludoteca.getAisha().separarMenoresDe(5, ludoteca.getDalsy());
        console.writeln("Niños que se quedan con Aisha:");
        for (Niño niñoActual : quedan) {
            console.writeln(" - " + niñoActual.getNombre() + " (" + niñoActual.getEdad() + " años)");
        }
    }
    
    
    private void alarmaEmergencia() {
        console.writeln("¡ALARMA CONTRA INCENDIOS!");
        console.writeln("PROTOCOLO DE EMERGENCIA ACTIVADO");
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
        console.writeln(totalTransferidos + " niños transferidos");
        console.writeln("Lydia ahora tiene " + ludoteca.getLydia().getCantidad() + " niños listos para evacuar en orden");
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}