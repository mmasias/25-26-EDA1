public class Ludoteca {
    private final Monitor lydia;
    private final Monitor aisha;
    private final Pizarra pizarraSalon;

    public Ludoteca() {
        this.lydia = new Monitor("Lydia");
        this.aisha = new Monitor("Aisha");
        this.pizarraSalon = new Pizarra();
    }

    public void recibirNiño(Nino nino) {
        lydia.recibir(nino);
        System.out.println("[Llega] " + nino.getNombre() + " (Lydia: " + lydia.tamanoCola() + ")");
    }

    public void gestionarTraspaso() {
        if (!aisha.estaJugando() && lydia.tieneNiños()) {
            lydia.entregarATransferencia(aisha);
        }
    }

    public boolean aishaPuedeJugar() {
        return aisha.puedeJugar();
    }

    public int jugarPartida(int minutoActual) {
        gestionarTraspaso();
        int n = aisha.tamanoCola();
        if (n <= 5) return 1;

        System.out.println();
        System.out.println("[min " + minutoActual + "] Comienza partida con " + n + " niños.");
        int duracion = aisha.jugar(pizarraSalon, minutoActual);
        System.out.println("Termina partida (duración " + duracion + " min). Cola Aisha tras juego: " + aisha.tamanoCola());

        if (lydia.tieneNiños()) {
            lydia.entregarATransferencia(aisha);
            System.out.println("Se transfieren niños que esperaban con Lydia. Aisha: " + aisha.tamanoCola());
        }

        return duracion;
    }

    public void verEstado() {
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
        System.out.println("Uso de arrays: Lydia " + lydia.getUsoMemoria() + " | Aisha " + aisha.getUsoMemoria());
    }
}