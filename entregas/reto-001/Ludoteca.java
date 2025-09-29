public class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
    }

    public void recibirNiño(Niño niño) {
        lydia.recibeNiño(niño);
    }

    public void actualizar() {
        if (lydia.tieneNiños() && !aisha.estaJugando()) {
            lydia.entregaNiños(aisha);
        }
        if (aisha.puedeJugar()) {
            aisha.jugar();
        }
    }

    public void verEstado() {
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
        System.out.println("Uso de memoria: Lydia " + lydia.getUsoMemoria() + ", Aisha " + aisha.getUsoMemoria());
    }
}
