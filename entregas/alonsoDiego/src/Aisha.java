public class Aisha {
    private ColaNiños cola;

    public Aisha() {
        cola = new ColaNiños();
    }

    public void meterEnCola(Niño niño) {
        cola.encolar(niño);
    }

    public ColaNiños getCola() {
        return cola;
    }
}

