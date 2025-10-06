public class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void recibirNiñoEnLydia(Niño niño) { lydia.recibirNiño(niño); }

    public Monitor getLydia() { return lydia; }
    public Monitor getAisha() { return aisha; }
    public Monitor getDalsy() { return dalsy; }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("       ESTADO ACTUAL DE LA LUDOTECA     ");
        System.out.println("========================================");
        lydia.mostrarLista();
        aisha.mostrarLista();
        dalsy.mostrarLista();
        System.out.println("========================================");
    }
}
