public class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Pizarra pizarraDelSalon;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        pizarraDelSalon = new Pizarra();
    }

    public void recibirNiño(Niño niño) {
        lydia.recibeNiño(niño);
    }

    public void actualizar() {
        gestionarNiños();
        if (aisha.puedeJugar()) {
            aisha.jugar(pizarraDelSalon);
        }
    }

    private void gestionarNiños() {
        if (lydia.tieneNiños() && !aisha.estaJugando()) {
            lydia.entregaNiños(aisha);
        }
    }

    public void verEstado() {
    lydia.mostrarListaNiños();
    aisha.mostrarListaNiños();
    System.out.println("Uso de arrays: Lydia " + lydia.getUsoMemoria() + ", Aisha " + aisha.getUsoMemoria());
    System.out.println("Pizarra del salón: [" + pizarraDelSalon.leerMensaje() + "]");
    }
}
