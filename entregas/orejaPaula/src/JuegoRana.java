```java
public class JuegoRana {
    private Aisha aisha;
    private Dalsy dalsy;

    public JuegoRana(Aisha aisha, Dalsy dalsy) {
        this.aisha = aisha;
        this.dalsy = dalsy;
    }

    public boolean puedeJugar() {
        return aisha.verificarJuegoRana();
    }

    public void iniciarJuego() {
        aisha.separarParaJuego(dalsy);
        System.out.println("Juego de la rana en progreso...");
    }

    public void finalizarJuego() {
        for (Niño n : dalsy.getCola()) {
            if (n.getEdad() < 5) aisha.recibirNiño(n);
        }
        dalsy.getCola().removeIf(n -> n.getEdad() < 5);
        System.out.println("Juego de la rana terminado, todos los niños vuelven a Aisha");
    }
}
```

