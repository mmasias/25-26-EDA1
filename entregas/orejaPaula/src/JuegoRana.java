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
