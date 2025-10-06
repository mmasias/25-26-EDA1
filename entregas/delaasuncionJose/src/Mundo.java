import java.util.Scanner;

public class Mundo {
    private Ludoteca ludoteca = new Ludoteca();
    private Scanner sc = new Scanner(System.in);

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");
            procesarOpcion(opcion);
            if (opcion != 0) pause();
        } while (opcion != 0);
        System.out.println("Saliendo...");
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
                opcion1();
                break;
            case 2:
                opcion2();
                break;
            case 3:
                opcion3();
                break;
            case 4:
                opcion4();
                break;
            case 5:
                opcion5();
                break;
            case 6:
                opcion6();
                break;
            case 7:
                opcion7();
                break;
            case 8:
                opcion8();
                break;
            case 9:
                opcion9();
                break;
            case 10:
                opcion10();
                break;
            case 11:
                opcion11();
                break;
            case 12:
                opcion12();
                break;
            case 13:
                opcion13();
                break;
            case 0:
                // salir
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void opcion1() {
        String nombre = leerString("Nombre: ");
        int edad = leerInt("Edad: ");
        Nino n = ludoteca.simularLlegada(nombre, edad);
        System.out.println("Llega " + n.getNombre() + " (" + n.getEdad() + " años)");
        System.out.println(n.getNombre() + " pasa a la cola de Lydia");
    }

    private void opcion2() {
        Nino[] transferidos = ludoteca.intentarInicioJuego(5);
        if (transferidos.length == 0) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        } else {
            System.out.println("Lydia transfiere sus niños a Aisha");
            for (int i = 0; i < transferidos.length; i++) System.out.println(transferidos[i].toString());
        }
    }

    private void opcion3() {
        System.out.print(ludoteca.presentacionesGenerales());
    }

    private void opcion4() {
        int x = leerInt("Edad mínima: ");
        System.out.print(ludoteca.presentacionesPorEdad(x));
    }

    private void opcion5() {
        String s = leerString("Inicial (una letra): ");
        if (s.isEmpty()) { System.out.println("Letra no válida"); return; }
        char c = s.charAt(0);
        System.out.print(ludoteca.presentacionesPorInicial(c));
    }

    private void opcion6() {
        System.out.print(ludoteca.primerosCinco());
    }

    private void opcion7() {
        System.out.print(ludoteca.ultimosCinco());
    }

    private void opcion8() {
        System.out.print(ludoteca.conteoAsistencia());
    }

    private void opcion9() {
        System.out.println(ludoteca.edadPromedioAisha());
    }

    private void opcion10() {
        System.out.print(ludoteca.intentoJuegoRana());
    }

    private void opcion11() {
        System.out.print(ludoteca.separarMenoresParaJuego(5));
    }

    private void opcion12() {
        System.out.print(ludoteca.alarmaIncendios());
    }

    private void opcion13() {
        System.out.print(ludoteca.mostrarEstado());
    }

    private String leerString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private int leerInt(String msg) {
        while (true) {
            System.out.print(msg);
            String l = sc.nextLine().trim();
            try {
                return Integer.parseInt(l);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada no válida, ingrese un número");
            }
        }
    }

    private void pause() {
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
    }

    public static void main(String[] args) {
        new Mundo().ejecutarSimulacion();
    }
}
