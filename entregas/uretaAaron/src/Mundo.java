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