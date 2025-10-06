public class Dalsy {
    private ColaNinos cola;

    public Dalsy() {
        cola = new ColaNinos();
    }

    public void recibirNino(Nino nino) {
        cola.encolar(nino);
    }

    public ColaNinos getCola() {
        return cola;
    }

    public void transferirALydia(Lydia lydia) {
        while (!cola.estaVacia()) {
            lydia.recibirNino(cola.desencolar());
        }
    }
}