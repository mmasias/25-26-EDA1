public class Lydia {
    private ColaNinos cola;

    public Lydia() {
        cola = new ColaNinos();
    }

    public void recibirNino(Nino nino) {
        cola.encolar(nino);
        System.out.println(nino.getNombre() + " pasa a la cola de Lydia");
    }

    public ColaNinos getCola() {
        return cola;
    }

    public void transferirAAisha(Aisha aisha) {
        System.out.println("Lydia transfiere sus niños a Aisha");
        while (!cola.estaVacia()) {
            Nino nino = cola.desencolar();
            aisha.meterEnCola(nino);
            System.out.println("- " + nino);
        }
    }
}
