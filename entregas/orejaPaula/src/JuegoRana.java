public class JuegoRana {
    private Aisha aisha;
    private Dalsy dalsy;

    public JuegoRana(Aisha aisha, Dalsy dalsy) {
        this.aisha = aisha;
        this.dalsy = dalsy;
    }
}
public boolean puedeJugar() {
    return aisha.verificarJuegoRana();
}
public void iniciarJuego() {
    aisha.separarParaJuego(dalsy);
    System.out.println("Juego de la rana en progreso...");
}
