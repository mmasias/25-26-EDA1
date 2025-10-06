import java.util.Scanner;

public class Simulacion {

    private static final int CAPACIDAD_MAXIMA_DE_NIÑOS = 20;
    private Monitora lydia;
    private Monitora aisha;
    private Monitora dalsy;
    private Scanner scanner;

    public Simulacion() {
        this.lydia = new Monitora("Lydia", CAPACIDAD_MAXIMA_DE_NIÑOS);
        this.aisha = new Monitora("Aisha", CAPACIDAD_MAXIMA_DE_NIÑOS);
        this.dalsy = new Monitora("Dalsy", CAPACIDAD_MAXIMA_DE_NIÑOS);
        this.scanner = new Scanner(System.in);
    }

    public void simularLlegadaNino() {
        Niño niño = generarNinoAleatorio();
        lydia.recibeNiño(niño);
    }

    private Niño generarNinoAleatorio() {
        String[] nombres = { "Ana", "Luis", "Marta", "Pedro", "Pepe", "Miguel", "Isaias", "Ale", "Rebeca", "Daisy",
                "Rosa", "Chofo" };
        int edad = (int) (Math.random() * 10 + 1);
        String nombre = nombres[(int) (Math.random() * nombres.length)];
        return new Niño(nombre, edad);
    }

    public void simularInicioJuego() {
        lydia.entregaNiños(aisha);
    }

    public void aishaPresentaYNinosSePresentan() {
        aisha.presentarse();
        aisha.prepararCadenaPresentaciones();
        aisha.pideAlPrimerNiñoPresentarse();
    }

    public void aishaPideNinosMayoresDe5() {
        aisha.pedirNiñosMayoresDe5Presentarse();
    }

    public void aishaPideNinosPorLetra() {
        char letra = solicitarLetra();
        aisha.pedirNiñosPorLetra(letra);
    }

    private char solicitarLetra() {
        System.out.print("Ingrese la letra con la que deben empezar los nombres de los niños: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No se ingresó ninguna letra. Se usará 'A' por defecto.\n");
            return 'A';
        }

        return input.charAt(0);
    }

    public void aishaPideCincoPrimeros() {
        aisha.pedirCincoPrimerosPresentarse();
    }

    public void aishaPideCincoUltimos() {
        aisha.pedirCincoUltimosPresentarse();
    }

    public void aishaYLydiaCuentanNinos() {
        int niñosDeAisha = aisha.cantidadDeNiños();
        int niñosDeLydia = lydia.cantidadDeNiños();
        int niñosDeDalsy = dalsy.cantidadDeNiños();
        int total = sumarNiños(niñosDeAisha, niñosDeLydia, niñosDeDalsy);
        mostrarAsistencia(total);
    }

    private int sumarNiños(int niñosDeAisha, int niñosDeLydia, int niñosDeDalsy) {
        return niñosDeLydia + niñosDeAisha + niñosDeDalsy;
    }

    private void mostrarAsistencia(int total) {
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.cantidadDeNiños() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.cantidadDeNiños() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.cantidadDeNiños() + " niños en cola");
        System.out.println("Total: " + total + " niños\n");
    }

    public void edadPromedioDeLosNiños() {
        double promedio = aisha.decirEdadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años\n\n", promedio);
    }

    public void simularInicioJuegoRana() {
        lydia.entregaNiños(dalsy);
    }

    public void pasarMenoresDeCincoADalsy() {
        System.out.println("Separando niños para el juego de la rana...\n");
        aisha.separarNiñosMenoresDe5(dalsy);
    }

    public void activarProtocoloEmergencia() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");

        Niño[] deDalsy = dalsy.entregaTodosNiñosA(lydia);

        Niño[] deAisha = aisha.entregaTodosNiñosA(lydia);

        int totalTransferidos = deDalsy.length + deAisha.length;

        System.out.println(totalTransferidos + " niños transferidos\n");
        System.out.println("Lydia ahora tiene " + lydia.cantidadDeNiños() + " niños listos para evacuar en orden\n");
    }

    public void mostrarMonitorasYNinos() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");

        lydia.mostrarEstado();
        aisha.mostrarEstado();
        dalsy.mostrarEstado();

        System.out.println("========================================\n");
    }

}
