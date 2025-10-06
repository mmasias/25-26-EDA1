public class Simulacion {

    private static final int CAPACIDAD_MAXIMA_DE_DALSY = 5;
    private static final int CAPACIDAD_MAXIMA_DE_AISHA = 5;
    private static final int CAPACIDAD_MAXIMA_DE_LYDIA = 20;
    private Monitora lydia;
    private Monitora aisha;
    private Monitora dalsy;

    public Simulacion() {
        this.lydia = new Monitora("Lydia", CAPACIDAD_MAXIMA_DE_LYDIA);
        this.aisha = new Monitora("Aisha", CAPACIDAD_MAXIMA_DE_AISHA);
        this.dalsy = new Monitora("Dalsy", CAPACIDAD_MAXIMA_DE_DALSY);
    }

    public void simularLlegadaNino() {
        Niño niño = generarNinoAleatorio();
        lydia.recibeNiño(niño);
    }

    private Niño generarNinoAleatorio() {
        String[] nombres = { "Ana", "Luis", "Marta", "Pedro", "Pepe", "Miguel", "Isaias", "Ale", "Rebeca", "Daisy", "Rosa", "Chofo"};
        int edad = (int) (Math.random() * 10 + 1);
        String nombre = nombres[(int) (Math.random() * nombres.length)];
        return new Niño(nombre, edad);
    }

    public void simularInicioJuego() {
        lydia.entregaNiños(aisha);
    }

    public void aishaPresentaYNinosSePresentan() {

    }

    public void aishaPideNinosMayoresDe5() {

    }

    public void aishaPideNinosPorLetra() {

    }

    public void aishaPideCincoPrimeros() {

    }

    public void aishaPideCincoUltimos() {

    }

    public void aishaYLydiaCuentanNinos() {

    }

    public void aishaPromedioEdad() {

    }

    public void simularInicioJuegoRana() {
        lydia.entregaNiños(dalsy);
    }

    public void pasarMenoresCincoADalsy() {

    }

    public void activarProtocoloEmergencia() {

    }

    public void mostrarMonitorasYNinos() {

    }

}
