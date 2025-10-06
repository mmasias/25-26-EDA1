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

    private Ludoteca ludoteca;
    private Scanner scanner;
    private Mensaje mensaje;

    public Mundo() {
        ludoteca = new Ludoteca();
        scanner = new Scanner(System.in);
        mensaje = new Mensaje();
    }

    public void ejecutarSimulacion() {
        int opcionSimulacion;
        do {
            mostrarMenu();
            opcionSimulacion = scanner.nextInt();
            scanner.nextLine(); 
            procesarOpcion(opcionSimulacion);
            System.out.println();
        } while (opcionSimulacion != OPCION_SALIR);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN"           );
        System.out.println("========================================");
        System.out.println(
            "1.  Simular llegada de niño\n" +
            "2.  Simular intento de inicio de juego\n" +
            "3.  Aisha se presenta y pide a los niños que se presenten\n" +
            "4.  Aisha pide que se presenten los niños mayores de 5 años\n" +
            "5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra\n" +
            "6.  Aisha pide que se presenten los cinco primeros niños\n" +
            "7.  Aisha pide que se presenten los cinco últimos niños\n" +
            "8.  Aisha y Lydia dicen cuántos niños hay en cola\n" +
            "9.  Aisha dice la edad promedio de los niños en cola\n" +
            "10. Simular intento de inicio del juego de la rana\n" +
            "11. Paso de niños menores de 5 años a monitora Dalsy\n" +
            "12. Alarma contra incendios y protocolo de emergencia\n" +
            "13. Mostrar monitoras y niños\n" +
            "0.  Salir"
        );

        mensaje.mensaje("Seleccione una opción: ");
    }

    private void procesarOpcion(int opcionSimulacion) {
        if (opcionSimulacion == OPCION_SIMULAR_LLEGADA) {
            simularLlegada();
        } else if (opcionSimulacion == OPCION_INTENTAR_INICIO_JUEGO) {
            intentarInicioJuego();
        } else if (opcionSimulacion == OPCION_PRESENTACION_GENERAL) {
            presentacionGeneral();
        } else if (opcionSimulacion == OPCION_PRESENTACION_POR_EDAD) {
            presentacionPorEdad();
        } else if (opcionSimulacion == OPCION_PRESENTACION_POR_INICIAL) {
            presentacionPorInicial();
        } else if (opcionSimulacion == OPCION_PRIMEROS_CINCO) {
            primerosCinco();
        } else if (opcionSimulacion == OPCION_ULTIMOS_CINCO) {
            ultimosCinco();
        } else if (opcionSimulacion == OPCION_CONTEO_ASISTENCIA) {
            conteoAsistencia();
        } else if (opcionSimulacion == OPCION_EDAD_PROMEDIO) {
            edadPromedio();
        } else if (opcionSimulacion == OPCION_JUEGO_RANA) {
            juegoRana();
        } else if (opcionSimulacion == OPCION_SEPARAR_POR_EDAD) {
            separarPorEdad();
        } else if (opcionSimulacion == OPCION_ALARMA_INCENDIOS) {
            alarmaIncendios();
        } else if (opcionSimulacion == OPCION_MOSTRAR_ESTADO) {
            ludoteca.mostrarEstado();
        } else if (opcionSimulacion == OPCION_SALIR) {
            mensaje.mensajeLn("Saliendo del programa...");
        } else {
            mensaje.mensajeLn("Opción no válida.");
        }
    }

    private void simularLlegada() {
        mensaje.mensaje("Ingrese nombre del niño: ");
        String nombreNino = scanner.nextLine();
        mensaje.mensaje("Ingrese edad: ");
        int edadNino = scanner.nextInt();
        scanner.nextLine(); 
        Niño nino = new Niño(nombreNino, edadNino);
        mensaje.mensajeLn("Llega " + nombreNino + " (" + edadNino + " años)");
        mensaje.mensajeLn(nombreNino + " pasa a la cola de Lydia");
        ludoteca.getLydia().recibirNiño(nino);
    }

    private void intentarInicioJuego() {
        Monitor monitorLydia = ludoteca.getLydia();
        Monitor monitorAisha = ludoteca.getAisha();
        if (monitorLydia.getCantidad() >= MINIMO_NINIOS_PARA_INICIAR_JUEGO) {
            mensaje.mensajeLn("Lydia transfiere sus niños a Aisha");
            monitorLydia.transferirNiños(monitorAisha);
        } else {
            mensaje.mensajeLn("No hay suficientes niños para iniciar el juego");
            mensaje.mensajeLn("Se necesitan al menos " + MINIMO_NINIOS_PARA_INICIAR_JUEGO + " niños");
        }
    }

    private void presentacionGeneral() {
        Monitor monitorAisha = ludoteca.getAisha();
        if (!monitorAisha.tieneNiños()) {
            mensaje.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        mensaje.mensajeLn("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (Niño nino : monitorAisha.getNiños()) {
            if (nino != null) nino.presentarse();
        }
    }

    private void presentacionPorEdad() {
        mensaje.mensaje("Ingrese edad mínima: ");
        int edadMinima = scanner.nextInt();
        scanner.nextLine(); 
        mensaje.mensajeLn("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        for (Niño nino : ludoteca.getAisha().getNiños()) {
            if (nino != null && nino.getEdad() > edadMinima) nino.presentarse();
        }
    }

    private void presentacionPorInicial() {
        mensaje.mensaje("Ingrese letra inicial: ");
        char letraInicial = scanner.nextLine().toUpperCase().charAt(0);
        mensaje.mensajeLn("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letraInicial + "':\n");
        for (Niño nino : ludoteca.getAisha().getNiños()) {
            if (nino != null && nino.getNombre().toUpperCase().charAt(0) == letraInicial) nino.presentarseNombre();
        }
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
        Monitor monitorLydia = ludoteca.getLydia();
        Monitor monitorAisha = ludoteca.getAisha();
        Monitor monitorDalsy = ludoteca.getDalsy();
        int totalAsistentes = ludoteca.totalNiños();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + monitorLydia.getCantidad() + " niños en cola");
        System.out.println("Aisha tiene " + monitorAisha.getCantidad() + " niños en cola");
        System.out.println("Dalsy tiene " + monitorDalsy.getCantidad() + " niños en cola");
        System.out.println("Total: " + totalAsistentes + " niños");
    }

    private void edadPromedio() {
        Monitor monitorAisha = ludoteca.getAisha();
        if (!monitorAisha.tieneNiños()) {
            mensaje.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        double promedioEdad = monitorAisha.edadPromedio();
        mensaje.mensajeLn("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", promedioEdad) + " años");
    }

    private void juegoRana() {
        Monitor monitorAisha = ludoteca.getAisha();
        int totalNinosAisha = monitorAisha.getCantidad();
        int cantidadMayoresOIgualEdadMinima = monitorAisha.contarMayoresDe(EDAD_MINIMA_JUEGO_RANA);
        mensaje.mensajeLn("Verificando condiciones para el juego de la rana...");
        mensaje.mensajeLn("Total de niños: " + totalNinosAisha);
        mensaje.mensajeLn("Niños de " + EDAD_MINIMA_JUEGO_RANA + " años o más: " + cantidadMayoresOIgualEdadMinima);
        if (cantidadMayoresOIgualEdadMinima > totalNinosAisha / DIVISOR_MAYORIA_SIMPLE) {
            mensaje.mensajeLn("¡Más de la mitad cumplen la condición!");
            mensaje.mensajeLn("¡Pueden jugar al juego de la rana!");
        } else {
            mensaje.mensajeLn("No hay suficientes niños mayores de " + EDAD_MINIMA_JUEGO_RANA + " años");
            mensaje.mensajeLn("No pueden jugar todavía");
        }
    }

    private void separarPorEdad() {
        mensaje.mensajeLn("Separando niños para el juego de la rana...");
        Monitor monitorAisha = ludoteca.getAisha();
        Monitor monitorDalsy = ludoteca.getDalsy();
        mensaje.mensajeLn("Niños menores de " + EDAD_MINIMA_JUEGO_RANA + " años pasan a Dalsy:");
        int indice = 0;
        while (indice < monitorAisha.getCantidad()) {
            Niño nino = monitorAisha.getNiñoEn(indice);
            if (nino != null && nino.getEdad() < EDAD_MINIMA_JUEGO_RANA) {
                mensaje.mensajeLn("- " + nino.getNombre() + " (" + nino.getEdad() + " años)");
                Niño ninoMovido = monitorAisha.removerEn(indice);
                monitorDalsy.recibirNiño(ninoMovido);
            } else {
                indice++;
            }
        }
        mensaje.mensajeLn("Niños que se quedan con Aisha para jugar:");
        for (int indiceRestantes = 0; indiceRestantes < monitorAisha.getCantidad(); indiceRestantes++) {
            Niño ninoRestante = monitorAisha.getNiñoEn(indiceRestantes);
            if (ninoRestante != null && ninoRestante.getEdad() >= EDAD_MINIMA_JUEGO_RANA) {
                mensaje.mensajeLn("- " + ninoRestante.getNombre() + " (" + ninoRestante.getEdad() + " años)");
            }
        }
    }

    private void alarmaIncendios() {
        mensaje.mensajeLn("¡ALARMA CONTRA INCENDIOS!");
        mensaje.mensajeLn("PROTOCOLO DE EMERGENCIA ACTIVADO\n");

        Monitor monitorLydia = ludoteca.getLydia();
        Monitor monitorAisha = ludoteca.getAisha();
        Monitor monitorDalsy = ludoteca.getDalsy();

        monitorDalsy.transferirNiños(monitorLydia);
        monitorAisha.transferirNiños(monitorLydia);

        int totalTransferidos = monitorLydia.getCantidad();
        mensaje.mensajeLn(totalTransferidos + " niños transferidos");
        mensaje.mensajeLn("Lydia ahora tiene " + totalTransferidos + " niños listos para evacuar en orden");
    }
}
