
import java.util.Scanner;

public class Ludoteca {

    private LidiaMonitora lidia = new LidiaMonitora("Lydia");
    private AishaMonitora aisha = new AishaMonitora("Aisha");
    private DalsyMonitora dalsy = new DalsyMonitora("Dalsy");
    private Scanner sc = new Scanner(System.in);

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.println();
                System.out.print("Presione ENTER para continuar...");
                sc.nextLine();
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1.  Simular llegada de niño");
        System.out.println("2.  Simular intento de inicio de juego");
        System.out.println("3.  Aisha se presenta y pide a los niños que se presenten");
        System.out.println("4.  Aisha pide que se presenten los mayores de 5 años");
        System.out.println("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        System.out.println("6.  Aisha pide que se presenten los cinco primeros niños");
        System.out.println("7.  Aisha pide que se presenten los cinco últimos niños");
        System.out.println("8.  Aisha y Lydia dicen cuántos niños hay en cola");
        System.out.println("9.  Aisha dice la edad promedio de los niños en cola");
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println();
        System.out.println("0.  Salir");
        System.out.println();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                simularLlegada();
                return;
            case 2:
                intentoInicioJuego();
                return;
            case 3:
                presentacionesGenerales();
                return;
            case 4:
                presentacionesPorEdadMinima();
                return;
            case 5:
                presentacionesPorInicial();
                return;
            case 6:
                presentarPrimerosCinco();
                return;
            case 7:
                presentarUltimosCinco();
                return;
            case 8:
                conteoAsistencia();
                return;
            case 9:
                edadPromedioAisha();
                return;
            case 10:
                intentoJuegoRana();
                return;
            case 11:
                separarMenoresADalsyConRestauracion();
                return;
            case 12:
                alarmaIncendios();
                return;
            case 13:
                mostrarEstado();
                return;
            case 0:
                System.out.println("¡Hasta luego!");
                return;
            default:
                System.out.println("Opción no válida.");
                return;
        }
    }

    private void simularLlegada() {
        System.out.print("Nombre del niño: ");
        String nombre = sc.nextLine().trim();
        int edad = leerEntero("Edad del niño: ");
        Nino n = new Nino(nombre, edad);
        System.out.println();
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        System.out.println(nombre + " pasa a la cola de Lydia");
        lidia.agregarAFila(n, true);
    }

    private void intentoInicioJuego() {
        if (lidia.tamanoFila() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            Nino[] transferidos = lidia.transferirTodos();
            int i = 0;
            while (i < transferidos.length) {
                Nino n = transferidos[i];
                System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                aisha.agregarAFila(n, true);
                i = i + 1;
            }
            return;
        }
        System.out.println("No hay suficientes niños para iniciar el juego");
        System.out.println("Se necesitan al menos 5 niños");
    }

    private void presentacionesGenerales() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        Nino[] arr = aisha.getFila().getLista();
        int i = 0;
        while (i < arr.length) {
            Nino n = arr[i];
            System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            i = i + 1;
        }
    }

    private void presentacionesPorEdadMinima() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int edadMin = leerEntero("Edad mínima: ");
        System.out.println();
        System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:\n");
        Nino[] arr = aisha.getFila().getLista();
        int i = 0;
        while (i < arr.length) {
            Nino n = arr[i];
            if (n.getEdad() > edadMin) {
                System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            }
            i = i + 1;
        }
    }

    private void presentacionesPorInicial() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.print("Letra inicial: ");
        String letra = sc.nextLine().trim();
        if (letra.length() == 0) {
            System.out.println("No se ha introducido letra.");
            return;
        }
        char inicial = toLower(letra.charAt(0));
        System.out.println();
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra.charAt(0) + "':\n");
        Nino[] arr = aisha.getFila().getLista();
        int i = 0;
        while (i < arr.length) {
            Nino n = arr[i];
            String nombre = n.getNombre();
            if (nombre.length() > 0) {
                char c0 = toLower(nombre.charAt(0));
                if (c0 == inicial) {
                    System.out.println("[" + nombre + "]: Hola, soy " + nombre);
                }
            }
            i = i + 1;
        }
    }

    private void presentarPrimerosCinco() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        Nino[] arr = aisha.getFila().getLista();
        int limite = arr.length < 5 ? arr.length : 5;
        int i = 0;
        while (i < limite) {
            String nombre = arr[i].getNombre();
            System.out.println("[" + nombre + "]: Hola, soy " + nombre);
            i = i + 1;
        }
    }

    private void presentarUltimosCinco() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        Nino[] arr = aisha.getFila().getLista();
        int desde = arr.length - 5;
        if (desde < 0) {
            desde = 0;
        }
        int i = desde;
        while (i < arr.length) {
            String nombre = arr[i].getNombre();
            System.out.println("[" + nombre + "]: Hola, soy " + nombre);
            i = i + 1;
        }
    }

    private void conteoAsistencia() {
        int x = lidia.tamanoFila();
        int y = aisha.tamanoFila();
        int z = dalsy.tamanoFila();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + x + " niños en cola");
        System.out.println("Aisha tiene " + y + " niños en cola");
        System.out.println("Dalsy tiene " + z + " niños en cola");
        System.out.println("Total: " + (x + y + z) + " niños");
    }

    private void edadPromedioAisha() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        Nino[] arr = aisha.getFila().getLista();
        int suma = 0;
        int i = 0;
        while (i < arr.length) {
            suma = suma + arr[i].getEdad();
            i = i + 1;
        }

        int decimas = (suma * 10 + arr.length / 2) / arr.length;
        int parteEntera = decimas / 10;
        int parteDecimal = decimas % 10;
        System.out.println("Edad promedio de los niños en la cola de Aisha: " + parteEntera + "." + parteDecimal + " años");
    }

    private void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.tamanoFila();
        int ge5 = 0;
        Nino[] arr = aisha.getFila().getLista();
        int i = 0;
        while (i < arr.length) {
            if (arr[i].getEdad() >= 5) {
                ge5 = ge5 + 1;
            }
            i = i + 1;
        }
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + ge5);
        if (ge5 > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
            return;
        }
        System.out.println("No hay suficientes niños mayores de 5 años");
        System.out.println("No pueden jugar todavía");
    }

    private void separarMenoresADalsyConRestauracion() {
        if (aisha.tamanoFila() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Separando niños para el juego de la rana...");
        Nino[] backupAisha = aisha.getFila().getLista();
        Nino[] backupDalsy = dalsy.getFila().getLista();

        aisha.getFila().setDesde(new Nino[0]);
        dalsy.getFila().setDesde(new Nino[0]);

        int i = 0;
        while (i < backupAisha.length) {
            Nino n = backupAisha[i];
            if (n.getEdad() < 5) {
                dalsy.agregarAFila(n, true);
            } else {
                aisha.agregarAFila(n, true);
            }
            i = i + 1;
        }

        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        if (dalsy.tamanoFila() == 0) {
            System.out.println("- (ninguno)"); 
        }else {
            Nino[] arrD = dalsy.getFila().getLista();
            i = 0;
            while (i < arrD.length) {
                System.out.println("- " + arrD[i].getNombre() + " (" + arrD[i].getEdad() + " años)");
                i = i + 1;
            }
        }
        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        if (aisha.tamanoFila() == 0) {
            System.out.println("- (ninguno)"); 
        }else {
            Nino[] arrA = aisha.getFila().getLista();
            i = 0;
            while (i < arrA.length) {
                System.out.println("- " + arrA[i].getNombre() + " (" + arrA[i].getEdad() + " años)");
                i = i + 1;
            }
        }
        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");

        aisha.getFila().setDesde(backupAisha);
        dalsy.getFila().setDesde(backupDalsy);
    }

    private void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");

        Nino[] deDalsy = dalsy.vaciar();
        Nino[] deAisha = aisha.vaciar();

        int contador = 0;
        lidia.recibirMuchosSilencioso(deDalsy);
        contador = contador + deDalsy.length;
        lidia.recibirMuchosSilencioso(deAisha);
        contador = contador + deAisha.length;

        System.out.println(contador + " niños transferidos\n");
        System.out.println("Lydia ahora tiene " + lidia.tamanoFila() + " niños listos para evacuar en orden");
    }

    // === Opción 13 ===
    private void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println();

        System.out.println("LYDIA:");
        if (lidia.tamanoFila() > 0) {
            System.out.println("  Niños en cola: " + lidia.tamanoFila());
            Nino[] arr = lidia.getFila().getLista();
            int i = 0;
            while (i < arr.length) {
                System.out.println("  - " + arr[i].getNombre() + " (" + arr[i].getEdad() + " años)");
                i = i + 1;
            }
        } else {
            System.out.println("  Cola vacía");
        }
        System.out.println();

        System.out.println("AISHA:");
        if (aisha.tamanoFila() > 0) {
            System.out.println("  Niños en cola: " + aisha.tamanoFila());
            Nino[] arr = aisha.getFila().getLista();
            int i = 0;
            while (i < arr.length) {
                System.out.println("  - " + arr[i].getNombre() + " (" + arr[i].getEdad() + " años)");
                i = i + 1;
            }
        } else {
            System.out.println("  Cola vacía");
        }
        System.out.println();

        System.out.println("DALSY:");
        if (dalsy.tamanoFila() > 0) {
            System.out.println("  Niños en cola: " + dalsy.tamanoFila());
            Nino[] arr = dalsy.getFila().getLista();
            int i = 0;
            while (i < arr.length) {
                System.out.println("  - " + arr[i].getNombre() + " (" + arr[i].getEdad() + " años)");
                i = i + 1;
            }
        } else {
            System.out.println("  Cola vacía");
        }
        System.out.println("========================================");
    }

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();

            if (linea.length() == 0) {
                System.out.println("Por favor, introduzca un número válido.");
                continue;
            }

            boolean ok = true;
            int i = 0;
            int n = linea.length();
            while (ok && i < n) {
                char c = linea.charAt(i);
                if (c < '0' || c > '9') {
                    ok = false;
                } else {
                    i = i + 1;
                }
            }
            if (!ok) {
                System.out.println("Por favor, introduzca un número válido.");
                continue;
            }

            int valor = 0;
            i = 0;
            while (i < n) {
                valor = valor * 10 + (linea.charAt(i) - '0');
                i = i + 1;
            }
            return valor;
        }
    }

    private char toLower(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }
}
