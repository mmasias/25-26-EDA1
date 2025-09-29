public class Ludoteca {
private final Monitor lydia;
private final Monitor aisha;
private final Pizarra pizarraSalon;


public Ludoteca() {
this.pizarraSalon = new Pizarra();
Cola colaLydia = new Cola(Configuracion.CAPACIDAD_COLA);
Cola colaAisha = new Cola(Configuracion.CAPACIDAD_COLA);
this.lydia = new Monitor("Lydia", colaLydia);
this.aisha = new Monitor("Aisha", colaAisha);
}


public void recibirNiño(Niño n) { lydia.recibeNiño(n); }


public void actualizar() {
if (lydia.tieneNiños() && !aisha.estaJugando()) {
lydia.entregarNiños(aisha);
}
if (aisha.puedeJugar() && !aisha.estaJugando()) {
aisha.iniciarJuego(pizarraSalon);
}
if (aisha.estaJugando()) {
aisha.jugarUnPaso();
}
}


public void verEstado() {
System.out.println("Estado colas:");
lydia.mostrarListaNiños();
aisha.mostrarListaNiños();
System.out.println();
}
}