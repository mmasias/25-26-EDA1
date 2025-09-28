
class Ludoteca {
    private Monitor lydia;              
    private Monitor aisha;               
    private Pizarra pizarraDelSalon;     

    public Ludoteca() {
        this.lydia = new Monitor("Lydia");
        this.aisha = new Monitor("Aisha");
        this.pizarraDelSalon = new Pizarra();
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
        new Console().writeln("Uso de arrays: Lydia " + lydia.getUsoMemoria()
                + ", Aisha " + aisha.getUsoMemoria());
    }
}

