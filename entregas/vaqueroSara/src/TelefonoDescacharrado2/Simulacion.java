package TelefonoDescacharrado2;

import java.util.Scanner;

public class Simulacion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Monitor lydia = new Monitor("Lydia");
        Monitor aisha = new Monitor("Aisha");
        Monitor dalsy = new Monitor("Dalsy");

        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del niño: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad del niño: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    Niño nuevo = new Niño(nombre, edad);
                    System.out.println("Llega " + nombre + " (" + edad + " años)");
                    System.out.println(nombre + " pasa a la cola de Lydia");
                    lydia.agregarNiño(nuevo);
                }

                case 2 -> {
                    if (lydia.contarNiños() >= 5) {
                        System.out.println("Lydia transfiere sus niños a Aisha");
                        while (!lydia.estaVacia()) {
                            Niño transferido = lydia.sacarNiño();
                            System.out.println("- " + transferido.getNombre() + " (" + transferido.getEdad() + " años)");
                            aisha.agregarNiño(transferido);
                        }
                    } else {
                        System.out.println("No hay suficientes niños para iniciar el juego");
                        System.out.println("Se necesitan al menos 5 niños");
                    }
                }

                case 3 -> aisha.presentarTodos();
                case 4 -> {
                    System.out.print("Edad mínima: ");
                    int edadMinima = sc.nextInt();
                    aisha.presentarMayoresDe(edadMinima);
                }
                case 5 -> {
                    System.out.print("Letra inicial: ");
                    char letra = sc.next().charAt(0);
                    aisha.presentarPorInicial(letra);
                }
                case 6 -> aisha.presentarPrimeros(5);
                case 7 -> aisha.presentarUltimos(5);
                case 8 -> {
                    System.out.println("CONTEO DE ASISTENCIA:");
                    int x = lydia.contarNiños();
                    int y = aisha.contarNiños();
                    int z = dalsy.contarNiños();
                    System.out.println("Lydia tiene " + x + " niños en cola");
                    System.out.println("Aisha tiene " + y + " niños en cola");
                    System.out.println("Dalsy tiene " + z + " niños en cola");
                    System.out.println("Total: " + (x + y + z) + " niños");
                }

                case 9 -> aisha.mostrarPromedioEdad();

                 case 10 ->  {

                    System.out.println("Verificando condiciones para el juego de la rana...");
                    int total = aisha.contarNiños();
                    int mayoresOIgual5 = aisha.contarMayoresIgual(5);
                    System.out.println("Total de niños: " + total);
                    System.out.println("Niños de 5 años o más: " + mayoresOIgual5);
                    if (total > 0 && mayoresOIgual5 > total / 2) {
                        System.out.println("¡Más de la mitad cumplen la condición!");
                        System.out.println("¡Pueden jugar al juego de la rana!");
                    } else {
                        System.out.println("No hay suficientes niños mayores de 5 años");
                        System.out.println("No pueden jugar todavía");
                    }
                }

                case 11 -> aisha.simularSeparacionMenores(5, dalsy);

                case 12 ->  {
                    System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
                    System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
                    System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                    int t1 = dalsy.transferirTodosAMonitor(lydia);
                    System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                    int t2 = aisha.transferirTodosAMonitor(lydia);
                    int totalTransferidos = t1 + t2;
                    System.out.println(totalTransferidos + " niños transferidos\n");
                    System.out.println("Lydia ahora tiene " + lydia.contarNiños() + " niños listos para evacuar en orden");
                }

                case 13 -> {
                    System.out.println("========================================");
                    System.out.println("        ESTADO ACTUAL");
                    System.out.println("========================================");
                    lydia.mostrarEstado();
                    aisha.mostrarEstado();
                    dalsy.mostrarEstado();
                }

                case 0 -> System.out.println("Saliendo de la simulación...");
                default -> System.out.println("Opción no válida.");
            }

            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
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
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println("0.  Salir");
        System.out.print("Seleccione una opción: ");
    }
}
