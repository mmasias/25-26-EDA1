import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Mundo {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;
    private Console console;

    public Mundo() {
        console = new Console();
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione una opción: ");
            procesarOpcion(opcion);
            if (opcion != 0) {
                console.readString("Presione ENTER para continuar...");
            }
        } while (opcion != 0);
        console.writeln("¡Gracias por usar la simulación de la ludoteca!");
    }

    private void mostrarMenu() {
        console.writeln("========================================\n        LUDOTECA - SIMULACIÓN\n========================================\n");
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
        console.writeln("0.  Salir\n");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                String nombre = console.readString("Ingrese nombre del niño: ");
                int edad = console.readInt("Ingrese edad del niño: ");
                Niño niño = new Niño(nombre, edad);
                lydia.recibeNiño(niño);
                console.writeln("Llega " + nombre + " (" + edad + " años)");
                console.writeln(nombre + " pasa a la cola de Lydia");
                break;
            case 2:
                if (lydia.size() < 5) {
                    console.writeln("No hay suficientes niños para iniciar el juego");
                    console.writeln("Se necesitan al menos 5 niños");
                } else {
                    console.writeln("Lydia transfiere sus niños a Aisha");
                    List<Niño> trans = lydia.entregaNiños(aisha);
                    console.writeln("Lista de niños transferidos:");
                    for (Niño n : trans) {
                        console.writeln("- " + n.getNombre());
                    }
                }
                break;
            case 3:
                console.writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha para presentarse.");
                } else {
                    for (int i = 0; i < aisha.size(); i++) {
                        Niño n = aisha.getNiño(i);
                        console.writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
                    }
                }
                break;
            case 4:
                int minEdad = console.readInt("Edad mínima: ");
                console.writeln("Aisha pide que se presenten los mayores de " + minEdad + " años:");
                boolean presented4 = false;
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha.");
                } else {
                    for (int i = 0; i < aisha.size(); i++) {
                        Niño n = aisha.getNiño(i);
                        if (n.getEdad() > minEdad) {
                            console.writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
                            presented4 = true;
                        }
                    }
                    if (!presented4) {
                        console.writeln("No hay niños que cumplan la condición.");
                    }
                }
                break;
            case 5:
                String letStr = console.readString("Letra inicial: ");
                if (letStr.length() == 0) break;
                char letra = Character.toLowerCase(letStr.charAt(0));
                console.writeln("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letStr.charAt(0) + "':");
                boolean presented5 = false;
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha.");
                } else {
                    for (int i = 0; i < aisha.size(); i++) {
                        Niño n = aisha.getNiño(i);
                        if (n.getNombre().toLowerCase().startsWith(String.valueOf(letra))) {
                            console.writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + "'");
                            presented5 = true;
                        }
                    }
                    if (!presented5) {
                        console.writeln("No hay niños que cumplan la condición.");
                    }
                }
                break;
            case 6:
                console.writeln("Aisha pide que se presenten los primeros 5 niños:");
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha.");
                } else {
                    int numFirst = Math.min(5, aisha.size());
                    for (int i = 0; i < numFirst; i++) {
                        Niño n = aisha.getNiño(i);
                        console.writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + "'");
                    }
                    if (aisha.size() < 5) {
                        console.writeln("Solo hay " + aisha.size() + " niños en la cola.");
                    }
                }
                break;
            case 7:
                console.writeln("Aisha pide que se presenten los últimos 5 niños:");
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha.");
                } else {
                    int numLast = Math.min(5, aisha.size());
                    int startLast = aisha.size() - numLast;
                    for (int i = startLast; i < aisha.size(); i++) {
                        Niño n = aisha.getNiño(i);
                        console.writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + "'");
                    }
                    if (aisha.size() < 5) {
                        console.writeln("Solo hay " + aisha.size() + " niños en la cola.");
                    }
                }
                break;
            case 8:
                console.writeln("CONTEO DE ASISTENCIA:");
                console.writeln("Lydia tiene " + lydia.size() + " niños en cola");
                console.writeln("Aisha tiene " + aisha.size() + " niños en cola");
                console.writeln("Dalsy tiene " + dalsy.size() + " niños en cola");
                console.writeln("Total: " + (lydia.size() + aisha.size() + dalsy.size()) + " niños");
                break;
            case 9:
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha");
                } else {
                    int suma = 0;
                    for (int i = 0; i < aisha.size(); i++) {
                        suma += aisha.getNiño(i).getEdad();
                    }
                    double prom = (double) suma / aisha.size();
                    console.writeln("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", prom) + " años");
                }
                break;
            case 10:
                if (aisha.size() == 0) {
                    console.writeln("Verificando condiciones para el juego de la rana...");
                    console.writeln("Total de niños: 0");
                    console.writeln("No hay niños para jugar.");
                    break;
                }
                console.writeln("Verificando condiciones para el juego de la rana...");
                int total = aisha.size();
                int mayores5 = 0;
                for (int i = 0; i < total; i++) {
                    if (aisha.getNiño(i).getEdad() >= 5) {
                        mayores5++;
                    }
                }
                console.writeln("Total de niños: " + total);
                console.writeln("Niños de 5 años o más: " + mayores5);
                if ((double) mayores5 > total / 2.0) {
                    console.writeln("¡Más de la mitad cumplen la condición!");
                    console.writeln("¡Pueden jugar al juego de la rana!");
                } else {
                    console.writeln("No hay suficientes niños mayores de 5 años");
                    console.writeln("No pueden jugar todavía");
                }
                break;
            case 11:
                if (aisha.size() == 0) {
                    console.writeln("No hay niños en la cola de Aisha para separar.");
                    break;
                }
                console.writeln("Separando niños para el juego de la rana...");
                List<Niño> originalDalsy = new ArrayList<>(dalsy.getAllNiños());
                List<Niño> todosAisha = new ArrayList<>(aisha.getAllNiños());
                aisha.vaciarCola();
                List<Niño> menores = new ArrayList<>();
                List<Niño> mayores = new ArrayList<>();
                for (Niño n : todosAisha) {
                    if (n.getEdad() < 5) {
                        menores.add(n);
                    } else {
                        mayores.add(n);
                    }
                }
                for (Niño n : mayores) {
                    aisha.recibeNiño(n);
                }
                for (Niño n : menores) {
                    dalsy.recibeNiño(n);
                }
                console.writeln("Niños menores de 5 años pasan a Dalsy:");
                for (Niño n : menores) {
                    console.writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                }
                console.writeln("Niños que se quedan con Aisha para jugar:");
                for (int i = 0; i < aisha.size(); i++) {
                    Niño n = aisha.getNiño(i);
                    console.writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                }
                console.writeln("NOTA: Al terminar el juego, los niños volverán a su posición original");
                // Restore to original positions
                aisha.vaciarCola();
                for (int i = 0; i < menores.size(); i++) {
                    dalsy.removeLastNiño();
                }
                for (Niño n : originalDalsy) {
                    dalsy.recibeNiño(n);
                }
                for (Niño n : todosAisha) {
                    aisha.recibeNiño(n);
                }
                break;
            case 12:
                console.writeln("¡ALARMA CONTRA INCENDIOS!\n");
                console.writeln("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
                List<Niño> fromDalsy = dalsy.transferirTodosA(lydia);
                List<Niño> fromAisha = aisha.transferirTodosA(lydia);
                int totalTrans = fromDalsy.size() + fromAisha.size();
                console.writeln("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                console.writeln("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
                console.writeln(totalTrans + " niños transferidos\n");
                console.writeln("Lydia ahora tiene " + lydia.size() + " niños listos para evacuar en orden");
                break;
            case 13:
                console.writeln("========================================\n        ESTADO ACTUAL\n========================================\n");
                mostrarMonitor(lydia, "LYDIA");
                mostrarMonitor(aisha, "AISHA");
                mostrarMonitor(dalsy, "DALSY");
                console.writeln("========================================\n");
                break;
            case 0:
                break;
            default:
                console.writeln("Opción inválida. Intente nuevamente.");
        }
    }

    private void mostrarMonitor(Monitor m, String nombreMonitor) {
        console.writeln(nombreMonitor + ":");
        if (m.size() == 0) {
            console.writeln("  Cola vacía\n");
        } else {
            console.writeln("  Niños en cola: " + m.size());
            for (int i = 0; i < m.size(); i++) {
                Niño n = m.getNiño(i);
                console.writeln("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
            }
            console.writeln();
        }
    }

    public static void main(String[] args) {
        new Mundo().ejecutarSimulacion();
    }
}