package reto002;

public class Ludoteca {
    private Monitora lydia = new Monitora("Lydia");
    private Monitora aisha = new Monitora("Aisha");
    private Monitora dalsy = new Monitora("Dalsy");

    public Monitora getLydia() { return lydia; }
    public Monitora getAisha() { return aisha; }
    public Monitora getDalsy() { return dalsy; }

    public int totalNinios() {
        return lydia.cantidad() + aisha.cantidad() + dalsy.cantidad();
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println();
        System.out.print(lydia.toString());
        System.out.println();
        System.out.print(aisha.toString());
        System.out.println();
        System.out.print(dalsy.toString());
        System.out.println("========================================");
    }
}
