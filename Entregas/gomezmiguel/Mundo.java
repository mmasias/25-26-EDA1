import java.util.Scanner;

class Mundo {
    private static final int OPCION_SIMULAR_LLEGADA = 1;
    private static final int OPCION_INTENTAR_INICIO_JUEGO = 2;
    private static final int OPCION_PRESENTACION_GENERAL = 3;
    private static final int OPCION_PRESENTACION_POR_EDAD = 4;
    private static final int OPCION_PRESENTACION_POR_INICIAL = 5;
    private static final int OPCION_PRIMEROS_CINCO = 6;
    private static final int OPCION_ULTIMOS_CINCO = 7;
    private static final int OPCION_CONTEO_ASISTENCIA = 8;
    private static final int OPCION_EDAD_PROMEDIO = 9;
    private static final int OPCION_JUEGO_RANA = 10;
    private static final int OPCION_SEPARAR_POR_EDAD = 11;
    private static final int OPCION_ALARMA_INCENDIOS = 12;
    private static final int OPCION_MOSTRAR_ESTADO = 13;
    private static final int OPCION_SALIR = 0;

    private static final int MINIMO_NINIOS_PARA_INICIAR_JUEGO = 5;
    private static final int EDAD_MINIMA_JUEGO_RANA = 5;
    private static final int DIVISOR_MAYORIA_SIMPLE = 2;
    private static final String SEPARADOR_VISUAL = "----------------------------------------";

    private final Ludoteca ludoteca = new Ludoteca();
    private final Scanner scanner = new Scanner(System.in);
    private final Mensaje mensaje = new Mensaje();

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            procesarOpcion(opcion);
            System.out.println("\n" + SEPARADOR_VISUAL + "\n");
        } while (opcion != OPCION_SALIR);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println(
                "1.  Simular llegada de niño\n" +
                "2.  Simular intento de inicio de juego\n" +
                "3.  Presentación general\n" +
                "4.  Presentación por edad\n" +
                "5.  Presentación por inicial\n" +
                "6.  Primeros cinco\n" +
                "7.  Últimos cinco\n" +
                "8.  Conteo asistencia\n" +
                "9.  Edad promedio\n" +
                "10. Juego de la rana\n" +
                "11. Separar por edad\n" +
                "12. Alarma incendios\n" +
                "13. Mostrar estado\n" +
                "0.  Salir"
        );
        mensaje.mensaje("Seleccione una opción: ");
    }

    private void procesarOpcion(int opcion) {
        if (opcion == OPCION_SIMULAR_LLEGADA) simularLlegada();
        else if (opcion == OPCION_INTENTAR_INICIO_JUEGO) intentarInicioJuego();
        else if (opcion == OPCION_PRESENTACION_GENERAL) presentacionGeneral();
        else if (opcion == OPCION_PRESENTACION_POR_EDAD) presentacionPorEdad();
        else if (opcion == OPCION_PRESENTACION_POR_INICIAL) presentacionPorInicial();
        else if (opcion == OPCION_PRIMEROS_CINCO) primerosCinco();
        else if (opcion == OPCION_ULTIMOS_CINCO) ultimosCinco();
        else if (opcion == OPCION_CONTEO_ASISTENCIA) conteoAsistencia();
        else if (opcion == OPCION_EDAD_PROMEDIO) edadPromedio();
        else if (opcion == OPCION_JUEGO_RANA) juegoRana();
        else if (opcion == OPCION_SEPARAR_POR_EDAD) separarPorEdad();
        else if (opcion == OPCION_ALARMA_INCENDIOS) alarmaIncendios();
        else if (opcion == OPCION_MOSTRAR_ESTADO) ludoteca.mostrarEstado();
        else if (opcion == OPCION_SALIR) mensaje.mensajeLn("Saliendo del programa...");
        else mensaje.mensajeLn("Opción no válida.");
    }

    private void simularLlegada() {
        mensaje.mensaje("Ingrese nombre del niño: ");
        String nombre = scanner.nextLine();
        mensaje.mensaje("Ingrese edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        Niño nino = new Niño(nombre, edad);
        mensaje.mensajeLn("Llega " + nombre + " (" + edad + " años)");
        mensaje.mensajeLn(nombre + " pasa a la cola de Lydia");
        ludoteca.getLydia().recibirNiño(nino);
    }

    private void intentarInicioJuego() {
        Monitor lydia = ludoteca.getLydia();
        Monitor aisha = ludoteca.getAisha();
        if (lydia.getCantidad() >= MINIMO_NINIOS_PARA_INICIAR_JUEGO) {
            mensaje.mensajeLn("Lydia transfiere sus niños a Aisha");
            lydia.transferirNiños(aisha);
        } else {
            mensaje.mensajeLn("No hay suficientes niños para iniciar el juego");
            mensaje.mensajeLn("Se necesitan al menos " + MINIMO_NINIOS_PARA_INICIAR_JUEGO + " niños");
        }
    }

    private void presentacionGeneral() {
        Monitor aisha = ludoteca.getAisha();
        if (!aisha.tieneNiños()) {
            mensaje.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        mensaje.mensajeLn("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (Niño nino : aisha.getNiños())
            if (nino != null) nino.presentarse();
    }

    private void presentacionPorEdad() {
        mensaje.mensaje("Ingrese edad mínima: ");
        int edadMinima = scanner.nextInt();
        scanner.nextLine();
        mensaje.mensajeLn("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        for (Niño nino : ludoteca.getAisha().getNiños())
            if (nino != null && nino.getEdad() > edadMinima) nino.presentarse();
    }

    private void presentacionPorInicial() {
        mensaje.mensaje("Ingrese letra inicial: ");
        char letra = scanner.nextLine().toUpperCase().charAt(0);
        mensaje.mensajeLn("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (Niño nino : ludoteca.getAisha().getNiños())
            if (nino != null && nino.getNombre().toUpperCase().charAt(0) == letra)
                nino.presentarseNombre();
    }

    private void primerosCinco() {
        mensaje.mensajeLn("Aisha pide que se presenten los primeros 5 niños:");
        ludoteca.getAisha().mostrarPrimerosCinco();
    }

    private void ultimosCinco() {
        mensaje.mensajeLn("Aisha pide que se presenten los últimos 5 niños:");
        ludoteca.getAisha().mostrarUltimosCinco();
    }

    private void conteoAsistencia() {
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + ludoteca.getLydia().getCantidad() + " niños en cola");
        System.out.println("Aisha tiene " + ludoteca.getAisha().getCantidad() + " niños en cola");
        System.out.println("Dalsy tiene " + ludoteca.getDalsy().getCantidad() + " niños en cola");
        System.out.println("Total: " + ludoteca.totalNiños() + " niños");
    }

    private void edadPromedio() {
        Monitor aisha = ludoteca.getAisha();
        if (!aisha.tieneNiños()) {
            mensaje.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        mensaje.mensajeLn("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", aisha.edadPromedio()) + " años");
    }

    private void juegoRana() {
        Monitor aisha = ludoteca.getAisha();
        int total = aisha.getCantidad();
        int mayores = aisha.contarMayoresDe(EDAD_MINIMA_JUEGO_RANA);
        mensaje.mensajeLn("Verificando condiciones para el juego de la rana...");
        mensaje.mensajeLn("Total de niños: " + total);
        mensaje.mensajeLn("Niños de " + EDAD_MINIMA_JUEGO_RANA + " años o más: " + mayores);
        if (mayores > total / DIVISOR_MAYORIA_SIMPLE) {
            mensaje.mensajeLn("¡Más de la mitad cumplen la condición!");
            mensaje.mensajeLn("¡Pueden jugar al juego de la rana!");
        } else {
            mensaje.mensajeLn("No hay suficientes niños mayores de " + EDAD_MINIMA_JUEGO_RANA + " años");
            mensaje.mensajeLn("No pueden jugar todavía");
        }
    }

    private void separarPorEdad() {
        mensaje.mensajeLn("Separando niños para el juego de la rana...");
        Monitor aisha = ludoteca.getAisha();
        Monitor dalsy = ludoteca.getDalsy();
        mensaje.mensajeLn("Niños menores de " + EDAD_MINIMA_JUEGO_RANA + " años pasan a Dalsy:");
        int i = 0;
        while (i < aisha.getCantidad()) {
            Niño n = aisha.getNiñoEn(i);
            if (n != null && n.getEdad() < EDAD_MINIMA_JUEGO_RANA) {
                mensaje.mensajeLn("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                dalsy.recibirNiño(aisha.removerEn(i));
            } else i++;
        }
        mensaje.mensajeLn("Niños que se quedan con Aisha para jugar:");
        for (int j = 0; j < aisha.getCantidad(); j++) {
            Niño n = aisha.getNiñoEn(j);
            if (n != null && n.getEdad() >= EDAD_MINIMA_JUEGO_RANA)
                mensaje.mensajeLn("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
    }

    private void alarmaIncendios() {
        mensaje.mensajeLn("¡ALARMA CONTRA INCENDIOS!");
        mensaje.mensajeLn("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        ludoteca.getDalsy().transferirNiños(ludoteca.getLydia());
        ludoteca.getAisha().transferirNiños(ludoteca.getLydia());
        mensaje.mensajeLn(ludoteca.getLydia().getCantidad() + " niños transferidos");
        mensaje.mensajeLn("Lydia ahora tiene " + ludoteca.getLydia().getCantidad() + " niños listos para evacuar en orden");
    }
}
