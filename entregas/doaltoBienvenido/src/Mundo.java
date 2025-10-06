import java.util.Scanner;

class Mundo {
    private Ludoteca ludoteca;
    private Scanner sc;
    private Mensaje msg;

    public Mundo() {
        ludoteca = new Ludoteca();
        sc = new Scanner(System.in);
        msg = new Mensaje();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());
            procesarOpcion(opcion);
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println("""
        1.  Simular llegada de niño
        2.  Simular intento de inicio de juego
        3.  Aisha se presenta y pide a los niños que se presenten
        4.  Aisha pide que se presenten los niños mayores de 5 años
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

        msg.mensaje("Seleccione una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> simularLlegada();
            case 2 -> intentarInicioJuego();
            case 3 -> presentacionGeneral();
            case 4 -> presentacionPorEdad();
            case 5 -> presentacionPorInicial();
            case 6 -> primerosCinco();
            case 7 -> ultimosCinco();
            case 8 -> conteoAsistencia();
            case 9 -> edadPromedio();
            case 10 -> juegoRana();
            case 11 -> separarPorEdad();
            case 12 -> alarmaIncendios();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> msg.mensajeLn("Saliendo del programa...");
            default -> msg.mensajeLn("Opción no válida.");
        }
    }

    private void simularLlegada() {
        msg.mensaje("Ingrese nombre del niño: ");
        String nombre = sc.nextLine();
        msg.mensaje("Ingrese edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        Niño n = new Niño(nombre, edad);
        msg.mensajeLn("\nLlega " + nombre + " (" + edad + " años)");
        msg.mensajeLn(nombre + " pasa a la cola de Lydia");
        ludoteca.getLydia().recibirNiño(n);
    }

    private void intentarInicioJuego() {
        Monitor lydia = ludoteca.getLydia();
        Monitor aisha = ludoteca.getAisha();
        if (lydia.getCantidad() >= 5) {
            msg.mensajeLn("\nLydia transfiere sus niños a Aisha");
            lydia.transferirNiños(aisha);
        } else {
            msg.mensajeLn("\nNo hay suficientes niños para iniciar el juego");
            msg.mensajeLn("Se necesitan al menos 5 niños");
        }
    }

    private void presentacionGeneral() {
        Monitor aisha = ludoteca.getAisha();
        if (!aisha.tieneNiños()) {
            msg.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        msg.mensajeLn("\nAisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (Niño n : aisha.getNiños()) {
            if (n != null) n.presentarse();
        }
    }

    private void presentacionPorEdad() {
        msg.mensaje("Ingrese edad mínima: ");
        int edad = Integer.parseInt(sc.nextLine());
        msg.mensajeLn("\nAisha pide que se presenten los mayores de " + edad + " años:\n");
        for (Niño n : ludoteca.getAisha().getNiños()) {
            if (n != null && n.getEdad() > edad) n.presentarse();
        }
    }

    private void presentacionPorInicial() {
        msg.mensaje("Ingrese letra inicial: ");
        char letra = sc.nextLine().toUpperCase().charAt(0);
        msg.mensajeLn("\nAisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (Niño n : ludoteca.getAisha().getNiños()) {
            if (n != null && n.getNombre().toUpperCase().charAt(0) == letra) n.presentarseNombre();
        }
    }

    private void primerosCinco() {
        msg.mensajeLn("\nAisha pide que se presenten los primeros 5 niños:\n");
        ludoteca.getAisha().mostrarPrimerosCinco();
    }

    private void ultimosCinco() {
        msg.mensajeLn("\nAisha pide que se presenten los últimos 5 niños:\n");
        ludoteca.getAisha().mostrarUltimosCinco();
    }

    private void conteoAsistencia() {
        Monitor l = ludoteca.getLydia(), a = ludoteca.getAisha(), d = ludoteca.getDalsy();
        int total = ludoteca.totalNiños();
        System.out.println("\nCONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + l.getCantidad() + " niños en cola");
        System.out.println("Aisha tiene " + a.getCantidad() + " niños en cola");
        System.out.println("Dalsy tiene " + d.getCantidad() + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }

    private void edadPromedio() {
        Monitor a = ludoteca.getAisha();
        if (!a.tieneNiños()) {
            msg.mensajeLn("No hay niños en la cola de Aisha");
            return;
        }
        double promedio = a.edadPromedio();
        msg.mensajeLn("\nEdad promedio de los niños en la cola de Aisha: " + String.format("%.1f", promedio) + " años");
    }

    private void juegoRana() {
        Monitor a = ludoteca.getAisha();
        int total = a.getCantidad();
        int mayores5 = a.contarMayoresDe(5);
        msg.mensajeLn("\nVerificando condiciones para el juego de la rana...");
        msg.mensajeLn("Total de niños: " + total);
        msg.mensajeLn("Niños de 5 años o más: " + mayores5);
        if (mayores5 > total / 2) {
            msg.mensajeLn("¡Más de la mitad cumplen la condición!");
            msg.mensajeLn("¡Pueden jugar al juego de la rana!");
        } else {
            msg.mensajeLn("No hay suficientes niños mayores de 5 años");
            msg.mensajeLn("No pueden jugar todavía");
        }
    }

    private void separarPorEdad() {
        msg.mensajeLn("\nSeparando niños para el juego de la rana...");
        Monitor a = ludoteca.getAisha();
        Monitor d = ludoteca.getDalsy();
        msg.mensajeLn("Niños menores de 5 años pasan a Dalsy:");
        for (int i = 0; i < a.getCantidad(); i++) {
            Niño n = a.getNiños()[i];
            if (n != null && n.getEdad() < 5) {
                msg.mensajeLn("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                d.recibirNiño(n);
                a.getNiños()[i] = null;
            }
        }
        msg.mensajeLn("\nNiños que se quedan con Aisha para jugar:");
        for (Niño n : a.getNiños()) {
            if (n != null && n.getEdad() >= 5) {
                msg.mensajeLn("- " + n.getNombre() + " (" + n.getEdad() + " años)");
            }
        }
    }

    private void alarmaIncendios() {
        msg.mensajeLn("\n¡ALARMA CONTRA INCENDIOS!");
        msg.mensajeLn("PROTOCOLO DE EMERGENCIA ACTIVADO\n");

        Monitor l = ludoteca.getLydia(), a = ludoteca.getAisha(), d = ludoteca.getDalsy();

        d.transferirNiños(l);
        a.transferirNiños(l);

        int total = l.getCantidad();
        msg.mensajeLn(total + " niños transferidos");
        msg.mensajeLn("Lydia ahora tiene " + total + " niños listos para evacuar en orden");
    }
}
