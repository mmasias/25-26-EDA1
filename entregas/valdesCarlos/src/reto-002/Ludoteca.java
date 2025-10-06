
public class Ludoteca {
    private Niño[] colaNiños;
    private int cantidadCola;

    private Monitor aisha;
    private Monitor lydia;
    private Monitor dalsy;
    private Pizarra pizarra;

    private static final int MAX_NINOS = 50; 

    public Ludoteca() {
        colaNiños = new Niño[MAX_NINOS];
        cantidadCola = 0;

        aisha = new Monitor("Aisha");
        lydia = new Monitor("Lydia");
        dalsy = new Monitor("Dalsy");
        pizarra = new Pizarra();
    }

    
    public void simularLlegadaNino() {
        System.out.println("Simulando llegada de un niño...");
        
    }

    public void simularInicioJuego() {
        System.out.println("Simulando intento de inicio de juego...");
    }

    public void presentacionGeneral() {
        System.out.println("Aisha pide que se presenten todos los niños...");
    }

    public void presentarMayoresDe5() {
        System.out.println("Presentando niños mayores de 5 años...");
    }

    public void presentarPorLetra(char letra) {
        System.out.println("Presentando niños cuyo nombre empieza con: " + letra);
    }

    public void presentarPrimeros5() {
        System.out.println("Presentando los cinco primeros niños...");
    }

    public void presentarUltimos5() {
        System.out.println("Presentando los cinco últimos niños...");
    }

    public void mostrarCola() {
        System.out.println("Aisha y Lydia dicen cuántos niños hay en cola...");
        System.out.println("Cantidad de niños en cola: " + cantidadCola);
    }

    public void mostrarEdadPromedio() {
        System.out.println("Aisha calcula la edad promedio de los niños...");
    }

    public void simularJuegoRana() {
        System.out.println("Simulando intento de inicio del juego de la rana...");
    }

    public void pasarNinosMenoresADalsy() {
        System.out.println("Pasando niños menores de 5 años a Dalsy...");
    }

    public void protocoloEmergencia() {
        System.out.println("¡Alarma activada! Protocolo de emergencia...");
    }

    public void mostrarMonitorasYNinos() {
        System.out.println("Mostrando monitoras y niños...");
        System.out.println("--- Aisha ---");
        aisha.presentarNiños();
        System.out.println("--- Lydia ---");
        lydia.presentarNiños();
        System.out.println("--- Dalsy ---");
        dalsy.presentarNiños();
    }

    
    public boolean agregarNinoACola(Niño n) {
        if (cantidadCola < MAX_NINOS) {
            colaNiños[cantidadCola] = n;
            cantidadCola++;
            aisha.agregarNiño(n); 
            return true;
        } else {
            System.out.println("La cola de la ludoteca está llena.");
            return false;
        }
    }

    public Niño[] getColaNiños() {
        return colaNiños;
    }

    public Pizarra getPizarra() {
        return pizarra;
    }
}
