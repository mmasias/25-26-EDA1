public class Mundo {
    private final Ludoteca ludoteca = new Ludoteca();
    private final Consola consola = new Consola();

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = consola.leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
            if (opcion != 0) consola.pausar();
        } while (opcion != 0);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> opcionLlegadaNinio();
            case 2 -> ludoteca.intentarInicioJuego();
            case 3 -> validarAisha("No hay niños en la cola de Aisha", ludoteca.getAisha().tamanoCola() > 0, ludoteca::presentacionesGenerales);
            case 4 -> opcionPresentacionesPorEdad();
            case 5 -> opcionPresentacionesPorInicial();
            case 6 -> validarAisha("No hay niños en la cola de Aisha", ludoteca.getAisha().tamanoCola() > 0, ludoteca::primerosCinco);
            case 7 -> validarAisha("No hay niños en la cola de Aisha", ludoteca.getAisha().tamanoCola() > 0, ludoteca::ultimosCinco);
            case 8 -> ludoteca.conteoAsistencia();
            case 9 -> ludoteca.edadPromedioAisha();
            case 10 -> ludoteca.intentoJuegoRana();
            case 11 -> validarAisha("No hay niños en la cola de Aisha", ludoteca.getAisha().tamanoCola() > 0, ludoteca::separarParaJuego);
            case 12 -> ludoteca.alarmaIncendios();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida");
        }
    }

    private void opcionLlegadaNinio() {
        String nombre = consola.leerCadena("Nombre del niño: ");
        int edad;
        while (true) {
            edad = consola.leerEntero("Edad del niño: ");
            if (edad >= 0 && edad <= 17) break;
            System.out.println("La edad debe estar entre 0 y 17.");
        }
        ludoteca.llegadaNinio(nombre, edad);
    }

    private void opcionPresentacionesPorEdad() {
        if (ludoteca.getAisha().tamanoCola() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int edadMin = consola.leerEntero("Edad mínima: ");
        ludoteca.presentacionesPorEdadMinima(edadMin);
    }

    private void opcionPresentacionesPorInicial() {
        if (ludoteca.getAisha().tamanoCola() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        char letra = consola.leerLetra("Letra inicial: ");
        ludoteca.presentacionesPorInicial(letra);
    }

    private void validarAisha(String mensajeVacio, boolean condicion, Runnable accion) {
        if (!condicion) {
            System.out.println(mensajeVacio);
            return;
        }
        accion.run();
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("1) Llegada de niño");
        System.out.println("2) Intentar inicio de juego");
        System.out.println("3) Presentaciones generales");
        System.out.println("4) Presentaciones por edad mínima");
        System.out.println("5) Presentaciones por inicial");
        System.out.println("6) Presentaciones primeros 5");
        System.out.println("7) Presentaciones últimos 5");
        System.out.println("8) Conteo de asistencia");
        System.out.println("9) Edad promedio Aisha");
        System.out.println("10) Intento juego rana");
        System.out.println("11) Separar para juego");
        System.out.println("12) Alarma incendios");
        System.out.println("13) Mostrar estado");
        System.out.println("0) Salir");
    }
}
