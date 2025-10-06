import java.util.Scanner;

public class Mundo {
    private Lydia lydia;
    private Aisha aisha;
    private Dalsy dalsy;
    private Scanner scanner;
    private ColaNinos colaOriginal;

    public Mundo() {
        lydia = new Lydia();
        aisha = new Aisha();
        dalsy = new Dalsy();
        scanner = new Scanner(System.in);
        colaOriginal = new ColaNinos();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.print("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 0);
        System.out.println("¡Hasta luego!");
    }

    private void mostrarMenu() {
        System.out.println("\n========================================");
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
                simularLlegadaNino();
                break;
            case 2:
                simularInicioJuego();
                break;
            case 3:
                aisha.pedirPresentaciones();
                break;
            case 4:
                System.out.print("Ingrese la edad mínima: ");
                int edad = scanner.nextInt();
                scanner.nextLine();
                aisha.pedirPresentacionesMayoresDe(edad);
                break;
            case 5:
                System.out.print("Ingrese la letra inicial: ");
                char letra = scanner.nextLine().charAt(0);
                aisha.pedirPresentacionesPorLetra(letra);
                break;
            case 6:
                aisha.pedirPrimerosCinco();
                break;
            case 7:
                aisha.pedirUltimosCinco();
                break;
            case 8:
                mostrarConteoAsistencia();
                break;
            case 9:
                mostrarEdadPromedio();
                break;
            case 10:
                simularJuegoRana();
                break;
            case 11:
                separarNinosParaJuego();
                break;
            case 12:
                activarAlarmaIncendios();
                break;
            case 13:
                mostrarEstadoActual();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void simularLlegadaNino() {
        System.out.print("Ingrese el nombre del niño: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del niño: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        Nino nuevoNino = new Nino(nombre, edad);
        System.out.println("\nLlega " + nombre + " (" + edad + " años)");
        lydia.recibirNino(nuevoNino);
    }

    private void simularInicioJuego() {
        if (lydia.getCola().getTamaño() >= 5) {
            lydia.transferirAAisha(aisha);
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    private void mostrarConteoAsistencia() {
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.getCola().getTamaño() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.getCola().getTamaño() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.getCola().getTamaño() + " niños en cola");
        int total = lydia.getCola().getTamaño() + aisha.getCola().getTamaño() + dalsy.getCola().getTamaño();
        System.out.println("Total: " + total + " niños");
    }

    private void mostrarEdadPromedio() {
        if (aisha.getCola().estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            double promedio = aisha.calcularEdadPromedio();
            System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años\n", promedio);
        }
    }

    private void simularJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.getCola().getTamaño();
        int mayores = aisha.contarMayoresDe5();
        
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores);
        
        if (aisha.verificarJuegoRana()) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    private void separarNinosParaJuego() {
        colaOriginal.limpiar();
        for (int i = 0; i < aisha.getCola().getTamaño(); i++) {
            colaOriginal.encolar(aisha.getCola().getNino(i));
        }

        System.out.println("Separando niños para el juego de la rana...");
        
        ColaNinos menores = new ColaNinos();
        ColaNinos mayores = new ColaNinos();
        
        while (!aisha.getCola().estaVacia()) {
            Nino nino = aisha.getCola().desencolar();
            if (nino.getEdad() < 5) {
                menores.encolar(nino);
            } else {
                mayores.encolar(nino);
            }
        }
        
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (int i = 0; i < menores.getTamaño(); i++) {
            Nino nino = menores.getNino(i);
            System.out.println("- " + nino.getNombre() + " (" + nino.getEdad() + " años)");
            dalsy.recibirNino(nino);
        }
        
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (int i = 0; i < mayores.getTamaño(); i++) {
            Nino nino = mayores.getNino(i);
            System.out.println("- " + nino.getNombre() + " (" + nino.getEdad() + " años)");
            aisha.meterEnCola(nino);
        }
        
        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    private void activarAlarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println("\nPROTOCOLO DE EMERGENCIA ACTIVADO\n");
        
        int totalTransferidos = 0;
        
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        int deDalsy = dalsy.getCola().getTamaño();
        dalsy.transferirALydia(lydia);
        totalTransferidos += deDalsy;
        
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        int deAisha = aisha.getCola().getTamaño();
        while (!aisha.getCola().estaVacia()) {
            Nino nino = aisha.getCola().desencolar();
            lydia.recibirNino(nino);
        }
        totalTransferidos += deAisha;
        
        System.out.println("\n" + totalTransferidos + " niños transferidos");
        System.out.println("Lydia ahora tiene " + lydia.getCola().getTamaño() + " niños listos para evacuar en orden");
    }

    private void mostrarEstadoActual() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");

        System.out.println("LYDIA:");
        if (lydia.getCola().estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + lydia.getCola().getTamaño());
            for (int i = 0; i < lydia.getCola().getTamaño(); i++) {
                System.out.println("  - " + lydia.getCola().getNino(i));
            }
        }

        System.out.println("\nAISHA:");
        if (aisha.getCola().estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + aisha.getCola().getTamaño());
            for (int i = 0; i < aisha.getCola().getTamaño(); i++) {
                System.out.println("  - " + aisha.getCola().getNino(i));
            }
        }

        System.out.println("\nDALSY:");
        if (dalsy.getCola().estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + dalsy.getCola().getTamaño());
            for (int i = 0; i < dalsy.getCola().getTamaño(); i++) {
                System.out.println("  - " + dalsy.getCola().getNino(i));
            }
        }

        System.out.println("\n========================================");
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}