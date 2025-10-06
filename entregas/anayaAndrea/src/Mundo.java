import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca;
    private Scanner scanner;

    public Mundo() {
        ludoteca = new Ludoteca();
        scanner = new Scanner(System.in);
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
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
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> llegadaNino();
            case 2 -> intentoInicioJuego();
            case 3 -> ludoteca.getAisha().presentarse();
            case 4 -> mayoresDe();
            case 5 -> porLetra();
            case 6 -> ludoteca.getAisha().presentarPrimerosCinco();
            case 7 -> ludoteca.getAisha().presentarUltimosCinco();
            case 8 -> conteo();
            case 9 -> promedio();
            case 10 -> intentoJuegoRana();
            case 11 -> separacion();
            case 12 -> emergencia();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private void llegadaNino() {
        String nombre = leerString("Nombre del niño: ");
        int edad = leerInt("Edad del niño: ");
        ludoteca.getLydia().recibirNino(new Nino(nombre, edad));
    }

    private void intentoInicioJuego() {
        Lydia l = ludoteca.getLydia();
        Aisha a = ludoteca.getAisha();
        if (l.getCantidad() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha:");
            Nino[] transferidos = l.pasarNinos();
            a.recibirNinos(transferidos, transferidos.length);
            for (Nino n : transferidos)
                System.out.println(" - " + n.getNombre());
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    private void mayoresDe() {
        int edad = leerInt("Ingrese edad mínima: ");
        ludoteca.getAisha().presentarMayoresDe(edad);
    }

    private void porLetra() {
        System.out.print("Ingrese letra inicial: ");
        char letra = scanner.next().charAt(0);
        scanner.nextLine();
        ludoteca.getAisha().presentarPorLetra(letra);
    }

    private void conteo() {
        int x = ludoteca.getLydia().getCantidad();
        int y = ludoteca.getAisha().getCantidad();
        int z = ludoteca.getDalsy().getCantidad();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + x + " niños en cola");
        System.out.println("Aisha tiene " + y + " niños en cola");
        System.out.println("Dalsy tiene " + z + " niños en cola");
        System.out.println("Total: " + (x + y + z) + " niños");
    }

    private void promedio() {
        Aisha a = ludoteca.getAisha();
        if (a.getCantidad() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", a.edadPromedio());
    }

    private void intentoJuegoRana() {
        Aisha a = ludoteca.getAisha();
        int total = a.getCantidad();
        int mayores = 0;
        for (Nino n : a.getFila()) if (n.getEdad() >= 5) mayores++;
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores);
        if (mayores > total / 2)
            System.out.println("¡Más de la mitad cumplen la condición!\n¡Pueden jugar al juego de la rana!");
        else
            System.out.println("No hay suficientes niños mayores de 5 años\nNo pueden jugar todavía");
    }

    private void separacion() {
        System.out.println("Separando niños para el juego de la rana...");
        Aisha a = ludoteca.getAisha();
        Dalsy d = ludoteca.getDalsy();

        Nino[] todos = a.getFila();
        a.vaciarFila();
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Nino n : todos) {
            if (n.getEdad() < 5) {
                d.recibirNino(n);
                System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
            } else {
                a.recibirNinos(new Nino[]{n}, 1);
            }
        }
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (Nino n : a.getFila()) {
            System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
    }

    private void emergencia() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        Lydia l = ludoteca.getLydia();
        Aisha a = ludoteca.getAisha();
        Dalsy d = ludoteca.getDalsy();

        int totalTransferidos = a.getCantidad() + d.getCantidad();

        for (Nino n : a.getFila()) l.recibirNino(n);
        for (Nino n : d.getFila()) l.recibirNino(n);
        a.vaciarFila();
        d.pasarNinos();

        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println("Lydia ahora tiene " + l.getCantidad() + " niños listos para evacuar en orden");
    }

    private int leerInt(String msg) {
        System.out.print(msg);
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private String leerString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
