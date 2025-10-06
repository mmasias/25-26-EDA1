
import java.util.Scanner;

public class Ludoteca {
    private Monitora lydia = new Monitora("Lydia");
    private Monitora aisha = new Monitora("Aisha");
    private Monitora dalsy = new Monitora("Dalsy");
    private Scanner sc = new Scanner(System.in);
    private boolean juegoTelefonoEnCurso = false;

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            procesarOpcion(opcion);
            System.out.println();
        } while (opcion != 0);
        System.out.println("Fin de la simulación. ¡Hasta luego!");
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
14. Simular juego del teléfono descacharrado
0.  Salir
""");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> simularLlegadaNinio();
            case 2 -> simularInicioJuego();
            case 3 -> aisha.presentacionGeneral();
            case 4 -> aisha.presentacionPorEdad();
            case 5 -> aisha.presentacionPorInicial();
            case 6 -> aisha.presentarPrimerosCinco();
            case 7 -> aisha.presentarUltimosCinco();
            case 8 -> mostrarConteo();
            case 9 -> aisha.mostrarEdadPromedio();
            case 10 -> aisha.intentoJuegoRana();
            case 11 -> aisha.separarParaJuego(dalsy);
            case 12 -> alarmaIncendios();
            case 13 -> mostrarEstado();
            case 14 -> jugarTelefonoDescacharrado();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción no válida");
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                String line = sc.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.print("Entrada no válida. Intenta de nuevo: ");
            }
        }
    }

    private void simularLlegadaNinio() {
        System.out.print("Nombre del niño: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre no puede estar vacío.");
            return;
        }
        System.out.print("Edad del niño: ");
        int edad = leerEnteroNonNegative();
        Ninio n = new Ninio(nombre, edad);
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        
        lydia.recibirNinio(n);
    }

    private int leerEnteroNonNegative() {
        while (true) {
            int v = leerEntero();
            if (v >= 0) return v;
            System.out.print("Introduce un número no negativo: ");
        }
    }

    private void simularInicioJuego() {
        if (lydia.getCantidadNinios() < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        } else {
            System.out.println("Lydia transfiere sus niños a Aisha");
            Ninio[] transferidos = lydia.transferirNinios();
            aisha.recibirTransferencia(transferidos);
            System.out.println("Niños transferidos:");
            for (int i = 0; i < transferidos.length; i++) {
                System.out.println("- " + transferidos[i]);
            }
        }
    }

    private void mostrarConteo() {
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.printf("Lydia tiene %d niños en cola%n", lydia.getCantidadNinios());
        System.out.printf("Aisha tiene %d niños en cola%n", aisha.getCantidadNinios());
        System.out.printf("Dalsy tiene %d niños en cola%n", dalsy.getCantidadNinios());
        System.out.printf("Total: %d niños%n",
                lydia.getCantidadNinios() + aisha.getCantidadNinios() + dalsy.getCantidadNinios());
    }

    private void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        
        Ninio[] fromAisha = aisha.transferirNinios();
        Ninio[] fromDalsy = dalsy.transferirNinios();
        lydia.recibirTransferencia(fromAisha);
        lydia.recibirTransferencia(fromDalsy);
        int transferred = fromAisha.length + fromDalsy.length;
        System.out.println(transferred + " niños transferidos");
        System.out.printf("Lydia ahora tiene %d niños listos para evacuar en orden%n", lydia.getCantidadNinios());
    }

    private void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        lydia.mostrarEstado();
        aisha.mostrarEstado();
        dalsy.mostrarEstado();
        System.out.println("========================================");
    }

    private void jugarTelefonoDescacharrado() {
        
        if (aisha.getCantidadNinios() <= 5) {
            System.out.println("No se puede iniciar el juego: la fila de Aisha debe tener más de 5 niños.");
            return;
        }

        
        juegoTelefonoEnCurso = true;
        System.out.println("Aisha empieza el juego del Teléfono Descacharrado...");
        
        Pizarra salon = new Pizarra();
        salon.limpiar();

        
        aisha.limpiarPizarrines();

        
        String mensajeInicial = generarMensajeAleatorio(10);
        System.out.println("Aisha escribe en su pizarrín: " + mensajeInicial);

        
        aisha.copiarMensajeAlPrimerNinio(mensajeInicial);

        
        int minutos = 1; 
        for (int i = 1; i < aisha.getCantidadNinios(); i++) {
            System.out.println("Minuto " + minutos + ": el niño " + (i) + " muestra su pizarrín al siguiente");
            String recibido = aisha.leerMensajeDeNinio(i - 1);
            aisha.copiarMensajeDeNinioA(i - 1, i);
            minutos++;
        }

        
        String mensajeFinal = aisha.leerMensajeDeNinio(aisha.getCantidadNinios() - 1);
        System.out.println("Minuto " + minutos + ": el último niño corre a la pizarra del salón y escribe el mensaje...");
        salon.escribir(mensajeFinal);

        System.out.println();
        System.out.println("Mensaje inicial: " + mensajeInicial);
        System.out.println("Mensaje final en la pizarra del salón: " + salon.leer());

        
        juegoTelefonoEnCurso = false;
    }

    private String generarMensajeAleatorio(int longitud) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            char c = (char) ('A' + (int) (Math.random() * 26));
            sb.append(c);
        }
        return sb.toString();
    }
}
