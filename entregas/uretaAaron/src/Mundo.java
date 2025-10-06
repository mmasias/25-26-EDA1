import java.util.Scanner;

public class Mundo {
    private final Ludoteca ludoteca; 
    private final Scanner sc;        

    public Mundo() {
        this.ludoteca = new Ludoteca(); 
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) { 
        new Mundo().ejecutarSimulacion();
    }

    public void ejecutarSimulacion() { 
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: "); 
            System.out.println();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.print("Pulsa ENTER para continuar..."); 
                sc.nextLine();
                System.out.println();
            }
int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: "); 
            System.out.println();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.print("Pulsa ENTER para continuar..."); 
                sc.nextLine();
                System.out.println();
            }
        } while (opcion != 0);
        
        System.out.println("Hasta pronto 👋");
    }

    private void mostrarMenu() { // (u1)
        System.out.println("========================================");
        System.out.println("           LUDOTECA · SIMULACIÓN");
        System.out.println("========================================");
        System.out.println(" 1) Llegada de un niño/niña");
        System.out.println(" 2) Intento de inicio de juego");
        System.out.println(" 3) Aisha se presenta y pide presentaciones");
        System.out.println(" 4) Presentaciones de >= edad mínima");
        System.out.println(" 5) Presentaciones por inicial del nombre");
        System.out.println(" 6) Mostrar cinco primeros");
        System.out.println(" 7) Mostrar cinco últimos");
        System.out.println(" 8) Conteo de asistencia (todas las colas)");
        System.out.println(" 9) Edad promedio en Aisha");
        System.out.println("10) Intento de iniciar el juego de la rana");
        System.out.println("11) Separar menores de 5 años hacia Dalsy");
        System.out.println("12) Alarma contra incendios (protocolo)");
        System.out.println("13) Mostrar monitoras y niños");
        System.out.println("----------------------------------------");
        System.out.println(" 0) Salir");
        System.out.println("----------------------------------------");
    }

    private void procesarOpcion(int opcion) { 
        switch (opcion) {
            case 1 -> opcionLlegada();
            case 2 -> ludoteca.intentoInicioDeJuego();
            case 3 -> ludoteca.presentacionesGeneralesAisha();
            case 4 -> {
                int edadMin = leerEntero("Edad mínima: ");
                ludoteca.presentacionesPorEdadMinima(edadMin);
            }
            case 5 -> {
                char letra = leerLetra("Letra inicial (A-Z): ");
                ludoteca.presentacionesPorInicial(letra);
            }
            case 6 -> ludoteca.primerosCinco();
            case 7 -> ludoteca.ultimosCinco();
            case 8 -> ludoteca.conteoAsistencia();
            case 9 -> ludoteca.edadPromedioAisha();
            case 10 -> ludoteca.intentoJuegoDeLaRana();
            case 11 -> ludoteca.separarMenoresAJuegoRana();
            case 12 -> ludoteca.alarmaIncendios();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("⚠ Opción no válida");
        }
    }


private void opcionLlegada() { // (u1)
        String nombre = leerTextoNoVacio("Nombre del niño/niña: "); 
        int edad = leerEntero("Edad (años): ");                     
        ludoteca.llegadaDeNino(nombre, edad);
    }

   

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Valor no válido. Introduce un número entero.");
            }
        }
    }

    private char leerLetra(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.length() == 1 && Character.isLetter(s.charAt(0))) {
                return Character.toUpperCase(s.charAt(0));
            }
            System.out.println("Debe introducir una única letra (A-Z).");
        }
    }

    private String leerTextoNoVacio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("El campo no puede estar vacío.");
        }
    }
}
