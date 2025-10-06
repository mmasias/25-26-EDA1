package reto002;

import java.util.LinkedList;

class Mundo {
    private Ludoteca ludoteca = new Ludoteca();
    private Console console = new Console();
    
    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione opción: ");
            procesarOpcion(opcion);
            console.readString("Presione ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
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
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println("0.  Salir");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                String nombre = console.readString("Nombre del niño: ");
                int edad = console.readInt("Edad: ");
                Ninio n = new Ninio(nombre, edad);
                ludoteca.getLydia().agregarNinio(n);
                System.out.println("Llega " + n);
                System.out.println(n.getNombre() + " pasa a la cola de Lydia");
                break;

            case 2:
                if (ludoteca.getLydia().cantidad() >= 5) {
                    System.out.println("Lydia transfiere sus niños a Aisha");
                    ludoteca.getLydia().transferirTodosA(ludoteca.getAisha());
                    for (Ninio ni : ludoteca.getAisha().getCola()) {
                        System.out.println("- " + ni);
                    }
                } else {
                    System.out.println("No hay suficientes niños para iniciar el juego");
                    System.out.println("Se necesitan al menos 5 niños");
                }
                break;

            case 3:
                System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                } else {
                    for (Ninio ni : ludoteca.getAisha().getCola()) {
                        System.out.println("[" + ni.getNombre() + "]: " + ni.presentacionCompleta());
                    }
                }
                break;

            case 4:
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                    break;
                }
                int edadMin = console.readInt("Edad mínima: ");
                System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:");
                boolean alguno4 = false;
                for (Ninio ni : ludoteca.getAisha().getCola()) {
                    if (ni.getEdad() > edadMin) {
                        System.out.println("[" + ni.getNombre() + "]: " + ni.presentacionCompleta());
                        alguno4 = true;
                    }
                }
                if (!alguno4) System.out.println("No hay niños que cumplan la condición.");
                break;

            case 5:
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                    break;
                }
                String letra = console.readString("Letra inicial: ").toLowerCase();
                if (letra.isEmpty()) letra = " ";
                char c = letra.charAt(0);
                System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + c + "':");
                boolean alguno5 = false;
                for (Ninio ni : ludoteca.getAisha().getCola()) {
                    if (ni.getNombre().toLowerCase().charAt(0) == c) {
                        System.out.println("[" + ni.getNombre() + "]: " + ni.presentacionNombre());
                        alguno5 = true;
                    }
                }
                if (!alguno5) System.out.println("No hay niños que cumplan la condición.");
                break;

            case 6:
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                    break;
                }
                System.out.println("Aisha pide que se presenten los primeros 5 niños:");
                int max6 = Math.min(5, ludoteca.getAisha().cantidad());
                for (int i = 0; i < max6; i++) {
                    Ninio ni = ludoteca.getAisha().getCola().get(i);
                    System.out.println("[" + (i+1) + "]: " + ni.presentacionNombre());
                }
                break;

            case 7:
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                    break;
                }
                System.out.println("Aisha pide que se presenten los últimos 5 niños:");
                int size7 = ludoteca.getAisha().cantidad();
                int start7 = Math.max(0, size7 - 5);
                int idx7 = 1;
                for (int i = start7; i < size7; i++) {
                    Ninio ni = ludoteca.getAisha().getCola().get(i);
                    System.out.println("[" + idx7 + "]: " + ni.presentacionNombre());
                    idx7++;
                }
                break;

            case 8:
                int cantL = ludoteca.getLydia().cantidad();
                int cantA = ludoteca.getAisha().cantidad();
                int cantD = ludoteca.getDalsy().cantidad();
                System.out.println("CONTEO DE ASISTENCIA:");
                System.out.println("Lydia tiene " + cantL + " niños en cola");
                System.out.println("Aisha tiene " + cantA + " niños en cola");
                System.out.println("Dalsy tiene " + cantD + " niños en cola");
                System.out.println("Total: " + (cantL+cantA+cantD) + " niños");
                break;

            case 9:
                if (ludoteca.getAisha().estaVacia()) {
                    System.out.println("No hay niños en la cola de Aisha");
                    break;
                }
                double prom = ludoteca.getAisha().edadPromedio();
                System.out.println("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", prom) + " años");
                break;

            case 10:
                int total10 = ludoteca.getAisha().cantidad();
                long mayores5 = 0;
                for (Ninio ni : ludoteca.getAisha().getCola()) {
                    if (ni.getEdad() >= 5) mayores5++;
                }
                System.out.println("Verificando condiciones para el juego de la rana...");
                System.out.println("Total de niños: " + total10);
                System.out.println("Niños de 5 años o más: " + mayores5);
                if (mayores5 > total10 / 2.0) {
                    System.out.println("¡Más de la mitad cumplen la condición!");
                    System.out.println("¡Pueden jugar al juego de la rana!");
                } else {
                    System.out.println("No hay suficientes niños mayores de 5 años");
                    System.out.println("No pueden jugar todavía");
                }
                break;

            case 11:
                System.out.println("Separando niños para el juego de la rana...");
                LinkedList<Ninio> menores = new LinkedList<>();
                LinkedList<Ninio> mayores = new LinkedList<>();
                for (Ninio ni : ludoteca.getAisha().getCola()) {
                    if (ni.getEdad() < 5) menores.add(ni);
                    else mayores.add(ni);
                }
                ludoteca.getAisha().getCola().clear();
                ludoteca.getAisha().getCola().addAll(mayores);
                ludoteca.getDalsy().getCola().addAll(menores);
                System.out.println("Niños menores de 5 años pasan a Dalsy:");
                if (menores.isEmpty()) System.out.println("  (ninguno)");
                else for (Ninio ni : menores) System.out.println("- " + ni);
                System.out.println("Niños que se quedan con Aisha para jugar:");
                if (mayores.isEmpty()) System.out.println("  (ninguno)");
                else for (Ninio ni : mayores) System.out.println("- " + ni);
                System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
                break;

            case 12:
                System.out.println("¡ALARMA CONTRA INCENDIOS!");
                System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
                int transfD = ludoteca.getDalsy().cantidad();
                int transfA = ludoteca.getAisha().cantidad();
                System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                ludoteca.getDalsy().transferirTodosA(ludoteca.getLydia());
                ludoteca.getAisha().transferirTodosA(ludoteca.getLydia());
                System.out.println((transfD+transfA) + " niños transferidos");
                System.out.println("Lydia ahora tiene " + ludoteca.getLydia().cantidad() + " niños listos para evacuar en orden");
                break;

            case 13:
                ludoteca.mostrarEstado();
                break;

            case 0:
                System.out.println("Saliendo...");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}
